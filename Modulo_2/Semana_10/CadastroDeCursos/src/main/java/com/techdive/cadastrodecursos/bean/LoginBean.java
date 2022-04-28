package com.techdive.cadastrodecursos.bean;


import com.techdive.cadastrodecursos.model.Curso;
import com.techdive.cadastrodecursos.model.Usuario;
import com.techdive.cadastrodecursos.repository.UsuarioRepository;
import org.jboss.weld.context.RequestContext;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
@Named
public class LoginBean implements Serializable {

    @Inject
    private UsuarioRepository usuarioRepository;

    private boolean isAutenticado;

    private String email;

    private String senha;

    private Usuario usuario;

    public String  login() {
        boolean isLogado = usuarioRepository.validarCredenciais(email, senha);
        if(isLogado) {
            isAutenticado = true;
            usuario = usuarioRepository.obterPor(email,senha);
            return "secure/principal?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Email ou senha inválido."));
        }
        return null;
    }

    public String logout() {
        isAutenticado = false;
        usuario = null;
        return "/faces/login?faces-redirect=true";
    }

    public boolean isAutenticado() {
        return isAutenticado;
    }

    public void setAutenticado(boolean autenticado) {
        isAutenticado = autenticado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
}
