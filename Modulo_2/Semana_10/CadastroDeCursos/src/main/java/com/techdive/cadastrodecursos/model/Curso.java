package com.techdive.cadastrodecursos.model;

import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;

public class Curso {

    private Long idCurso;

    @NotBlank(message = "{atr.obrigatorio}")
    private String codigo;

    @Size(min = 2, max = 100, message = "{atr.nome.tamanho.invalido}")
    @NotBlank(message = "{atr.obrigatorio}")
    private String nome;

    @NotNull(message = "{atr.obrigatorio}")
    private LocalDate dataInicio;

    @NotNull(message = "{atr.obrigatorio}")
    private LocalDate dataFim;

    public Curso() {}

    public Curso(Long idCurso, String codigo, String nome, LocalDate dataInicio, LocalDate dataFim) {
        this.idCurso = idCurso;
        this.codigo = codigo;
        this.nome = nome;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return Objects.equals(idCurso, curso.idCurso) && Objects.equals(codigo, curso.codigo) && Objects.equals(nome, curso.nome) && Objects.equals(dataInicio, curso.dataInicio) && Objects.equals(dataFim, curso.dataFim);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCurso, codigo, nome, dataInicio, dataFim);
    }

    @Override
    public String toString() {
        return "Curso{" +
                "idCurso=" + idCurso +
                ", codigo='" + codigo + '\'' +
                ", nome='" + nome + '\'' +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                '}';
    }
}
