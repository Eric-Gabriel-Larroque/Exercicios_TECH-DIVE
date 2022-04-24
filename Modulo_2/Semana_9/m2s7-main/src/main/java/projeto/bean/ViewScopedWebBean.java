package projeto.bean;

import org.omnifaces.cdi.ViewScoped;

import javax.inject.Named;
import java.io.Serializable;

@ViewScoped
@Named("viewScopedWebBean")
public class ViewScopedWebBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;

    private String nomeAlterado;

    public void alterarNome() {
        nomeAlterado = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeAlterado() {
        return nomeAlterado;
    }

    public void setNomeAlterado(String nomeAlterado) {
        this.nomeAlterado = nomeAlterado;
    }
}
