package net.gbm.software.infraestructure.provider.BCCR.soapClient;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.path.xml.XmlPath;
import io.smallrye.common.constraint.Assert;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
@QuarkusTest
public class TestBCCRProvider {

   @Test
    public void testBCCR() throws IOException {
        File soapRequestFile = new File("src/main/resources/BccrRequest/request.xml");

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost request = new HttpPost("https://gee.bccr.fi.cr/Indicadores/Suscripciones/WS/wsindicadoreseconomicos.asmx");
        request.addHeader("Content-Type","text/xml");
        request.setEntity(new InputStreamEntity(new FileInputStream(soapRequestFile)));

        CloseableHttpResponse response = client.execute(request);
        int statuscode = response.getStatusLine().getStatusCode();
        Assert.assertTrue(200==statuscode);

        String responseString = EntityUtils.toString(response.getEntity());
        XmlPath xmlpath = new XmlPath(responseString);
        String exchangeRate = xmlpath.getString("");
        Assert.assertTrue(exchangeRate.contains("587.24000000"));
    }
}
