package dive.tech.projeto.controller;

import dive.tech.projeto.model.entity.Curso;
import dive.tech.projeto.service.CursoService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/curso")
public class CursoController {

    private CursoService cursoService = new CursoService();
    @GET
    public Response listarCursos() {

        try {
            List<Curso> cursos  = cursoService.obterCursos();
            return Response
                    .ok(cursos)
                    .build();
        }catch (Exception e) {
            return  Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response criarCurso(Curso curso) {
        Curso cursoCriado = cursoService.criarCurso(curso);

        try {
            return Response
                    .ok(cursoCriado)
                    .status(201)
                    .build();

        }catch (Exception e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }
}
