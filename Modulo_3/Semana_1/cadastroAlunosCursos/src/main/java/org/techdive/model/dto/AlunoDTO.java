package org.techdive.model.dto;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class AlunoDTO implements Serializable {

    @NotBlank(message = "{aluno.matricula.notblank}")
    private String matricula;

    @NotBlank(message = "{aluno.nome.notblank }")
    private String nome;

    public AlunoDTO() {}

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
