package dive.tech.projeto.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/sistema")
public class SistemaController {

    private static List<Cookie> cookies = new ArrayList<>();

    @GET
    @Path("/cabecalho")
    @Consumes("multipart/form-data")
    @Produces("application/json")
    public Response obterCabecalhos(@Context HttpServletRequest httpServletRequest) {

        String headers = "";

        try {
            headers+=httpServletRequest
                    .getReader()
                    .lines()
                    .collect(Collectors.joining("\n"));
            return  Response
                    .ok(headers)
                    .build();
        }catch (IOException e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @GET
    @Path("/cookie")
    @Produces("application/json")
    public Response obterCookies() {

        if(cookies.size()==0){
            criarCookie("CookieCriadoManualmente");
        }

        return Response
                .ok(cookies)
                .build();
    }

    @POST
    @Path("/cookie")
    @Consumes("application/json")
    @Produces("application/json")
    public Response criarCookie(@NotEmpty String valorDoCookie){

        Cookie cookie = new Cookie("MeuCookie", valorDoCookie);
        cookies.add(cookie);

        return Response
                .ok(cookie)
                .status(201)
                .build();
    }
}