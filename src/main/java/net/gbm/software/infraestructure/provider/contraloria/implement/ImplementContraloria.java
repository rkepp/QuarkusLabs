package net.gbm.software.infraestructure.provider.contraloria.implement;

import net.gbm.software.domain.model.Product;
import net.gbm.software.infraestructure.provider.contraloria.ProviderContraloria;
import net.gbm.software.infraestructure.provider.contraloria.model.Presupuesto;
import net.gbm.software.infraestructure.provider.contraloria.restClient.ClientContraloria;

import java.util.List;

public class ImplementContraloria implements ProviderContraloria {

    private final ClientContraloria client;

    public ImplementContraloria() {
        this.client = new ClientContraloria();
    }

    @Override
    public void update() {
        client.updatePresupuestos();
    }

    @Override
    public List<Presupuesto> getPresupuestos() {
        return client.getPresupuestos();
    }
}
