package org.techdive.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class InscricaoRequestDTO {

    private Integer id;

    @NotNull(message = "Matricula do aluno não pode ser vazia!")
    private Integer matricula;

    @NotBlank(message = "Código do curso não pode ser vazio!")
    private String codigo;

    public InscricaoRequestDTO() {}

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
