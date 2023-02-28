package net.gbm.software.infraestructure.provider.contraloria.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@JsonPropertyOrder({"codigo", "descripcion", "presupuesto", "presupuestoEjecutado"})
public class Presupuesto implements Comparable<Presupuesto>{
    @NotEmpty
    private String codigo;
    @NotEmpty
    private String descripcion;
    @NotEmpty
    private double presupuesto;
    @NotEmpty
    private double presupuestoEjecutado;

    @Override
    public int compareTo(Presupuesto o) {
        return Double.compare(this.presupuesto, o.presupuesto);
    }

}
