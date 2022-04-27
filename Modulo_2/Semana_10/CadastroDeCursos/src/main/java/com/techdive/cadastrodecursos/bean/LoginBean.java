package com.techdive.cadastrodecursos.bean;


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

@SessionScoped
@Named
public class LoginBean implements Serializable {

    @Inject
    private UsuarioRepository usuarioRepository;

    private boolean isAutenticado;

    private Usuario usuario = new Usuario();

    public String  login() {
        boolean isLogado = usuarioRepository.validarCredenciais(usuario);
        if(isLogado) {
            isAutenticado = true;
            return "principal?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Email ou senha inválido."));
        }
        return null;
    }

    public String logout() {
        isAutenticado = false;
        usuario = new Usuario();
        return "login?faces-redirect=true";
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
}
