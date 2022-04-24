package projeto.bean;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.omnifaces.cdi.ViewScoped;
import org.omnifaces.util.Faces;
import projeto.dto.LoginDTO;
import projeto.service.UsuarioService;
import projeto.utils.MessageUtils;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@ViewScoped
@Named("loginWebBean")
public class LoginWebBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private UsuarioService usuarioService;

    private LoginDTO login = new LoginDTO();

    @PostConstruct
    public void init() {
        if (SecurityUtils.getSubject().isAuthenticated()) {
            Faces.redirect("/index.xhtml");
        }
        usuarioService.criarUsuarioAdmin();
    }

    public void login() {
        try {
            login = usuarioService.login(login);
        } catch (Exception e) {
            e.printStackTrace();
            MessageUtils.returnMessageOnFail("Ocorreu um erro no login.");
        }
    }

    public String logout() {
        try {
            Subject user = SecurityUtils.getSubject();
            if (user != null) {
                user.logout();
            }
            return "/index.xhtml?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace();
            MessageUtils.returnMessageOnFail("Ocorreu um erro no logout.");
            return null;
        }
    }

    public LoginDTO getLogin() {
        return login;
    }

    public void setLogin(LoginDTO login) {
        this.login = login;
    }
}
