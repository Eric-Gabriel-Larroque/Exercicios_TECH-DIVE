package com.techdive.cadastrodecursos.bean;

import com.techdive.cadastrodecursos.model.Curso;
import com.techdive.cadastrodecursos.repository.CursoRepository;

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
import java.util.Objects;

@SessionScoped
@Named
public class CursoBean implements Serializable {

    @Inject
    private CursoRepository cursoRepository;

    private List<Curso> cursos = new ArrayList<>();

    private Curso curso = new Curso();

    public String salvar() {
        boolean isCursoNovo = curso.getIdCurso() == null;
        if(isCursoNovo) {
            cursoRepository.cadatrarCurso(curso);
        } else {
            cursoRepository.alterarCurso(curso);
        }
        curso = new Curso();
        return "principal?faces-redirect=true";
    }

    public void validarCodigoCadastrado(FacesContext facesContext, UIComponent component, Object o) throws ValidatorException {
        if(o == null) {
            return;
        }
        String codigo = (String) o;
        boolean jaCadastrado = cursoRepository.codigoExistente(codigo);
        if(jaCadastrado) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                        "Erro de Validação",
                                          "Curso já cadastrado com o Código informado.");
            throw new ValidatorException(msg);
        }
    }

    public void validarNomeCadastrado(FacesContext facesContext, UIComponent component, Object o) throws ValidatorException {
        if(o == null) {
            return;
        }
        String nome = (String) o;
        boolean jaCadastrado = cursoRepository.nomeExistente(nome);
        if(jaCadastrado) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Erro de Validação",
                    "Curso já cadastrado com o nome informado.");
            throw new ValidatorException(msg);
        }
    }

    public void init() {
        List<Curso> cursosBD = cursoRepository.getCursos();
        cursos = cursosBD;
    }

    public String editarCurso(Curso curso) {
        setCurso(curso);
        return "cadastro";
    }

    public String removerCurso(Curso curso) {
        cursoRepository.removerCurso(curso.getIdCurso());
        cursos = new ArrayList<>();
        return null;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
