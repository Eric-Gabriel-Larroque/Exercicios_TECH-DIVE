package org.techdive.utils;

import org.techdive.model.dto.AlunoAtualizacaoDTO;
import org.techdive.model.dto.CursoAtualizacaoDTO;
import org.techdive.model.entity.Aluno;
import org.techdive.model.entity.Curso;

public class EntityCreationHandler {

    public static Curso criarCurso() {
        return new Curso("codigo","assunto",1);
    }

    public static CursoAtualizacaoDTO criarCursoAtualizacaoDTO() {

        CursoAtualizacaoDTO cursoAtualizacaoDTO = new CursoAtualizacaoDTO();
        cursoAtualizacaoDTO.setAssunto("assunto 2");
        cursoAtualizacaoDTO.setDuracao(999);
        return cursoAtualizacaoDTO;
    }

    public static Aluno criarAluno() {
        Aluno aluno = new Aluno();
        aluno.setMatricula(1234);
        aluno.setNome("nome");
        return aluno;
    }

    public static AlunoAtualizacaoDTO criarAlunoAtualizacaoDTO() {
        AlunoAtualizacaoDTO alunoAtualizacaoDTO = new AlunoAtualizacaoDTO();
        alunoAtualizacaoDTO.setMatricula(12345);
        alunoAtualizacaoDTO.setNome("nome 2");
        return alunoAtualizacaoDTO;
    }
}
