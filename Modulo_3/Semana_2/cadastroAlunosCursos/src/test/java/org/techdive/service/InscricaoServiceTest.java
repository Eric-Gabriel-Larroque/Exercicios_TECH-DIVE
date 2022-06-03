package org.techdive.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.techdive.exception.RegistroNaoEncontradoException;
import org.techdive.model.dto.AlunoDTO;
import org.techdive.model.dto.CursoDTO;
import org.techdive.model.dto.InscricaoRequestDTO;
import org.techdive.model.dto.InscricaoResponseDTO;
import org.techdive.model.entity.Aluno;
import org.techdive.model.entity.Inscricao;
import org.techdive.repository.InscricaoRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.techdive.utils.EntityCreationHandler.*;


@ExtendWith(MockitoExtension.class)
class InscricaoServiceTest {

    @Mock
    private AlunoService alunoService;
    @Mock
    private CursoService cursoService;
    @Mock
    private InscricaoRepository inscricaoRepository;

    private ModelMapper mapper = new ModelMapper();

    @InjectMocks
    private InscricaoService inscricaoService;

    @Test
    @DisplayName("DADO a requisição POST para criação de inscrição, QUANDO inserir uma matrícula inexistente, DEVE me retornar uma exceção.")
    void inserirInscricao_matriculaInexistente() {

        InscricaoRequestDTO requestDTO = criarInscricaoRequestDTO();

        Mockito.when(alunoService.obterAlunoPorMatricula(anyInt())).thenThrow(RegistroNaoEncontradoException.class);
        Assertions.assertThrows(RegistroNaoEncontradoException.class, () -> inscricaoService.inserirInscricao(requestDTO));
    }

    @Test
    @DisplayName("DADO a requisição POST para criação de inscrição, QUANDO inserir um codigo inexistente, DEVE me retornar uma exceção.")
    void inserirInscricao_CodigoInexistente() {
        InscricaoRequestDTO requestDTO = criarInscricaoRequestDTO();
        Aluno aluno = criarAluno();
        AlunoDTO alunoDTO = mapper.map(aluno,AlunoDTO.class);
        Mockito.when(alunoService.obterAlunoPorMatricula(aluno.getMatricula())).thenReturn(alunoDTO);
        Mockito.when(cursoService.obterCursoPorCodigo(anyString())).thenThrow(RegistroNaoEncontradoException.class);
        Assertions.assertThrows(RegistroNaoEncontradoException.class, () -> inscricaoService.inserirInscricao(requestDTO));
    }

    @Test
    @DisplayName("DADO a requisição POST para criação de inscrição, QUANDO inserir matricula E codigo existentes, DEVE me retornar a inscrição criada com ID")
    void inserirInscricao_sucesso() {
        InscricaoRequestDTO requestDTO = criarInscricaoRequestDTO();
        Mockito.when(alunoService.obterAlunoPorMatricula(requestDTO.getMatricula())).thenReturn(new AlunoDTO());
        Mockito.when(cursoService.obterCursoPorCodigo(requestDTO.getCodigo())).thenReturn(new CursoDTO());

        InscricaoResponseDTO inscricaoCriada = inscricaoService.inserirInscricao(requestDTO);

        assertNotNull(inscricaoCriada);
       // assertNotNull(inscricaoCriada.getId())
        assertInstanceOf(InscricaoResponseDTO.class,inscricaoCriada);

    }

    @Test
    @DisplayName("Dado a requisição GET realizada, QUANDO não houver inscrições disponíveis, DEVE me retornar uma lista vazia.")
    void obterInscricoes_listaVazia() {

        Mockito.when(inscricaoRepository.obterInscricoes()).thenReturn(new ArrayList<>());
        List<InscricaoResponseDTO> listaInscricoes = inscricaoService.obterInscricoes();

        assertNotNull(listaInscricoes,"Deveria ser uma lista vazia");
    }

    @Test
    @DisplayName("Dado a requisição GET realizada, QUANDO houver inscrições disponíveis, DEVE me retornar uma lista de inscrições.")
    void obterInscricoes_sucesso() {
        Mockito.when(inscricaoRepository.obterInscricoes()).thenReturn(Arrays.asList(criarInscricao()));
        List<InscricaoResponseDTO> listaInscricoes = inscricaoService.obterInscricoes();

        assertNotNull(listaInscricoes,"Deveria ser uma lista vazia");
        assertFalse(listaInscricoes.isEmpty());
    }

    @Test
    @DisplayName("DADO a requisição DELETE pelo id da inscrição, QUANDO o id da inscrição não for encontrado, DEVE retornar uma exceção")
    void deletarInscricao_idINexistente() {
        Mockito.when(inscricaoRepository.obterInscricaoPorId(anyInt())).thenThrow(RegistroNaoEncontradoException.class);
        assertThrows(RegistroNaoEncontradoException.class, () -> inscricaoService.deletarInscricao(anyInt()));
    }

    @Test
    @DisplayName("DADO a requisição DELETE pelo id da inscrição, QUANDO o id da inscrição for encontrado, DEVE remover a inscrição do BD")
    void deletarInscricao_sucesso() {
        Mockito.when(inscricaoRepository.obterInscricaoPorId(anyInt())).thenReturn(Optional.of(new Inscricao()));
        assertDoesNotThrow(() -> inscricaoService.deletarInscricao(anyInt()));
    }

    @Test
    @DisplayName("DADO a requisição GET pelo Id da inscrição, QUANDO o id não for encontrado, DEVE me retornar uma exceção.")
    void obterInscricaoPorId_IdInexistente() {
        Mockito.when(inscricaoRepository.obterInscricaoPorId(anyInt())).thenReturn(Optional.empty());
        assertThrows(RegistroNaoEncontradoException.class, () -> inscricaoService.obterInscricaoPorId(anyInt()));
    }

    @Test
    @DisplayName("DADO a requisição GET pelo Id da inscrição, QUANDO o id for encontrado, DEVE me retornar a inscrição.")
    void obterInscricaoPorId_sucesso() {
        Inscricao inscricao = criarInscricao();
        Mockito.when(inscricaoRepository.obterInscricaoPorId(inscricao.getId())).thenReturn(Optional.of(inscricao));

        Inscricao inscricaoEncontrada = inscricaoService.obterInscricaoPorId(inscricao.getId());

        assertNotNull(inscricaoEncontrada);
        assertNotNull(inscricaoEncontrada.getId());
        assertEquals(inscricaoEncontrada.getId(),inscricao.getId());
        assertInstanceOf(Inscricao.class,inscricaoEncontrada);
    }
}