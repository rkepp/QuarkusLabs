package net.gbm.software.infraestructure.provider.BCCR.soapClient;

import lombok.Getter;
import net.gbm.software.infraestructure.provider.BCCR.model.TipoDeCambio;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Getter
public class ClientBCCR {
    private TipoDeCambio tipoDeCambio;
    public ClientBCCR() {
       this.tipoDeCambio = new TipoDeCambio();
    }
    public void update(String fecha) {
        try {
            String url = ("https://gee.bccr.fi.cr/Indicadores/Suscripciones/WS/wsindicadoreseconomicos.asmx/ObtenerIndicadoresEconomicosXML?Indicador=318&FechaInicio=" + fecha + "&FechaFinal=" + fecha + "&Nombre=Gera&SubNiveles=N&CorreoElectronico=gcerdas1293@gmail.com&Token=DAIMGO9A11");
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());
            Pattern valorPattern = Pattern.compile("NUM_VALOR&gt;(.*?)&lt;/NUM_VALOR");
            Matcher valorMatcher = valorPattern.matcher(response.body());
            double valor = 999999999;
            if (valorMatcher.find()) {
                valor = Double.parseDouble(valorMatcher.group(1));
            }
            this.tipoDeCambio = new TipoDeCambio(LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy")), valor);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
