package org.techdive.service;

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
import org.techdive.repository.CursoRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.techdive.utils.EntityCreationHandler.*;

@DisplayName("Testes CursoService")
@ExtendWith(MockitoExtension.class)
class CursoServiceTest {

    @Mock
    private CursoRepository repository;

    @InjectMocks
    private CursoService service;

    private ModelMapper mapper = new ModelMapper();

    @Test
    @DisplayName("DADO a requisição GET, QUANDO chamar para obter cursos e não tiver nenhum disponível, DEVE me retornar array vazio.")
    void obterCursos_arrayVazio() {

        //when
        Mockito.when(repository.obterCursos(anyString(), anyInt())).thenReturn(new ArrayList<>());
        List<CursoDTO> listaCursos = service.obterCursos(anyString(),anyInt());

        //then
        assertNotNull(listaCursos, "Deveria retornar uma lista não nula");
        assertTrue(listaCursos.isEmpty(),"Deveria retornar uma lista vazia");
    }

    @Test
    @DisplayName("DADO a requisição GET, QUANDO chamar para obter cursos e houverem cursos disponíveis, DEVE me retornar array de Cursos")
    void obterCursos_arrayPreenchido() {
        //given
        List<Curso> listaCursos = Arrays.asList(criarCurso(),criarCurso());

        //when
        Mockito.when(repository.obterCursos(anyString(),anyInt())).thenReturn(listaCursos);
        List<Curso> cursosObtidos = service.obterCursos(anyString(),anyInt()).stream().map(curso -> mapper.map(curso,Curso.class)).collect(Collectors.toList());

        //then
        assertNotNull(cursosObtidos,"Deveria retornar lista não nula de Cursos");
        assertFalse(cursosObtidos.isEmpty(),"Deveria me retornar uma lista não vazia de Cursos");
        assertEquals(listaCursos.size(),cursosObtidos.size());
    }

    @Test
    @DisplayName("DADO a requisição GET, QUANDO houverem cursos disponíveis E limite setado, DEVE me retornar array de Cursos com size igual ao valor do limite")
    void obterCursos_limiteSetado() {
        //given
        int limite = 2;
        List<Curso> listaCursos = criarListaCurso();

        //when
        Mockito.when(repository.obterCursos(null,limite)).thenReturn(listaCursos.stream().limit(limite).collect(Collectors.toList()));
        List<CursoDTO> cursosObtidos = service.obterCursos(null,limite);

        //then
        assertNotNull(cursosObtidos,"Deveria retornar lista não nula de Cursos");
        assertFalse(cursosObtidos.isEmpty(),"Deveria me retornar uma lista não vazia de Cursos");
        assertNotEquals(listaCursos.size(),cursosObtidos.size());
        assertEquals(limite,cursosObtidos.size());
    }

    @Test
    @DisplayName("DADO a requisição POST, QUANDO chamada e o código do curso já existir, DEVE retornar uma exceção")
    void inserirCurso_CodigoExistente() {
        //given
        Curso curso = criarCurso();
        //when
        Mockito.when(repository.obterCursoPeloCodigo(anyString())).thenReturn(Optional.of(curso));
        //then
        CursoDTO cursoDTO = mapper.map(curso,CursoDTO.class);
        assertThrows(RegistroExistenteException.class, () -> service.inserirCurso(cursoDTO));
    }

    @Test
    @DisplayName("DADO a requisição POST, QUANDO chamada, DEVE retornar o Curso criado." )
    void inserirCurso_sucesso() {
        //given
        Curso curso = criarCurso();

        //when
        Mockito.when(repository.obterCursoPeloCodigo(anyString())).thenReturn(Optional.empty());

        CursoDTO cursoDTO = mapper.map(curso,CursoDTO.class);
        CursoDTO cursoCriado = service.inserirCurso(cursoDTO);

        //then
        assertNotNull(cursoCriado);
        assertInstanceOf(CursoDTO.class,cursoCriado);
        assertNotNull(cursoCriado.getCodigo());
    }

    @Test
    @DisplayName("DADO a requisição GET por ID, QUANDO inserir código inexistente, DEVE me retornar uma exceção")
    void obterCursoPorCodigo_CodigoInexistente() {
        Mockito.when(repository.obterCursoPeloCodigo(anyString())).thenReturn(Optional.empty());
        assertThrows(RegistroNaoEncontradoException.class,() -> service.obterCursoPorCodigo("codigo"));
    }

    @Test
    @DisplayName("DADO a requisição GET por ID, QUANDO inserir código existente, DEVE me retornar o Curso com o código inserido")
    void obterCursoPorCodigo_sucesso() {
        //given
        Curso curso = criarCurso();
        Mockito.when(repository.obterCursoPeloCodigo(anyString())).thenReturn(Optional.of(curso));

        //when
        CursoDTO cursoEncontrado = service.obterCursoPorCodigo("codigo");
        assertNotNull(cursoEncontrado);
        assertInstanceOf(CursoDTO.class,cursoEncontrado);
        assertEquals(cursoEncontrado.getCodigo(),curso.getCodigo());
    }

    @Test
    @DisplayName("DADO a requisição PUT, QUANDO inserir um codigo inexistente, DEVE me retornar uma exceção")
    void atualizarCurso_CodigoInexistente() {
        //given
        Curso curso = criarCurso();
        CursoAtualizacaoDTO atualizacaoDTO = criarCursoAtualizacaoDTO();
        atualizacaoDTO.setCodigo("codigo 2");
        //when
        Mockito.when(repository.obterCursoPeloCodigo(anyString())).thenReturn(Optional.of(curso));
        Mockito.doThrow(new RegistroNaoEncontradoException("Curso","codigo")).when(repository).atualizarCurso(Mockito.any(Curso.class));
        //then
        assertThrows(RegistroNaoEncontradoException.class,() -> service.atualizarCurso(atualizacaoDTO));
    }

    @Test
    @DisplayName("DADO a requisição PUT, QUANDO inserir um codigo existente, DEVE me retornar o Curso atualizado")
    void atualizarCurso_sucesso() {
        //given
        Curso curso = criarCurso();
        //when
        Mockito.when(repository.obterCursoPeloCodigo(anyString())).thenReturn(Optional.of(curso));
        CursoDTO cursoEncontrado = service.obterCursoPorCodigo(curso.getCodigo());
        CursoAtualizacaoDTO atualizacaoDTO = criarCursoAtualizacaoDTO();
        atualizacaoDTO.setCodigo(cursoEncontrado.getCodigo());


        CursoDTO cursoAtualizado = service.atualizarCurso(atualizacaoDTO);
        //then
        assertNotNull(cursoEncontrado);
        assertNotNull(cursoAtualizado.getCodigo());
        assertNotNull(cursoAtualizado);
        assertEquals(cursoAtualizado.getDuracao(),atualizacaoDTO.getDuracao());
        assertEquals(cursoAtualizado.getAssunto(),atualizacaoDTO.getAssunto());
        assertEquals(cursoAtualizado.getCodigo(),curso.getCodigo());
        assertInstanceOf(CursoDTO.class,cursoAtualizado);

    }

    @Test
    @DisplayName("DADO a requisição DELETE, QUANDO chamada para deleção de Curso e não houver curso com o código digitado, DEVE retornar uma exceção")
    public void removerCurso_CodigoInexitente() {
        Mockito.when(repository.obterCursoPeloCodigo(anyString())).thenThrow(RegistroNaoEncontradoException.class);
        assertThrows(RegistroNaoEncontradoException.class, () -> service.deletarCurso(anyString()));
    }

    @Test
    @DisplayName("DADO a requisição DELETE, QUANDO chamada e houver Curso com o código encontrado, DEVE removê-lo de tal forma que não me retorne uma exceção.")
    public void removerCurso_sucesso() {
        Curso curso = criarCurso();
        Mockito.when(repository.obterCursoPeloCodigo(anyString())).thenReturn(Optional.of(curso));
       assertDoesNotThrow(() -> service.deletarCurso(curso.getCodigo()));
    }
}