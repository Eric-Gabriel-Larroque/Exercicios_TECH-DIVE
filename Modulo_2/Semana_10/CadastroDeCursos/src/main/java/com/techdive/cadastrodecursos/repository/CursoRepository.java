package com.techdive.cadastrodecursos.repository;

import com.techdive.cadastrodecursos.model.Curso;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ApplicationScoped
public class CursoRepository {

    List<Curso> cursos = new ArrayList<>();
    private Long idCurso = 0L;

    public CursoRepository() {
        cursos.add(new Curso(obterProximoId(),"C01", "Robótica", LocalDate.now(), LocalDate.now().plusDays(5)));
        cursos.add(new Curso(obterProximoId(),"C02", "POO", LocalDate.now().minusDays(4), LocalDate.now().plusDays(10)));
        cursos.add(new Curso(obterProximoId(),"C03","Cibersegurança", LocalDate.now().minusDays(100), LocalDate.now().plusDays(40)));
        cursos.add(new Curso(obterProximoId(),"C04", "Desenvolvimento Mobile IOS", LocalDate.now().plusDays(10), LocalDate.now().plusDays(30)));
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

    public boolean codigoExistente(String codigo)  {
        return cursos.stream().filter(curso -> curso.getCodigo().equals(codigo))
                .collect(Collectors.toList()).size()>=1;
    }

    public boolean nomeExistente(String nome) {

        return cursos.stream().filter(curso -> curso.getNome().equals(nome))
                .collect(Collectors.toList()).size()>=1;
    }

    public void alterarCurso(Curso curso) {
        removerCurso(curso.getIdCurso());
        cursos.add(curso);
        Collections.sort(cursos, Comparator.comparing(Curso::getIdCurso));
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

}
