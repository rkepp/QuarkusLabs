package net.gbm.software.infraestructure.provider.aresep.restclient;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import net.gbm.software.infraestructure.provider.aresep.model.Cuenta;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ClientAresep {
    private List<Cuenta> info;

    public ClientAresep() {
        this.info = new ArrayList<>();
    }

    public void update() {
        String body = "";
        try {
            String url = ("https://web.aresep.go.cr/ws.datosabiertos/Services/DF/InformacionPresupuestaria.svc/ObtenerInformacionEgresosPresupuesto/2020/1/1");
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());
            body = response.body();
            System.out.println("Aresep Link AOK.");

        } catch (InterruptedException | IOException e) {
            System.out.println("Error : Using Backup Aresep File.");
            try {
                body = Files.readString(Path.of("src/main/resources/Aresep Backup/Aresep.json"));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNodeRoot = null;
            jsonNodeRoot = mapper.readTree(body);
            JsonNode jsonNodeValues = jsonNodeRoot.get("value");
            this.info = mapper.readValue(jsonNodeValues.traverse(), new TypeReference<List<Cuenta>>(){});

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
