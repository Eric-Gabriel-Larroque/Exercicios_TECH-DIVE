package org.techdive.controller;

import org.techdive.exception.RegistroExistenteException;
import org.techdive.exception.RegistroNaoEncontradoException;
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
        try {
            AlunoDTO alunoDTO = alunoService.obterAlunoPorMatricula(matricula);
            return Response.ok(alunoDTO).build();
        } catch (RegistroNaoEncontradoException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @POST
    public Response inserirAluno(@Valid AlunoDTO alunoDTO) {
        try {
             AlunoDTO alunoCriado = alunoService.inserirAluno(alunoDTO);
            return Response.ok(alunoCriado).build();
        } catch (RegistroExistenteException e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{matricula}")
    public Response atualizarAluno(@PathParam("matricula") Integer matricula, @Valid AlunoAtualizacaoDTO alunoAtualizacaoDTO) {
        try {
            alunoAtualizacaoDTO.setMatricula(matricula);
            AlunoDTO alunoAtualizado = alunoService.atualizarAluno(alunoAtualizacaoDTO);
            return Response.ok(alunoAtualizado).build();
        } catch (RegistroExistenteException e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        } catch (RegistroNaoEncontradoException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{matricula}")
    public Response deletarAluno(@PathParam("matricula") Integer matricula) {
        try {
            alunoService.deletarAluno(matricula);
            return Response.noContent().build();
        } catch (RegistroNaoEncontradoException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }
    }

}
