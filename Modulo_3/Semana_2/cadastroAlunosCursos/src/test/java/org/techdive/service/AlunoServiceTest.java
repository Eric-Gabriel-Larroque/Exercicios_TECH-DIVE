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
import org.techdive.model.dto.AlunoAtualizacaoDTO;
import org.techdive.model.dto.AlunoDTO;
import org.techdive.model.entity.Aluno;
import org.techdive.repository.AlunoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.techdive.utils.EntityCreationHandler.*;

@DisplayName("Testes AlunoService")
@ExtendWith(MockitoExtension.class)
class AlunoServiceTest {

    @Mock
    private AlunoRepository repository;

    @InjectMocks
    private AlunoService service;

    private ModelMapper mapper = new ModelMapper();

    @Test
    @DisplayName("DADO a requisição GET para obter alunos, QUANDO a lista estiver vazia, DEVE me retornar uma exceção.")
    public void obterAlunos_listaVazia() {
        //when
        Mockito.when(repository.obterAlunos(anyString())).thenReturn(new ArrayList<>());
        List<Aluno> alunosEncontrados = repository.obterAlunos(anyString());
        //then
        assertThrows(RegistroNaoEncontradoException.class, () -> service.obterAlunos(anyString()));
        assertTrue(alunosEncontrados.isEmpty());
    }

    @Test
    @DisplayName("DADO a requisição GET para obtenção de alunos, QUANDO houverem alunos disponiveis com o nome pesquisado, DEVE me retornar uma lista de alunos")
    public void obterAlunos_sucesso() {
        Aluno aluno = criarAluno();
        aluno.setNome("nome1");
        List<Aluno> listaAlunos = criarListaAluno();
        Mockito.when(repository.obterAlunos(aluno.getNome()))
                .thenReturn(listaAlunos.stream()
                        .filter(a->a.getNome().equals(aluno.getNome()))
                        .collect(Collectors.toList()));
        List<AlunoDTO> alunosEncontrados = service.obterAlunos(aluno.getNome());

        assertNotNull(alunosEncontrados);
        assertFalse(alunosEncontrados.isEmpty());
        assertEquals(alunosEncontrados.get(0).getNome(),aluno.getNome());
    }

    @Test
    @DisplayName("DADO a requisição GET pela matricula do Aluno, QUANDO o numero da matricula for inexistente, DEVE me retornar uma exceção")
    public void obterAlunoPorMatricula_MatriculaInexistente() {
        Mockito.when(repository.obterAlunoPorMatricula(anyInt())).thenReturn(Optional.empty());
        assertThrows(RegistroNaoEncontradoException.class,() -> service.obterAlunoPorMatricula(anyInt()));
    }

    @Test
    @DisplayName("DADO a requisição GET pela matricula do Aluno, QUANDO o numero da matricula existir, DEVE me retornar um AlunoDTO")
    public void obterAlunoPorMatricula_sucesso() {
        //given
        Aluno aluno = criarAluno();
        Mockito.when(repository.obterAlunoPorMatricula(aluno.getMatricula())).thenReturn(Optional.of(aluno));

        AlunoDTO alunoEncontrado = service.obterAlunoPorMatricula(aluno.getMatricula());

        assertNotNull(alunoEncontrado);
        assertEquals(aluno.getMatricula(),alunoEncontrado.getMatricula());
        assertInstanceOf(AlunoDTO.class,alunoEncontrado);

    }

    @Test
    @DisplayName("DADO a requisição POST para criação de Aluno, QUANDO tentar criar Aluno com numero de matricula existente, DEVE me retornar uma exceção.")
    public void criarAluno_MatriculaExistente() {
        Aluno aluno = criarAluno();
        Mockito.when(repository.obterAlunoPorMatricula(aluno.getMatricula())).thenReturn(Optional.of(aluno));
        AlunoDTO alunoDTO = mapper.map(aluno,AlunoDTO.class);
        assertThrows(RegistroExistenteException.class, () -> service.inserirAluno(alunoDTO));
    }

    @Test
    @DisplayName("DADO a requisição POST para criação de Aluno, QUANDO criar Aluno com atributos preenchidos, DEVE me retornar o aluno criado.")
    public void criarAluno_sucesso() {
        Aluno aluno = criarAluno();
        AlunoDTO alunoDTO = mapper.map(aluno,AlunoDTO.class);
        alunoDTO.setMatricula(12345);

        Mockito.when(repository.obterAlunoPorMatricula(alunoDTO.getMatricula())).thenReturn(Optional.empty());

        AlunoDTO alunoCriado = service.inserirAluno(alunoDTO);

        assertNotNull(alunoCriado);
        assertNotNull(alunoCriado.getMatricula());
        assertNotNull(alunoCriado.getNome());
        assertNotEquals(alunoCriado.getMatricula(),aluno.getMatricula());
        assertEquals(alunoCriado.getMatricula(),alunoDTO.getMatricula());
        assertInstanceOf(AlunoDTO.class,alunoCriado);

    }

    @Test
    @DisplayName("DADO a requisição PUT para atualizar Aluno pela matricula, QUANDO inserir uma matricula inexistente, DEVE me retornar uma exceção.")
    public void atualizarAluno_MatriculaInexistente() {
        AlunoAtualizacaoDTO alunoAtualizacaoDTO = criarAlunoAtualizacaoDTO();
        Mockito.when(repository.obterAlunoPorMatricula(anyInt())).thenReturn(Optional.empty());
        assertThrows(RegistroNaoEncontradoException.class, () -> service.atualizarAluno(alunoAtualizacaoDTO));
    }

    @Test
    @DisplayName("Dado a requisição PUT para atualizar Aluno pela matrícula, QUANDO atualizar um aluno, DEVE me retornar um aluno atualizado sem mudar a sua matrícula.")
    public void atualizarAluno_sucesso() {
        Aluno aluno = criarAluno();
        AlunoAtualizacaoDTO alunoAtualizacaoDTO = mapper.map(aluno,AlunoAtualizacaoDTO.class);
        alunoAtualizacaoDTO.setNome("outro nome");

        Mockito.when(repository.obterAlunoPorMatricula(aluno.getMatricula())).thenReturn(Optional.of(aluno));

        AlunoDTO alunoAtualizado = service.atualizarAluno(alunoAtualizacaoDTO);

        assertNotNull(alunoAtualizado);
        assertEquals(alunoAtualizado.getMatricula(),aluno.getMatricula());
        assertInstanceOf(AlunoDTO.class,alunoAtualizado);
        assertNotEquals(aluno.getNome(),alunoAtualizado.getNome());
    }

    @Test
    @DisplayName("DADO a requisição DELETE para deleção de aluno, QUANDO passar uma matrícula inexistente, DEVE me retornar uma exceção.")
    public void deletarAluno_MatriculaInexistente() {
        Mockito.when(repository.obterAlunoPorMatricula(anyInt())).thenReturn(Optional.empty());
        assertThrows(RegistroNaoEncontradoException.class, () -> service.deletarAluno(anyInt()));
    }

    @Test
    @DisplayName("DADO a requisição DELETE para deleção de aluno, QUANDO passar uma matrícula existente, DEVE removê-lo de tal forma que não me retorne uma exceção.")
    public void deletarAluno_sucesso() {
        Aluno aluno = criarAluno();
        Mockito.when(repository.obterAlunoPorMatricula(aluno.getMatricula())).thenReturn(Optional.of(aluno));
        assertDoesNotThrow(() -> service.deletarAluno(aluno.getMatricula()));

    }


}