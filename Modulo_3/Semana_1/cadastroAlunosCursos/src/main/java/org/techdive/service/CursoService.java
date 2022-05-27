package org.techdive.service;

import org.modelmapper.ModelMapper;
import org.techdive.exception.RegistroExistenteException;
import org.techdive.exception.RegistroNaoEncontradoException;
import org.techdive.model.dto.CursoAtualizacaoDTO;
import org.techdive.model.dto.CursoDTO;
import org.techdive.model.entity.Curso;
import org.techdive.repository.CursoRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestScoped
public class CursoService {

    @Inject
    private CursoRepository cursoRepository;

    private final ModelMapper mapper = new ModelMapper();

    public List<CursoDTO> obterCursos(String sortBy, int limit) {
        List<Curso> cursos = cursoRepository.obterCursos(sortBy,limit);
        return cursos.stream().map(curso -> mapper.map(curso,CursoDTO.class)).collect(Collectors.toList());
    }


    public CursoDTO inserirCurso(CursoDTO cursoDTO) {
        verificaCodigoExistente(cursoDTO);
        Curso curso = mapper.map(cursoDTO,Curso.class);
        cursoRepository.inserirCurso(curso);
        return mapper.map(curso, CursoDTO.class);
    }

    public void verificaCodigoExistente(CursoDTO cursoDTO) {
        Optional<Curso> cursoOpt = cursoRepository.obterCursoPeloCodigo(cursoDTO.getCodigo());
        Curso curso = cursoOpt.orElse(null);
        if(curso!=null) {
            throw new RegistroExistenteException(Curso.class.getSimpleName(), cursoDTO.getCodigo());
        }
    }

    public CursoDTO obterCursoPorCodigo(String codigo) {
        Optional<Curso> cursoOpt = cursoRepository.obterCursoPeloCodigo(codigo);
        Curso curso = cursoOpt.orElse(null);
        if(curso == null) {
            throw new RegistroNaoEncontradoException(Curso.class.getSimpleName(), codigo);
        }
        return mapper.map(cursoOpt.get(),CursoDTO.class);
    }


    public CursoDTO atualizarCurso(CursoAtualizacaoDTO cursoAtualizacaoDTO) {
        obterCursoPorCodigo(cursoAtualizacaoDTO.getCodigo());
        Curso curso = mapper.map(cursoAtualizacaoDTO,Curso.class);
        cursoRepository.atualizarCurso(curso);
        return mapper.map(curso,CursoDTO.class);
    }

    public void deletarCurso(String codigo) {
        obterCursoPorCodigo(codigo);
        cursoRepository.deletarCurso(codigo);
    }
}
