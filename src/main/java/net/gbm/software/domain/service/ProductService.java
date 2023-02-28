package net.gbm.software.domain.service;

import lombok.extern.slf4j.Slf4j;
import net.gbm.software.application.data.ResponseDemo;
import net.gbm.software.application.utils.BooleanValor;
import net.gbm.software.application.utils.Orden;
import net.gbm.software.infraestructure.provider.BCCR.ProviderBCCR;
import net.gbm.software.infraestructure.provider.BCCR.implement.ImplementBCCR;
import net.gbm.software.infraestructure.provider.BCCR.model.TipoDeCambio;
import net.gbm.software.infraestructure.provider.aresep.ProviderAresep;
import net.gbm.software.infraestructure.provider.aresep.implement.ImplementAresep;
import net.gbm.software.infraestructure.provider.aresep.model.Cuenta;
import net.gbm.software.infraestructure.provider.contraloria.ProviderContraloria;
import net.gbm.software.infraestructure.provider.contraloria.implement.ImplementContraloria;
import net.gbm.software.domain.model.Product;
import net.gbm.software.domain.port.api.ProductPort;
import net.gbm.software.infraestructure.provider.contraloria.model.Presupuesto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Slf4j
public class ProductService implements ProductPort {
    /*
        private final ProductPort productPort;

        public ProductService(ProductPort productPort) {
            this.productPort = productPort;
        }*/
    private final ProviderContraloria contraloria;
    private final ProviderAresep aresep;
    private final ProviderBCCR bccr;


    public ProductService() {
        this.contraloria = new ImplementContraloria();
        this.aresep = new ImplementAresep();
        this.bccr = new ImplementBCCR();
    }

    @Override
    public List<ResponseDemo> processProduct(Product product) {
        List<ResponseDemo> responseDemos = new ArrayList<>();
        TipoDeCambio tipoDeCambio = bccr.processInfo(product);
        if (product.getContraloria().equals(BooleanValor.SI)) {
            contraloria.update();
            List<Presupuesto> presupuestos = contraloria.getPresupuestos();
            if (product.getOrden().equals(Orden.ASC)) {
                Collections.sort(presupuestos);
            } else {
                presupuestos.sort(Collections.reverseOrder());
            }
            List<ResponseDemo> temp = new ArrayList<>();
            for (Presupuesto presupuesto : presupuestos) {
                if (temp.size() >= product.getTop()) {
                    break;
                }
                if (presupuesto.getPresupuesto() > product.getMonto()) {
                    ResponseDemo r = new ResponseDemo();
                    r.setInstitucion("Contraloria");
                    r.setDetalle(presupuesto.getDescripcion());
                    r.setTipoDeCambio(tipoDeCambio.getValor());
                    r.setMontoPresupesto(presupuesto.getPresupuesto());
                    r.setMontoPresupuestoDolares((presupuesto.getPresupuesto()) / tipoDeCambio.getValor());
                    r.setMontoEjecutado(presupuesto.getPresupuestoEjecutado());
                    r.setMontoEjecucionDolares((presupuesto.getPresupuestoEjecutado()) / tipoDeCambio.getValor());
                    temp.add(r);
                }
            }
            responseDemos.addAll(temp);
        }
        if (product.getAresep().equals(BooleanValor.SI)) {
            try {
                aresep.update();
            } catch (Exception e) {
                ResponseDemo r = new ResponseDemo();
                r.setDetalle("ERROR");
                r.setInstitucion(e.getMessage());
                responseDemos.add(r);
                return responseDemos;
            }
            List<Cuenta> cuentas = aresep.getInfo();
            List<ResponseDemo> temp = new ArrayList<>();
            if (product.getOrden().equals(Orden.ASC)) {
                Collections.sort(cuentas);
            } else {
                cuentas.sort(Collections.reverseOrder());
            }
            for (Cuenta cuenta : cuentas) {
                if (temp.size() >= product.getTop()) {
                    break;
                }
                if (cuenta.getOriginal() > product.getMonto()) {
                    ResponseDemo r = new ResponseDemo();
                    r.setDetalle(cuenta.getDescripcionCuenta());
                    r.setInstitucion("Aresep");
                    r.setTipoDeCambio(tipoDeCambio.getValor());
                    r.setMontoPresupesto(cuenta.getOriginal());
                    r.setMontoPresupuestoDolares((cuenta.getOriginal()) / tipoDeCambio.getValor());
                    r.setMontoEjecutado(cuenta.getTotalEgresos());
                    r.setMontoEjecucionDolares((cuenta.getTotalEgresos()) / tipoDeCambio.getValor());
                    temp.add(r);
                }
            }
            responseDemos.addAll(temp);
        }
        return responseDemos;
    }
}