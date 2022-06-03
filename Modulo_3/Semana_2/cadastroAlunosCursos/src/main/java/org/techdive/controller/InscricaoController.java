package org.techdive.controller;

import org.techdive.model.dto.InscricaoRequestDTO;
import org.techdive.model.dto.InscricaoResponseDTO;
import org.techdive.service.InscricaoService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/inscricoes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InscricaoController {

    @Inject
    private InscricaoService inscricaoService;

    @GET
    public Response obterInscricoes() {
        List<InscricaoResponseDTO> inscricoesEncontradas = inscricaoService.obterInscricoes();
        return Response.ok(inscricoesEncontradas).build();
    }

    @POST
    public Response inserirInscricao(@Valid InscricaoRequestDTO inscricaoRequestDTO) {
        InscricaoResponseDTO inscricaoCriada = inscricaoService.inserirInscricao(inscricaoRequestDTO);
        return Response.status(Response.Status.CREATED).entity(inscricaoCriada).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletarInscricao(@PathParam("id") Integer id) {
        inscricaoService.deletarInscricao(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
