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
import org.techdive.model.dto.AlunoAtualizacaoDTO;
import org.techdive.model.dto.AlunoDTO;
import org.techdive.service.AlunoService;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.techdive.utils.EntityCreationHandler.criarAluno;
import static org.techdive.utils.EntityCreationHandler.criarListaAluno;

@ExtendWith(MockitoExtension.class)
class AlunoControllerTest {

    @Mock
    private AlunoService service;

    @InjectMocks
    private AlunoController controller;

    private ModelMapper mapper = new ModelMapper();

    @Test
    @DisplayName("DADO a requisição GET, QUANDO não houverem alunos, DEVE me retornar uma lista vazia com status 200")
    void obterAlunos_listaVazia() {
        Mockito.when(service.obterAlunos(anyString())).thenReturn(new ArrayList<>());
        Response result = controller.obterAlunos(anyString());

        assertNotNull(result);
        assertEquals(result.getStatus(),Response.Status.OK.getStatusCode());
        List<AlunoDTO> listaAlunos = (List<AlunoDTO>) result.getEntity();
        assertTrue(listaAlunos.isEmpty());
    }

    @Test
    @DisplayName("DADO a requisição GET, QUANDO houverem alunos, DEVE me retornar uma lista de alunos com status 200")
    void obterAlunos_sucesso() {
        List<AlunoDTO> alunos = criarListaAluno().stream().map(a->mapper.map(a,AlunoDTO.class)).collect(Collectors.toList());
        Mockito.when(service.obterAlunos(anyString())).thenReturn(alunos);
        Response result = controller.obterAlunos(anyString());

        assertNotNull(result);
        assertEquals(result.getStatus(),Response.Status.OK.getStatusCode());
        List<AlunoDTO> listaAlunos = (List<AlunoDTO>) result.getEntity();
        assertFalse(listaAlunos.isEmpty());
    }




    @Test
    @DisplayName("DADO a requisição GET pelo Id do Aluno, QUANDO a matricula inserida for inexistente, DEVE me retornar uma exceção com status 404")
    void obterAlunoPorMatricula_matriculaInexistente() {
        Mockito.when(service.obterAlunoPorMatricula(anyInt())).thenThrow(RegistroNaoEncontradoException.class);
        assertThrows(RegistroNaoEncontradoException.class, () -> controller.obterAlunoPorMatricula(anyInt()));
    }

    @Test
    @DisplayName("Dado a requisição GET pelo Id do Aluno, QUANDO a matricula inserido existir, DEVE me retornar o Aluno com status 200.")
    void obterAlunoPorMatricula_sucesso() {
        Mockito.when(service.obterAlunoPorMatricula(anyInt())).thenReturn(mapper.map(criarAluno(),AlunoDTO.class));
        Response result = controller.obterAlunoPorMatricula(anyInt());

        assertNotNull(result);
        assertInstanceOf(AlunoDTO.class,result.getEntity());
        assertEquals(result.getStatus(),Response.Status.OK.getStatusCode());

    }

    @Test
    @DisplayName("DADO a requisição POST para criação de Aluno, QUANDO inserir uma matricula existente, DEVE me retornar uma exceção com status 409")
    void inserirAluno_matriculaExistente() {
        AlunoDTO alunoDTO = mapper.map(criarAluno(),AlunoDTO.class);
        Mockito.when(service.inserirAluno(alunoDTO)).thenThrow(RegistroExistenteException.class);
        assertThrows(RegistroExistenteException.class, () -> controller.inserirAluno(alunoDTO));
    }

    @Test
    @DisplayName("DADO a requisição POST para criação de Aluno, QUANDO inserir uma matricula inexistente, DEVE me retornar o Aluno criado com status 201")
    void inserirAluno_sucesso() {
        AlunoDTO alunoDTO = mapper.map(criarAluno(),AlunoDTO.class);
        Mockito.when(service.inserirAluno(alunoDTO)).thenReturn(alunoDTO);

        Response result = controller.inserirAluno(alunoDTO);
        assertNotNull(result);
        assertInstanceOf(AlunoDTO.class,result.getEntity());
        assertEquals(result.getStatus(),Response.Status.CREATED.getStatusCode());
    }

    @Test
    @DisplayName("DADO a requisição PUT para atualização de Aluno, QUANDO a matricula inserida for inexistente, DEVE retornar uma exceção com status 409")
    void atualizarAluno_matriculaInexistente() {
        AlunoAtualizacaoDTO atualizacaoDTO = mapper.map(criarAluno(),AlunoAtualizacaoDTO.class);
        Mockito.when(service.atualizarAluno(atualizacaoDTO)).thenThrow(RegistroExistenteException.class);
        assertThrows(RegistroExistenteException.class, () -> controller.atualizarAluno(anyInt(),atualizacaoDTO));
    }

    @Test
    @DisplayName("DADO a requisição PUT para atualização de Aluno, QUANDO a matricula inserida existir, DEVE retornar o Aluno atualizado com status 200")
    void atualizarAluno_sucesso() {
        AlunoAtualizacaoDTO atualizacaoDTO = mapper.map(criarAluno(),AlunoAtualizacaoDTO.class);
        Mockito.when(service.atualizarAluno(atualizacaoDTO)).thenReturn(mapper.map(atualizacaoDTO,AlunoDTO.class));

        Response result = controller.atualizarAluno(anyInt(),atualizacaoDTO);

        assertNotNull(result);
        assertEquals(result.getStatus(),Response.Status.OK.getStatusCode());
        assertInstanceOf(AlunoDTO.class,result.getEntity());

    }

    @Test
    @DisplayName("DADO a requisição DELETE, QUANDO inserir uma matricula inexistente, DEVE retornar uma exceção com status 404")
    void deletarAluno_matriculaInexistente() {
        Mockito.doThrow(RegistroNaoEncontradoException.class).when(service).deletarAluno(anyInt());
        assertThrows(RegistroNaoEncontradoException.class, () -> controller.deletarAluno(anyInt()));
    }

    @Test
    @DisplayName("DADO a requisição DELETE, QUANDO inserir uma matricula existente, DEVE retornar o body vazio com status 204")
    void deletarAluno_sucesso() {
        Response result = controller.deletarAluno(anyInt());
        assertDoesNotThrow(() -> controller.deletarAluno(anyInt()));
        assertNull(result.getEntity());
        assertEquals(result.getStatus(),Response.Status.NO_CONTENT.getStatusCode());
    }

}