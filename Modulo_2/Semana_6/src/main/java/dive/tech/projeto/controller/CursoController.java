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
    @Produces("application/json")
    public Response obterCursos() {

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

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response obterCursoPeloId(@PathParam("id") Long id) {

        Curso cursoSelecionado = cursoService.obterCursoPeloId(id);

        if(cursoSelecionado==null) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }

        try {
            return Response
                    .ok(cursoSelecionado)
                    .build();
            }catch (Exception e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response criarCurso(Curso curso) {

        try {
            Curso cursoCriado = cursoService.criarCurso(curso);
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

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response atualizarCurso(Curso curso) {

        Curso cursoAtualizado = cursoService.atualizarCurso(curso);

        if(cursoAtualizado==null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }

        try{
            return Response
                    .ok(cursoAtualizado)
                    .build();
        }catch (Exception e) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces("application/json")
    public Response deletarCurso(@PathParam("id") Long id) {
        obterCursos();

        List<Curso> cursosRestantes = cursoService.deletarCurso(id);

        if(cursosRestantes==null) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }

        try {
            return Response
                    .ok(cursosRestantes)
                    .build();
        }catch (Exception e) {
            return Response
                    .status(404)
                    .entity(e.getMessage())
                    .build();
        }
    }

}
