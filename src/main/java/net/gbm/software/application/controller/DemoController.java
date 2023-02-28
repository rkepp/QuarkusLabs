package net.gbm.software.application.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.gbm.software.application.data.RequestDemo;
import net.gbm.software.application.handler.DemoHandler;
import net.gbm.software.application.data.ResponseDemo;
import net.gbm.software.application.utils.BooleanValor;
import net.gbm.software.application.utils.Orden;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Slf4j
@Path("/demo/backend")
@Produces(MediaType.APPLICATION_JSON)
@AllArgsConstructor
public class DemoController {

    /*private final DemoHandler demoHandler;

    public DemoController(DemoHandler demoHandler){this.demoHandler = demoHandler; }*/
    @GET
    @APIResponse(responseCode = "200", description = "Get consult",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(type = SchemaType.ARRAY, implementation = ResponseDemo.class)))
    @APIResponse(responseCode = "400", description = "Invalid parameters")
    @APIResponse(responseCode = "500", description = "Internal server error")

    public Response getResponse(@NotNull @Valid @QueryParam("Contraloria") String contraloria, @NotNull @Valid @QueryParam("Aresep") String aresep,
                                @NotNull @Valid @QueryParam("FechaTC") String fechaTc, @NotNull @Valid @QueryParam("Orden") String orden,
                                @NotNull @Valid @QueryParam("Top") Integer top, @NotNull @Valid @QueryParam("Monto") Integer monto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        LocalDate fecha = LocalDate.parse(fechaTc, formatter);

        RequestDemo request = new RequestDemo();
        request.setContraloria(BooleanValor.valueOf(contraloria));
        request.setAresep(BooleanValor.valueOf(aresep));
        request.setFechaTC(fecha);
        request.setOrden(Orden.valueOf(orden));
        request.setTop(top);
        request.setMonto(monto);

        Map<String, Object> responseGBM = new HashMap<>();

        DemoHandler demo = new DemoHandler();
        List<ResponseDemo> r = demo.execute(request);
        List<ResponseDemo> objResponse = new ArrayList<>(r);

        responseGBM.put("respuestaGBM", objResponse);

        return Response.ok(responseGBM).build();//(List<ResponseDemo>) demoHandler.execute(request);
    }
}