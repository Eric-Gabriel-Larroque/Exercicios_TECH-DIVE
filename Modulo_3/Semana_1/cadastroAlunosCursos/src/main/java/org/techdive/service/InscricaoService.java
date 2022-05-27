package org.techdive.service;

import org.modelmapper.ModelMapper;
import org.techdive.model.dto.AlunoDTO;
import org.techdive.model.dto.CursoDTO;
import org.techdive.model.dto.InscricaoRequestDTO;
import org.techdive.model.dto.InscricaoResponseDTO;
import org.techdive.model.entity.Aluno;
import org.techdive.model.entity.Curso;
import org.techdive.model.entity.Inscricao;
import org.techdive.repository.InscricaoRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class InscricaoService {

    @Inject
    private InscricaoRepository inscricaoRepository;
    @Inject
    private AlunoService alunoService;
    @Inject
    private CursoService cursoService;

    private ModelMapper mapper = new ModelMapper();

    public InscricaoResponseDTO inserirInscricao(InscricaoRequestDTO inscricaoRequestDTO) {
        AlunoDTO alunoDTO = alunoService.obterAlunoPorMatricula(inscricaoRequestDTO.getMatricula());
        Aluno aluno = mapper.map(alunoDTO,Aluno.class);
        CursoDTO cursoDTO = cursoService.obterCursoPorCodigo(inscricaoRequestDTO.getCodigo());
        Curso curso = mapper.map(cursoDTO,Curso.class);

        Inscricao inscricao = new Inscricao();
        inscricao.setCurso(curso);
        inscricao.setAluno(aluno);
        inscricaoRepository.inserirInscricao(inscricao);

        inscricaoRequestDTO.setId(inscricao.getId());

        return mapper.map(inscricaoRequestDTO, InscricaoResponseDTO.class);
    }
}
