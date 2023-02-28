package net.gbm.software.infraestructure.adapter;

import net.gbm.software.domain.model.Product;
import net.gbm.software.domain.port.spi.ProductIntegrationPort;
import net.gbm.software.infraestructure.provider.BCCR.ProviderBCCR;
import net.gbm.software.infraestructure.provider.aresep.ProviderAresep;
import net.gbm.software.infraestructure.provider.contraloria.ProviderContraloria;

public class ProductIntegrationAdapter implements ProductIntegrationPort {

    private final ProviderAresep providerAresep;
    private final ProviderBCCR providerBCCR;
    private final ProviderContraloria providerContraloria;

    public ProductIntegrationAdapter(ProviderAresep providerAresep, ProviderBCCR providerBCCR,ProviderContraloria providerContraloria) {
        this.providerAresep = providerAresep;
        this.providerBCCR = providerBCCR;
        this.providerContraloria = providerContraloria;
    }

    @Override
    public void processDemo(Product product) {
        providerBCCR.processInfo(product);
    }
}
