package net.gbm.software.infraestructure.provider.aresep.implement;

import net.gbm.software.infraestructure.provider.aresep.ProviderAresep;
import net.gbm.software.infraestructure.provider.aresep.model.Cuenta;
import net.gbm.software.infraestructure.provider.aresep.restclient.ClientAresep;

import java.util.List;

public class ImplementAresep implements ProviderAresep {

    private final ClientAresep client;

    public ImplementAresep() {
        this.client = new ClientAresep();
    }

    @Override
    public List<Cuenta> getInfo() {
        return client.getInfo();
    }
    @Override
    public void update() {
        client.update();
    }
}
