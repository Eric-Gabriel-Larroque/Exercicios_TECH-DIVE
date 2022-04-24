package projeto.bean;

import org.omnifaces.cdi.ViewScoped;
import projeto.entity.Role;
import projeto.security.UserAuthenticationToken;
import projeto.utils.MessageUtils;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@ViewScoped
@Named("homeWebBean")
public class HomeWebBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private UserAuthenticationToken usuarioLogado;

    private String texto;

    private Date data;

    private boolean apenasVisualizarCampoDeTexto;

    @PostConstruct
    public void init() {
        texto = "Hello, world!";
        apenasVisualizarCampoDeTexto = true;
    }

    public void mostrarCamposNaMensagem() {
        String mensagem = "Campos: \n";
        mensagem += "Texto digitado: " + texto + "\n";
        mensagem += "Data selecionada: " + data + "\n";
        MessageUtils.returnMessageOnSuccess(mensagem);
    }

    public void atualizarData() {
        data = new Date();
        MessageUtils.returnMessageOnSuccess("Nova data: " + data);
    }

    public boolean isAdmin() {
        return usuarioLogado.possuiRole(Role.ADMIN);
    }

    public boolean isRenderizarCampoDeTexto() {
        return true;
    }

    public boolean desabilitarCampoDeTexto() {
        return false;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public boolean isApenasVisualizarCampoDeTexto() {
        return apenasVisualizarCampoDeTexto;
    }

    public void setApenasVisualizarCampoDeTexto(boolean apenasVisualizarCampoDeTexto) {
        this.apenasVisualizarCampoDeTexto = apenasVisualizarCampoDeTexto;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
