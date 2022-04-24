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
        if (usuarioRepository.existeAdministradorJaCadastrado()) {
            return;
        }

        Usuario usuario = new Usuario();
        usuario.setNome("Administrador do sistema");
        usuario.setEmail("admin@admin.com");
        usuario.setSenha("21232f297a57a5a743894a0e4a801fc3"); // senha 'admin'
        Role role = new Role();
        role.setNome("ADMIN");

        usuarioRepository.persist(role);
        usuario.getRoles().add(role);
        usuarioRepository.persist(usuario);
        usuarioRepository.flush();
    }

    /*
        Aqui é o método chamado quando o usuário clica em ENTRAR na tela de login.
        Ao entrar, primeiramente o sistema verifica se o Subject do Apache Shiro já está autenticado, ou seja, se o
        usuário já fez login no sistema. Se fez login, joga ele para a home (index.xhtml), senão, valida os campos de
        e-mail e senha e tenta realizar a autenticação.
    */
    public LoginDTO login(LoginDTO loginDTO) throws Exception {
        user = SecurityUtils.getSubject();
        if (user.isAuthenticated()) {
            Faces.redirect("/index.xhtml");
            return null;
        }

        String errorMsg = validateFields(loginDTO);

        if (errorMsg == null) {
            UserAuthenticationToken token = new UserAuthenticationToken(loginDTO);
            try {
                user.login(token);
                if (user.isAuthenticated()) {
                    Faces.redirect("/index.xhtml");
                }
                return loginDTO;
            } catch (IncorrectCredentialsException e) {
                MessageUtils.returnMessageOnFail("Usuário ou senha incorreta");
            } catch (Exception e) {
                MessageUtils.returnMessageOnFail("Ocorreu um erro ao efetuar o login. Por favor, entre em contato com o suporte");
            }
        } else {
            MessageUtils.returnMessageOnFail(errorMsg);
        }
        return loginDTO;
    }

    private String validateFields(LoginDTO loginDto) {
        String message = null;
        if (StringUtils.isBlank(loginDto.getEmail()) && StringUtils.isBlank(loginDto.getSenha())) {
            message = "Por favor, preencha os campos";
        } else if (StringUtils.isBlank(loginDto.getEmail())) {
            message = "E-mail não informado";
        } else if (StringUtils.isBlank(loginDto.getSenha())) {
            message = "Senha não informada";
        }

        return message;
    }
}
