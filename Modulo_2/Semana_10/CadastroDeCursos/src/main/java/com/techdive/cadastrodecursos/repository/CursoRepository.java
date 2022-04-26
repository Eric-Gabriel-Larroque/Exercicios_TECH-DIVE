package com.techdive.cadastrodecursos.repository;

import com.techdive.cadastrodecursos.model.Curso;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CursoRepository {

    List<Curso> cursos;
    private Long idCurso = 0L;

    public CursoRepository() {
        cursos.add(new Curso(obterProximoId(), "Robótica", LocalDate.now(), LocalDate.now().plusDays(5)));
        cursos.add(new Curso(obterProximoId(), "POO", LocalDate.now().minusDays(4), LocalDate.now().plusDays(10)));
        cursos.add(new Curso(obterProximoId(), "Cibersegurança", LocalDate.now().minusDays(100), LocalDate.now().plusDays(40)));
        cursos.add(new Curso(obterProximoId(), "Desenvolvimento Mobile IOS", LocalDate.now().plusDays(10), LocalDate.now().plusDays(30)));
    }

    public List<Curso> obterCursos() {
        return cursos;
    }

    private long obterProximoId() {
        return ++idCurso;
    }

    public void cadatrarCurso(Curso curso) {
        Long proxId = obterProximoId();
        curso.setIdCurso(proxId);
        cursos.add(curso);
    }

    public void removerCurso(Long idCurso) {
        cursos.removeIf(curso->curso.getIdCurso()==idCurso);
    }

    void alterarCurso(Curso curso) {
        removerCurso(curso.getIdCurso());
        cursos.add(curso);
        Collections.sort(cursos, Comparator.comparing(Curso::getIdCurso));
    }



}
