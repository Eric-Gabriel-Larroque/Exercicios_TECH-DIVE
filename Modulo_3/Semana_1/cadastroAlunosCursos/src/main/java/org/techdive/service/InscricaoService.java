package org.techdive.service;

import org.modelmapper.ModelMapper;
import org.techdive.exception.RegistroNaoEncontradoException;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestScoped
public class InscricaoService {

    @Inject
    private InscricaoRepository inscricaoRepository;
    @Inject
    private AlunoService alunoService;
    @Inject
    private CursoService cursoService;

    private final ModelMapper mapper = new ModelMapper();

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

    public List<InscricaoResponseDTO> obterInscricoes() {
        List<Inscricao> inscricoes = inscricaoRepository.obterCursos();
        List<InscricaoResponseDTO> responseDTOS = new ArrayList<>();
        for(Inscricao inscricao: inscricoes) {
            InscricaoResponseDTO responseDTO = new InscricaoResponseDTO();
            responseDTO.setId(inscricao.getId());
            responseDTO.setCodigo(inscricao.getCurso().getCodigo());
            responseDTO.setMatricula(inscricao.getAluno().getMatricula());
            responseDTOS.add(responseDTO);
        }

        return responseDTOS;
    }

    public void deletarInscricao(Integer id) {
        Inscricao inscricao = obterInscricaoPorId(id);
        inscricaoRepository.deletarInscricao(id);
    }

    public Inscricao obterInscricaoPorId(Integer id) {
        Optional<Inscricao> inscricaoOpt = inscricaoRepository.obterInscricaoPorId(id);
        Inscricao inscricao = inscricaoOpt.orElse(null);
        if(inscricao == null) {
            throw new RegistroNaoEncontradoException(Curso.class.getSimpleName(), String.valueOf(id));
        }
        return inscricaoOpt.get();
    }

}
