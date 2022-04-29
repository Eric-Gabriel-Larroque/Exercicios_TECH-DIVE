package br.com.techdive.listadecompras.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;

public class Item {

    @NotEmpty(message = "{attr.nome.nulo}")
    private String nome;

    @PositiveOrZero(message = "{attr.quantidade.negativa}")
    private float quantidade;

    @NotEmpty(message = "{attr.medida.nula}")
    private String medida;

    public Item() {}


    @Override
    public String toString() {
        return quantidade+" "+medida+"(s) de "+nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.toLowerCase();
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida.toLowerCase();
    }
}
