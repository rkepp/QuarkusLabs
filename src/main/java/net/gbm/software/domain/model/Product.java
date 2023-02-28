package net.gbm.software.domain.model;

import lombok.Getter;
import lombok.ToString;
import net.gbm.software.application.data.RequestDemo;
import net.gbm.software.application.utils.BooleanValor;
import net.gbm.software.application.utils.Orden;

import java.time.LocalDate;

@Getter
@ToString
public class Product {

    private BooleanValor contraloria;
    private BooleanValor aresep;
    private LocalDate fechaTC;
    private Orden orden;
    private Integer top;
    private Integer monto;

    public Product(RequestDemo requestDemo) {

        this.contraloria = requestDemo.getContraloria();
        this.aresep = requestDemo.getAresep();
        this.fechaTC = requestDemo.getFechaTC();
        this.orden = requestDemo.getOrden();
        this.top = requestDemo.getTop();
        this.monto = requestDemo.getMonto();
    }

    public Product() {

    }

    public void setFechaTC(LocalDate fechaTC) {
        this.fechaTC = fechaTC;
    }
}
