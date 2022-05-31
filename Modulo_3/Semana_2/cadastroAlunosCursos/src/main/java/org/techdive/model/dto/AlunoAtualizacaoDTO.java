package org.techdive.model.dto;

import javax.validation.constraints.NotBlank;

public class AlunoAtualizacaoDTO {

    private Integer matricula;

    @NotBlank(message = "Nome do aluno não pode ser vazio!")
    private String nome;

    public AlunoAtualizacaoDTO() {}

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
