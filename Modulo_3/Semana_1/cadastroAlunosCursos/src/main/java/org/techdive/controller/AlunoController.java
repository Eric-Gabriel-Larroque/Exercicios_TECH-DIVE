package org.techdive.controller;

import org.techdive.exception.RegistroNaoEncontradoException;
import org.techdive.model.dto.AlunoDTO;
import org.techdive.service.AlunoService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/alunos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AlunoController {

    @Inject
    private AlunoService alunoService;

    @GET
    public Response obterAlunos(@QueryParam("nome") String nome) {
        try {
            List<AlunoDTO> alunos = alunoService.obterAlunos(nome);
            return Response.ok(alunos).build();
        } catch (RegistroNaoEncontradoException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{matricula}")
    public Response obterAlunoPorMatricula(@PathParam("matricula") int matricula) {
        return null;
    }


}
