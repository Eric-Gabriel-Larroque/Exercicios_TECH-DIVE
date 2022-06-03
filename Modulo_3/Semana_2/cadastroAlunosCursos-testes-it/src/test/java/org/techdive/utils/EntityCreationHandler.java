package org.techdive.utils;

import org.modelmapper.ModelMapper;
import org.techdive.model.dto.*;
import org.techdive.model.entity.Aluno;
import org.techdive.model.entity.Curso;
import org.techdive.model.entity.Inscricao;

import java.util.ArrayList;
import java.util.List;

public class EntityCreationHandler {

    private static final ModelMapper mapper = new ModelMapper();


    public static Curso criarCurso() {
        return new Curso("codigo","assunto",1);
    }

    public static CursoDTO criarCursoDTO() {
        return mapper.map(criarCurso(),CursoDTO.class);
    }

    public static CursoAtualizacaoDTO criarCursoAtualizacaoDTO() {

        CursoAtualizacaoDTO cursoAtualizacaoDTO = new CursoAtualizacaoDTO();
        cursoAtualizacaoDTO.setAssunto("assunto 2");
        cursoAtualizacaoDTO.setDuracao(999);
        return cursoAtualizacaoDTO;
    }

    public static List<Curso> criarListaCurso(){
        List<Curso> cursos = new ArrayList<>();
        for(int i = 0; i < 10;i++) {
            Curso curso = new Curso();
            curso.setAssunto("assunto"+i);
            curso.setCodigo("codigo"+i);
            curso.setDuracao(i);
            cursos.add(curso);
        }
        return cursos;
    }

    public static Aluno criarAluno() {
        Aluno aluno = new Aluno();
        aluno.setMatricula(1234);
        aluno.setNome("nome");
        return aluno;
    }

    public static List<Aluno> criarListaAluno(){
        List<Aluno> alunos = new ArrayList<>();
        for(int i = 0; i < 10;i++) {
            Aluno aluno = new Aluno();
            aluno.setNome("nome"+i);
            aluno.setMatricula(i);
            alunos.add(aluno);
        }
        return alunos;
    }

    public static AlunoDTO criarAlunoDTO() {
        return mapper.map(criarAluno(),AlunoDTO.class);
    }

    public static AlunoAtualizacaoDTO criarAlunoAtualizacaoDTO() {
        AlunoAtualizacaoDTO alunoAtualizacaoDTO = new AlunoAtualizacaoDTO();
        alunoAtualizacaoDTO.setMatricula(12345);
        alunoAtualizacaoDTO.setNome("nome 2");
        return alunoAtualizacaoDTO;
    }

    public static Inscricao criarInscricao() {
        Inscricao inscricao = new Inscricao();
        inscricao.setCurso(criarCurso());
        inscricao.setAluno(criarAluno());
        inscricao.setId(1);
        return inscricao;
    }

    public static InscricaoRequestDTO criarInscricaoRequestDTO() {
        InscricaoRequestDTO inscricaoRequestDTO = new InscricaoRequestDTO();
        inscricaoRequestDTO.setCodigo("codigo");
        inscricaoRequestDTO.setMatricula(1234);
        return inscricaoRequestDTO;
    }
}
