package org.techdive.controller;

import org.techdive.model.dto.CursoDTO;
import org.techdive.service.CursoService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/cursos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CursoController {

    @Inject
    private CursoService cursoService;

    @GET
    public Response obterCursos(@QueryParam("sortBy") String sortBy, @QueryParam("limit") int limit) {
            List<CursoDTO> cursos = cursoService.obterCursos(sortBy,limit);
            return Response.ok(cursos).build();
    }

    @GET
    @Path("/{codigo}")
    public Response obterCursoPorCodigo(@PathParam("codigo") String codigo) {
        CursoDTO cursoEncontrado = cursoService.obterCursoPorCodigo(codigo);
        return Response.ok(cursoEncontrado).build();
    }

    @POST
    public Response inserirCurso(@Valid CursoDTO cursoDTO) {
        CursoDTO cursoCriado = cursoService.inserirCurso(cursoDTO);
        return Response.ok(cursoCriado).build();
    }

    @PUT
    @Path("/{codigo}")
    public Response atualizarCurso(@PathParam("codigo") String codigo, @Valid CursoDTO cursoDTO) {
        cursoDTO.setCodigo(codigo);
        CursoDTO cursoAtualizado = cursoService.atualizarCurso(cursoDTO);
        return Response.ok(cursoAtualizado).build();
    }

    @DELETE
    @Path("/{codigo}")
    public Response deletarCurso(@PathParam("codigo") String codigo) {
        cursoService.deletarCurso(codigo);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
