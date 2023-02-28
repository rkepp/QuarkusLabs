package net.gbm.software.infraestructure.provider.aresep.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Cuenta implements Comparable<Cuenta>{
    @NotEmpty
    private String descripcionCuenta;
    @NotEmpty
    private double original;
    @NotEmpty
    private double totalEgresos;


    @Override
    public int compareTo(Cuenta o) {
        return Double.compare(this.original, o.original);
    }
}
