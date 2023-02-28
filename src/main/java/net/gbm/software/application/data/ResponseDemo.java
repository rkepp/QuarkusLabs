package net.gbm.software.application.data;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ResponseDemo {
    @NotEmpty
    private String institucion;
    @NotEmpty
    private String detalle;
    private double montoPresupesto;
    private double montoEjecutado;
    @NotEmpty
    private double tipoDeCambio;
    private double montoPresupuestoDolares;
    private double montoEjecucionDolares;
}
