package com.techdive.cadastrodecursos.model;

import javax.validation.constraints.NotBlank;

public class Usuario {

    @NotBlank(message = "{atr.obrigatorio}")
    private String email;

    @NotBlank(message = "{atr.obrigatorio}")
    private String senha;

    private String nome;

    public Usuario() {

    }

    public Usuario(String email, String senha, String nome) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
    }

    public Usuario(String email, String nome) {
        this.email = email;
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
