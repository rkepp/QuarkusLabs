package net.gbm.software.infraestructure.provider.BCCR.implement;

import net.gbm.software.domain.model.Product;
import net.gbm.software.infraestructure.provider.BCCR.ProviderBCCR;
import net.gbm.software.infraestructure.provider.BCCR.model.TipoDeCambio;
import net.gbm.software.infraestructure.provider.BCCR.soapClient.ClientBCCR;

import java.time.format.DateTimeFormatter;

public class ImplementBCCR implements ProviderBCCR {

    private final ClientBCCR client;

    public ImplementBCCR() {
        this.client = new ClientBCCR();
    }

    @Override
    public TipoDeCambio processInfo(Product product) {
        client.update(product.getFechaTC().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        return client.getTipoDeCambio();
    }
}
