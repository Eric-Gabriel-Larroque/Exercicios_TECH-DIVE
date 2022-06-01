package org.techdive.utils;

import org.techdive.model.dto.CursoAtualizacaoDTO;
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
}
