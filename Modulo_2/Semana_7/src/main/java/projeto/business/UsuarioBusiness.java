package projeto.business;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.subject.Subject;
import org.omnifaces.util.Faces;
import projeto.dto.LoginDTO;
import projeto.entity.Role;
import projeto.entity.Usuario;
import projeto.repository.UsuarioRepository;
import projeto.security.UserAuthenticationToken;
import projeto.utils.MessageUtils;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

public class UsuarioBusiness {

    @Inject
    private UsuarioRepository usuarioRepository;

    Subject user;

    @PostConstruct
    public void init() {
        user = SecurityUtils.getSubject();
    }

    public void criarUsuarioAdmin() {
        if(usuarioRepository.existeAdministradorJaCadastrado()) return;

        Usuario usuario = new Usuario();
        usuario.setNome("Administrador de Sistemas");
        usuario.setEmail("admin@admin.com");
        usuario.setSenha("21232f297a57a5a743894a0e4a801fc3");
        Role role = new Role();
        role.setNome("ADMIN");

        usuarioRepository.persist(role);
        usuario.getRoles().add(role);
        usuarioRepository.persist(usuario);
        usuarioRepository.flush();

    }

    public LoginDTO login(LoginDTO loginDTO) throws Exception {
        user = SecurityUtils.getSubject();

        if(user.isAuthenticated()) {
            Faces.redirect("/index.xhtml");
            return null;
        }

        String errorMsg = validateFields(loginDTO);

        if(errorMsg == null) {
            UserAuthenticationToken token = new UserAuthenticationToken(loginDTO);

            try {
                user.login(token);

                if(user.isAuthenticated()) {
                    Faces.redirect("/index.xhtml");
                }
                return loginDTO;
            } catch (IncorrectCredentialsException e) {
                MessageUtils.returnMessageOnFail("Usuario ou senha incorretos");
            } catch (Exception e) {
                MessageUtils.returnMessageOnFail("Ocorreu um erro ao efetuar o login. Por favor, entre em contato com o suporte");
            }
        } else {
            MessageUtils.returnMessageOnFail(errorMsg);
        }
        return loginDTO;
    }

    private String validateFields(LoginDTO loginDTO) {
        String message = null;

        if(StringUtils.isBlank(loginDTO.getEmail()) && StringUtils.isBlank(loginDTO.getSenha())) {
            message = "Por favor, preencha os campos";
        } else if(StringUtils.isBlank(loginDTO.getEmail())) {
            message = "E-mail não informado";
        } else if(StringUtils.isBlank(loginDTO.getSenha())) {
            message = "Senha não informada";
        }

        return message;
    }

}
