package net.gbm.software.application.data;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.gbm.software.application.utils.BooleanValor;
import net.gbm.software.application.utils.Orden;

import javax.inject.Singleton;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Slf4j
@Singleton
@Data
public class RequestDemo {

    @NotEmpty
    private BooleanValor Contraloria;
    @NotEmpty
    private BooleanValor Aresep;
    @NotEmpty
    private LocalDate FechaTC;
    @NotEmpty
    private Orden Orden;
    @NotEmpty
    private Integer Top;
    @NotEmpty
    private Integer Monto;
}