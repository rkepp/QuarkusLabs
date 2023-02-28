package net.gbm.software.infraestructure.adapter;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

public class AdapterTest {

    // @Test
    // public void testContraloria() {
    //     given()
    //             .when().get("/demo/backend?Contraloria=SI&Aresep=NO&FechaTC=11/11/2020&Orden=ASC&Top=1&Monto=100000")
    //             .then()
    //             .statusCode(200)
    //             .body(is("{\"respuestaGBM\":[{\"detalle\":\"SERVICIO DE CORREO\",\"institucion\":\"Contraloria\",\"montoEjecucionDolares\":63.83259268870219,\"montoEjecutado\":39357.9,\"montoPresupesto\":103920.0,\"montoPresupuestoDolares\":168.54260598786854,\"tipoDeCambio\":616.58}]}"));
    // }

    // @Test
    // public void testAresep() {
    //     given()
    //             .when().get("/demo/backend?Contraloria=NO&Aresep=SI&FechaTC=11/11/2022&Orden=ASC&Top=1&Monto=100000")
    //             .then()
    //             .statusCode(200)
    //             .body(is("{\"respuestaGBM\":[{\"detalle\":\"Otros Productos Farmacéuticos y Medicinales\",\"institucion\":\"Aresep\",\"montoEjecucionDolares\":0.0,\"montoEjecutado\":0.0,\"montoPresupesto\":120000.0,\"montoPresupuestoDolares\":195.18225142727024,\"tipoDeCambio\":614.81}]}"));
    // }
}
