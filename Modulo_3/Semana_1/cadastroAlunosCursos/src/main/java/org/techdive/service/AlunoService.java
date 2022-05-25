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

@RequestScoped
public class AlunoService {

    @Inject
    private AlunoRepository alunoRepository;

    private final ModelMapper mapper = new ModelMapper();

    public List<AlunoDTO> obterAlunos(String nome) throws RegistroNaoEncontradoException {
        List<Aluno> alunos = alunoRepository.obterAlunos(nome);
        if(alunos.isEmpty()) {
            throw new RegistroNaoEncontradoException(Aluno.class.getSimpleName(),nome);
        }
        return alunos.stream().map(aluno -> mapper.map(aluno, AlunoDTO.class)).toList();
    }

    public AlunoDTO obterAlunoPorMatricula(int matricula) throws RegistroNaoEncontradoException {
        Optional<Aluno> alunoOptional = alunoRepository.obterAlunoPorMatricula(matricula);
        verificaSeExisteAlunoPorMatricula(matricula, alunoOptional);
        Aluno aluno = alunoOptional.get();
        return mapper.map(aluno,AlunoDTO.class);
    }

    public AlunoDTO inserirAluno(AlunoDTO alunoDTO) throws RegistroExistenteException {
        verificarMatriculaDuplicada(alunoDTO);
        Aluno aluno = mapper.map(alunoDTO,Aluno.class);
        alunoRepository.inserirAluno(aluno);
        return mapper.map(aluno,AlunoDTO.class);
    }

    public AlunoDTO atualizarAluno(AlunoAtualizacaoDTO alunoAtualizacaoDTO) throws RegistroNaoEncontradoException {
        Optional<Aluno> alunoOpt = alunoRepository.obterAlunoPorMatricula(alunoAtualizacaoDTO.getMatricula());
        verificaSeExisteAlunoPorMatricula(alunoAtualizacaoDTO.getMatricula(),alunoOpt);
        Aluno aluno = mapper.map(alunoAtualizacaoDTO,Aluno.class);
        alunoRepository.atualizar(aluno);
        return mapper.map(aluno,AlunoDTO.class);
    }

    public void deletarAluno(Integer matricula) throws RegistroNaoEncontradoException {
        Optional<Aluno> alunoOpt = alunoRepository.obterAlunoPorMatricula(matricula);
        verificaSeExisteAlunoPorMatricula(matricula,alunoOpt);
        alunoRepository.deletarAluno(matricula);
    }

    private void verificarMatriculaDuplicada(AlunoDTO alunoDTO) throws RegistroExistenteException {
        boolean existe = alunoRepository.obterAlunoPorMatricula(alunoDTO.getMatricula()).isPresent();
        if (existe)
            throw new RegistroExistenteException(Aluno.class.getSimpleName(),String.valueOf(alunoDTO.getMatricula()));
    }

    private void verificaSeExisteAlunoPorMatricula(int matricula, Optional<Aluno> alunoOptional) throws RegistroNaoEncontradoException {
        boolean existe = alunoOptional.isPresent();
        if(!existe) {
            throw new RegistroNaoEncontradoException(Aluno.class.getSimpleName(),String.valueOf(matricula));
        }
    }

}