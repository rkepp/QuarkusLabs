package net.gbm.software.infraestructure.provider.BCCR.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoDeCambio {
    @NotEmpty
    LocalDate fecha;
    @NotEmpty
    double valor;
}
