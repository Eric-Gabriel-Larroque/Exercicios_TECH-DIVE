package org.techdive.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.techdive.model.dto.AlunoDTO;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.techdive.utils.EntityCreationHandler.criarAlunoDTO;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AlunosIT {

    private ObjectMapper mapper = new ObjectMapper();

    private static int matricula = 0;

    @BeforeAll
    public static void preCondicao() {
        baseURI = "http://localhost";
        port = 8080;
        basePath = "/cadastroAlunosCursos-1.0-SNAPSHOT/api";
    }

    @Test
    @Order(1)
    public void criarAluno() throws JsonProcessingException {
        AlunoDTO alunoDTO = criarAlunoDTO();

        matricula = given()
                .contentType(ContentType.JSON)
                .body(mapper.writeValueAsString(alunoDTO))
                .when()
                .post("/alunos")
                .then()
                .statusCode(201)
                .body("matricula", notNullValue())
                .body("nome", notNullValue())
                .extract()
                .path("matricula");
    }

    @Test
    @Order(2)
    public void consultarAlunoPorId() {
        given()
                .when()
                .get("/alunos/{alunoId}",matricula)
                .then()
                .statusCode(200)
                .body("matricula",notNullValue())
                .body("matricula",is(matricula))
                .body("nome",not(empty()));
    }

    @Test
    @Order(3)
    public void deletarAluno() {
        given()
                .when()
                .delete("/alunos/{alunoId}",matricula)
                .then()
                .statusCode(204);
    }
}
