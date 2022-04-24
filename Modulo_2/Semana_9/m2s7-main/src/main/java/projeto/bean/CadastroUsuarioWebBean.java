package projeto.bean;

import org.omnifaces.cdi.ViewScoped;
import projeto.utils.MessageUtils;

import javax.inject.Named;
import java.io.Serializable;

@ViewScoped
@Named("cadastroUsuarioWebBean")
public class CadastroUsuarioWebBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;

    private String email;

    public void cadastrar() {
        MessageUtils.returnMessageOnSuccess("Nome: " + nome + ", " + "e-mail: " + email);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
