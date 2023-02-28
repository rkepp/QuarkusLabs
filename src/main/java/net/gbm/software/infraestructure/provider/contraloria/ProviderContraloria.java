package net.gbm.software.infraestructure.provider.contraloria;


import net.gbm.software.domain.model.Product;
import net.gbm.software.infraestructure.provider.contraloria.model.Presupuesto;

import java.util.List;

public interface ProviderContraloria {
    void update();
    List<Presupuesto> getPresupuestos();
}
