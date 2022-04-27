package com.techdive.cadastrodecursos.model;

import javax.validation.constraints.NotBlank;

public class Usuario {

    @NotBlank(message = "{atr.obrigatorio}")
    private String login;

    @NotBlank(message = "{atr.obrigatorio}")
    private String senha;

    private String nome;

    public Usuario() {

    }

    public Usuario(String login, String senha, String nome) {
        this.login = login;
        this.senha = senha;
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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
