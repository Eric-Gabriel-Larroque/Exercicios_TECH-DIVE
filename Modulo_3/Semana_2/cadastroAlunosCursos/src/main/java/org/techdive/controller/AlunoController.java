package org.techdive.controller;

import org.techdive.model.dto.AlunoAtualizacaoDTO;
import org.techdive.model.dto.AlunoDTO;
import org.techdive.service.AlunoService;

import javax.inject.Inject;
import javax.validation.Valid;
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
        List<AlunoDTO> alunos = alunoService.obterAlunos(nome);
        return Response.ok(alunos).build();
    }

    @GET
    @Path("/{matricula}")
    public Response obterAlunoPorMatricula(@PathParam("matricula") int matricula) {
        AlunoDTO alunoDTO = alunoService.obterAlunoPorMatricula(matricula);
        return Response.ok(alunoDTO).build();
    }

    @POST
    public Response inserirAluno(@Valid AlunoDTO alunoDTO) {
        AlunoDTO alunoCriado = alunoService.inserirAluno(alunoDTO);
        return Response.status(Response.Status.CREATED).entity(alunoCriado).build();
    }

    @PUT
    @Path("/{matricula}")
    public Response atualizarAluno(@PathParam("matricula") Integer matricula, @Valid AlunoAtualizacaoDTO alunoAtualizacaoDTO) {
        alunoAtualizacaoDTO.setMatricula(matricula);
        AlunoDTO alunoAtualizado = alunoService.atualizarAluno(alunoAtualizacaoDTO);
        return Response.ok(alunoAtualizado).build();
    }

    @DELETE
    @Path("/{matricula}")
    public Response deletarAluno(@PathParam("matricula") Integer matricula) {
        alunoService.deletarAluno(matricula);
        return Response.noContent().build();
    }

}