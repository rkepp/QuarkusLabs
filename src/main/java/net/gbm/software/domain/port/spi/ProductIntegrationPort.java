package net.gbm.software.domain.port.spi;

import net.gbm.software.domain.model.Product;

public interface ProductIntegrationPort {

    void processDemo(Product product);
}
