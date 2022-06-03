package org.techdive.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.techdive.model.dto.CursoDTO;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.techdive.utils.EntityCreationHandler.criarCursoDTO;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CursosIT {

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeAll
    public static void preCondicao() {
        baseURI = "http://localhost";
        port = 8080;
        basePath = "/cadastroAlunosCursos-1.0-SNAPSHOT/api";
    }

    @Test
    @Order(4)
    public void inserirCurso() throws JsonProcessingException {
        CursoDTO cursoDTO = criarCursoDTO();
        given()
                .contentType(ContentType.JSON)
                .body(mapper.writeValueAsString(cursoDTO))
                .when()
                .post("/cursos")
                .then()
                .statusCode(201)
                .body("codigo",not(blankString()))
                .body("assunto",not(blankString()))
                .body("duracao",notNullValue());
    }

}
