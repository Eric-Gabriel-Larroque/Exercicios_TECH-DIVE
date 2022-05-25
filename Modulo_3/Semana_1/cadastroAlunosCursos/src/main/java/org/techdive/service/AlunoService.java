package org.techdive.service;

import org.modelmapper.ModelMapper;
import org.techdive.exception.RegistroNaoEncontradoException;
import org.techdive.model.dto.AlunoDTO;
import org.techdive.model.entity.Aluno;
import org.techdive.repository.AlunoRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

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
        return alunos.stream().map(aluno -> mapper.map(aluno, AlunoDTO.class)).collect(Collectors.toList());
    }
}
