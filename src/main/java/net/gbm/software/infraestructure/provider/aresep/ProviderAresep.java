package net.gbm.software.infraestructure.provider.aresep;

import net.gbm.software.infraestructure.provider.aresep.model.Cuenta;

import java.util.List;

public interface ProviderAresep {

    List<Cuenta> getInfo();
    void update();
}
