package org.techdive.controller;

import org.techdive.model.dto.InscricaoRequestDTO;
import org.techdive.model.dto.InscricaoResponseDTO;
import org.techdive.service.InscricaoService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/inscricoes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InscricaoController {

    @Inject
    private InscricaoService inscricaoService;

    @POST
    public Response inserirInscricao(@Valid InscricaoRequestDTO inscricaoRequestDTO) {
        InscricaoResponseDTO inscricaoCriada = inscricaoService.inserirInscricao(inscricaoRequestDTO);
        return Response.ok(inscricaoCriada).build();
    }
}
