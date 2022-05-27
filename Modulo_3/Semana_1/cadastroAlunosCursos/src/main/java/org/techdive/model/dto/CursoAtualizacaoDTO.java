package org.techdive.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CursoAtualizacaoDTO {

    private String codigo;

    @NotBlank(message = "Assunto do código não pode ser vazio!")
    private String assunto;

    @Positive(message = "Duração do curso deve ser um valor inteiro positivo!")
    @NotNull(message = "Duracao do curso não pode ser vazia!")
    private int duracao;

    public CursoAtualizacaoDTO() {}

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

}
