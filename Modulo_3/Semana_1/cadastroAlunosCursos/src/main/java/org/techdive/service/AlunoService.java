package org.techdive.service;

import org.modelmapper.ModelMapper;
import org.techdive.exception.RegistroExistenteException;
import org.techdive.exception.RegistroNaoEncontradoException;
import org.techdive.model.dto.AlunoAtualizacaoDTO;
import org.techdive.model.dto.AlunoDTO;
import org.techdive.model.entity.Aluno;
import org.techdive.repository.AlunoRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestScoped
public class AlunoService {

    @Inject
    private AlunoRepository alunoRepository;

    private final ModelMapper mapper = new ModelMapper();

    public List<AlunoDTO> obterAlunos(String nome) {
        List<Aluno> alunos = alunoRepository.obterAlunos(nome);
        if(alunos.isEmpty()) {
            throw new RegistroNaoEncontradoException(Aluno.class.getSimpleName(),nome);
        }
        return alunos.stream().map(aluno -> mapper.map(aluno, AlunoDTO.class)).collect(Collectors.toList());
    }

    public AlunoDTO obterAlunoPorMatricula(int matricula)  {
        Optional<Aluno> alunoOptional = alunoRepository.obterAlunoPorMatricula(matricula);
        if(!alunoOptional.isPresent()) {
            throw new RegistroNaoEncontradoException(Aluno.class.getSimpleName(), String.valueOf(matricula));
        }
        return mapper.map(alunoOptional.get(),AlunoDTO.class);
    }

    public AlunoDTO inserirAluno(AlunoDTO alunoDTO) {
        verificarMatriculaDuplicada(alunoDTO);
        Aluno aluno = mapper.map(alunoDTO,Aluno.class);
        alunoRepository.inserirAluno(aluno);
        return mapper.map(aluno,AlunoDTO.class);
    }

    public AlunoDTO atualizarAluno(AlunoAtualizacaoDTO alunoAtualizacaoDTO) {
        obterAlunoPorMatricula(alunoAtualizacaoDTO.getMatricula());
        Aluno aluno = mapper.map(alunoAtualizacaoDTO,Aluno.class);
        alunoRepository.atualizar(aluno);
        return mapper.map(aluno,AlunoDTO.class);
    }

    public void deletarAluno(Integer matricula) {
        obterAlunoPorMatricula(matricula);
        alunoRepository.deletarAluno(matricula);
    }

    private void verificarMatriculaDuplicada(AlunoDTO alunoDTO) {
        boolean existe = alunoRepository.obterAlunoPorMatricula(alunoDTO.getMatricula()).isPresent();
        if (existe)
            throw new RegistroExistenteException(Aluno.class.getSimpleName(),String.valueOf(alunoDTO.getMatricula()));
    }


}