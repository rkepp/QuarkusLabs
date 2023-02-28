package net.gbm.software.infraestructure.provider.contraloria.restClient;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.Getter;
import net.gbm.software.infraestructure.provider.contraloria.model.Presupuesto;
import org.apache.commons.io.FileUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ClientContraloria {
    private List<Presupuesto> presupuestos;
    @ConfigProperty (name = "ContraloriaURL")
    private String ContraloriaURL;
    public ClientContraloria() {
        this.presupuestos = new ArrayList<Presupuesto>();
    }

    public void updatePresupuestos() {
        try {
            URL url = new URL("https://cgrfiles.cgr.go.cr/publico/docsweb/documentos/cgr-transp/datos-abiertos/presupuesto-cgr/da-presupuesto-19-diciembre-2022.csv");
            System.out.println(ContraloriaURL);
            File csvFile =  new File("temp.csv");;
            FileUtils.copyURLToFile(url, csvFile);
            CsvMapper csvMapper = new CsvMapper();
            CsvSchema csvSchema = csvMapper
                    .typedSchemaFor(Presupuesto.class)
                    .withHeader()
                    .withColumnSeparator(',')
                    .withComments();
            MappingIterator<Presupuesto> presupuestoMappingIterator = null;
            try {
                presupuestoMappingIterator = csvMapper
                        .readerWithTypedSchemaFor(Presupuesto.class)
                        .with(csvSchema)
                        .readValues(csvFile);
                this.presupuestos = presupuestoMappingIterator.readAll();
                if (csvFile.exists()) {
                    csvFile.delete();
                } else {
                    System.out.println("File not found");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}