package org.techdive.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.techdive.model.dto.InscricaoRequestDTO;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.techdive.utils.EntityCreationHandler.criarInscricaoRequestDTO;

public class InscricoesIT {

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeAll
    public static void preCondicao() {
        baseURI = "http://localhost";
        port = 8080;
        basePath = "/cadastroAlunosCursos-1.0-SNAPSHOT/api";
    }

    @Test
    public void inserirInscricao() throws JsonProcessingException {
        InscricaoRequestDTO inscricao = criarInscricaoRequestDTO();
        given().
                contentType(ContentType.JSON)
                .body(mapper.writeValueAsString(inscricao))
                .when()
                .post("/inscricoes")
                .then()
                .statusCode(201)
                .body("codigo",not(blankString()))
                .body("id",notNullValue())
                .body("matricula", notNullValue());
    }

}