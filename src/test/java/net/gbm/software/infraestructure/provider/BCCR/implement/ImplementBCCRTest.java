package net.gbm.software.infraestructure.provider.BCCR.implement;

import net.gbm.software.domain.model.Product;
import net.gbm.software.infraestructure.provider.BCCR.model.TipoDeCambio;
import net.gbm.software.infraestructure.provider.BCCR.soapClient.ClientBCCR;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalDate;
import static org.mockito.Mockito.when;

class ImplementBCCRTest {

    @Test
    void shouldObtainTipoDeCambio() {

        ClientBCCR clientB = Mockito.mock(ClientBCCR.class);
        ImplementBCCR implementBCCR = new ImplementBCCR();
        Product product = new Product();
        product.setFechaTC(LocalDate.now());
        TipoDeCambio tc = new TipoDeCambio();
        tc.setFecha(LocalDate.now());
        tc.setValor(593.79);
        when(clientB.getTipoDeCambio()).thenReturn(tc);
        tc = implementBCCR.processInfo(product);

    }
}