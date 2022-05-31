package org.techdive.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;

public class AlunoDTO implements Serializable {

    @Positive(message = "Matricula do aluno deve ser um valor inteiro positivo!")
    @NotNull(message = "Matricula do aluno não pode ser vazia!")
    private Integer matricula;

    @NotBlank(message = "Nome do aluno não pode ser vazio!")
    private String nome;

    public AlunoDTO() {}

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
