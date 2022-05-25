package org.techdive.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CursoDTO {

    @NotBlank(message = "{curso.codigo.notblank}")
    private String codigo;

    @NotBlank(message = "{curso.assunto.notblank}")
    private String assunto;

    @Positive(message = "{curso.duracao.positive}")
    @NotNull(message = "{curso.duracao.notnull}")
    private int duracao;

    public CursoDTO() {}

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
