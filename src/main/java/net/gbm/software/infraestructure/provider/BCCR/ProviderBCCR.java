package net.gbm.software.infraestructure.provider.BCCR;

import net.gbm.software.domain.model.Product;
import net.gbm.software.infraestructure.provider.BCCR.model.TipoDeCambio;

public interface ProviderBCCR {

    TipoDeCambio processInfo(Product product);
}
