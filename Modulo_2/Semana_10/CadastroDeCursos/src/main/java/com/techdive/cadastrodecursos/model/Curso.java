package com.techdive.cadastrodecursos.model;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Objects;

public class Curso {

    private Long idCurso;

    @Size(min = 2, max = 100, message = "{atr.codigo.tamanho.invalido}")
    @NotBlank(message = "{atr.obrigatorio}")
    private String codigo;

    @NotNull(message = "{atr.obrigatorio}")
    private LocalDate dataInicio;

    @NotNull(message = "{atr.obrigatorio}")
    private LocalDate dataFim;

    public Curso() {}

    public Curso(Long idCurso, String codigo, LocalDate dataInicio, LocalDate dataFim) {
        this.idCurso = idCurso;
        this.codigo = codigo;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curso curso = (Curso) o;
        return Objects.equals(idCurso, curso.idCurso) && Objects.equals(codigo, curso.codigo) && Objects.equals(dataInicio, curso.dataInicio) && Objects.equals(dataFim, curso.dataFim);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCurso, codigo, dataInicio, dataFim);
    }


    @Override
    public String toString() {
        return "Curso{" +
                "idCurso=" + idCurso +
                ", codigo='" + codigo + '\'' +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                '}';
    }

}
