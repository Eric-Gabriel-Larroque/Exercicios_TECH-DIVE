package org.techdive.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class AlunoDTO implements Serializable {

    @NotNull(message = "{aluno.matricula.notnull}")
    private int matricula;

    @NotBlank(message = "{aluno.nome.notblank }")
    private String nome;

    public AlunoDTO() {}

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
