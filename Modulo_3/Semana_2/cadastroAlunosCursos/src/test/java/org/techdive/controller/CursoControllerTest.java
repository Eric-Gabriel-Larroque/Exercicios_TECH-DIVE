package org.techdive.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.techdive.exception.RegistroExistenteException;
import org.techdive.exception.RegistroNaoEncontradoException;
import org.techdive.model.dto.CursoAtualizacaoDTO;
import org.techdive.model.dto.CursoDTO;
import org.techdive.model.entity.Curso;
import org.techdive.service.CursoService;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.techdive.utils.EntityCreationHandler.criarCurso;

@ExtendWith(MockitoExtension.class)
class CursoControllerTest {

    @Mock
    private CursoService service;

    @InjectMocks
    private CursoController controller;

    private ModelMapper mapper = new ModelMapper();

    @Test
    @DisplayName("DADO requisição GET para obtenção de cursos, QUANDO não houverem cursos, DEVE me retornar uma lista vazia com status 200")
    void obterCursos_listaVazia() {
        Mockito.when(service.obterCursos(anyString(), anyInt())).thenReturn(new ArrayList<>());
        Response result = controller.obterCursos(anyString(),anyInt());
        assertNotNull(result);
        assertEquals(Response.Status.OK.getStatusCode(), result.getStatus());
    }

    @Test
    @DisplayName("DADO requisição GET para obtenção de cursos, QUANDO houverem cursos, DEVE me retornar uma lista de cursos com status 200")
    void obterCursos_sucesso() {
        CursoDTO cursoDTO = mapper.map(criarCurso(),CursoDTO.class);
        Mockito.when(service.obterCursos(anyString(), anyInt())).thenReturn(Arrays.asList(cursoDTO));
        Response result = controller.obterCursos(anyString(),anyInt());
        assertNotNull(result);
        assertEquals(Response.Status.OK.getStatusCode(), result.getStatus());
        List<CursoDTO> lista =  (List<CursoDTO>) result.getEntity();
        assertFalse(lista.isEmpty());
    }

    @Test
    @DisplayName("DADO a requisição GET pelo codigo do Curso, QUANDO não houver curso com o codigo indicado, DEVE me retornar eceção com status 404")
    void obterCursoPorCodigo_codigoInexistente() {
        Mockito.when(service.obterCursoPorCodigo(anyString())).thenThrow(RegistroNaoEncontradoException.class);
        assertThrows(RegistroNaoEncontradoException.class,() -> controller.obterCursoPorCodigo(anyString()));
    }

    @Test
    @DisplayName("DADO a requisição GET pelo codigo do Curso, QUANDO houver curso com o codigo indicado, DEVE me retornar o Curso com status 200")
    void obterCursoPorCodigo_sucesso() {
        Curso curso = criarCurso();
        Mockito.when(service.obterCursoPorCodigo(anyString())).thenReturn(mapper.map(curso,CursoDTO.class));
        Response result = controller.obterCursoPorCodigo(anyString());
        assertNotNull(result);
        assertEquals(result.getStatus(),Response.Status.OK.getStatusCode());
        assertInstanceOf(CursoDTO.class,result.getEntity());
    }

    @Test
    @DisplayName("DADO a requisição POST para criação de curso, QUANDO inserir um codigo já existente, DEVE me retornar uma exceção com Status 409")
    void inserirCurso_codigoExistente() {
        CursoDTO cursoDTO = mapper.map(criarCurso(),CursoDTO.class);
        Mockito.when(service.inserirCurso(cursoDTO)).thenThrow(RegistroExistenteException.class);
        assertThrows(RegistroExistenteException.class,() -> controller.inserirCurso(cursoDTO));
    }

    @Test
    @DisplayName("DADO a requisição POST para criação de curso, QUANDO inserir um codigo inexistente, DEVE me retornar o curso criado com status 201")
    void inserirCurso_sucesso() {
        CursoDTO cursoDTO = mapper.map(criarCurso(),CursoDTO.class);
        Mockito.when(service.inserirCurso(cursoDTO)).thenReturn(cursoDTO);
        Response result = controller.inserirCurso(cursoDTO);

        assertNotNull(result);
        assertInstanceOf(CursoDTO.class,result.getEntity());
        assertEquals(result.getStatus(),Response.Status.CREATED.getStatusCode());
    }

    @Test
    @DisplayName("DADO a requisição PUT para atualização de curso, QUANDO inserir um código inexistente no path, DEVE me retornar uma exceção com status 404")
    void atualizarCurso_codigoInexistente() {
        CursoAtualizacaoDTO cursoAtualizacaoDTO = mapper.map(criarCurso(),CursoAtualizacaoDTO.class);
        Mockito.when(service.atualizarCurso(cursoAtualizacaoDTO)).thenThrow(RegistroNaoEncontradoException.class);
        assertThrows(RegistroNaoEncontradoException.class, () -> controller.atualizarCurso(criarCurso().getCodigo(),cursoAtualizacaoDTO));
    }


    @Test
    @DisplayName("DADO a requisição PUT para atualização de curso, QUANDO inserir um código existente no path, DEVE me retornar o Curso com status 200")
    void atualizarCurso_sucesso() {
        CursoAtualizacaoDTO cursoAtualizacaoDTO = mapper.map(criarCurso(),CursoAtualizacaoDTO.class);
        Mockito.when(service.atualizarCurso(cursoAtualizacaoDTO)).thenReturn(mapper.map(cursoAtualizacaoDTO,CursoDTO.class));

        Response result = controller.atualizarCurso(criarCurso().getCodigo(),cursoAtualizacaoDTO);

        assertNotNull(result);
        assertEquals(cursoAtualizacaoDTO.getCodigo(),criarCurso().getCodigo());
        assertInstanceOf(CursoDTO.class,result.getEntity());
    }

    @Test
    @DisplayName("DADO a requisição DELETE, QUANDO inserir um codigo ao Path inexsitente, DEVE me retornar uma exceção com status 404")
    void deletarCurso_codigoInexistente() {
        Mockito.doThrow(RegistroNaoEncontradoException.class).when(service).deletarCurso(anyString());
        assertThrows(RegistroNaoEncontradoException.class, () -> controller.deletarCurso(anyString()));
    }

    @Test
    @DisplayName("DADO a requisição DELETE, QUANDO inserir um codigo ao Path existente, DEVE me retornar o body vazio com status 204")
    void deletarCurso_sucesso() {
        Response result = controller.deletarCurso(anyString());
        assertNull(result.getEntity());
        assertEquals(result.getStatus(),Response.Status.NO_CONTENT.getStatusCode());
        assertDoesNotThrow(() -> controller.deletarCurso(anyString()));
    }
}