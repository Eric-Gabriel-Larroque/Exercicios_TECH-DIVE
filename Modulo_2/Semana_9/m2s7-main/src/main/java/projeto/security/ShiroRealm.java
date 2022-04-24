package projeto.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.realm.SimpleAccountRealm;
import projeto.dto.LoginDTO;
import projeto.repository.UsuarioRepository;
import projeto.utils.StringUtils;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public class ShiroRealm extends SimpleAccountRealm {

    @Inject
    private UsuarioRepository usuarioRepository;

    public ShiroRealm() {
        setName("loginDTO");
    }

    /*
        Aqui é onde acontece a autenticação em si. Como podemos imaginar pelo @Override, esse é um método chamado pelo Shiro
        por baixo dos panos, e acontece quando chamamos o "user.login(token)" dentro do método "login" da classe UsuarioBusiness.
        Aqui, o sistema pega o objeto LoginDTO que passamos em forma de AuthenticationToken (porque é esse objeto que o Shiro usa)
        e buscamos ele no banco de dados para ver se o usuário informado realmente existe.
        Se existe (loginDTO.getId() != null), o sistema verifica se a senha informada é a mesma que existe no banco.
        Se for verdadeiro, o sistema busca as roles no banco de dados e cria um objeto do tipo SimpleAuthenticationInfo,
        que representa um usuário autenticado (logado) no sistema.
    */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UserAuthenticationToken authToken = (UserAuthenticationToken) token;
        LoginDTO loginDTO = usuarioRepository.login(authToken.getLoginDTO());

        if (loginDTO.getId() != null && StringUtils.validatePassword(loginDTO.getSenha(), authToken.getLoginDTO().getSenha())) {
            Map<String, String> credentials = new HashMap<>();
            credentials.put("email", loginDTO.getEmail());
            credentials.put("senha", loginDTO.getSenha());

            // Set the hashed password so Shiro can compare loginDTO correctly
            authToken.getLoginDTO().setSenha(loginDTO.getSenha());
            authToken.getLoginDTO().setRoles(usuarioRepository.consultarRolesPorIdUsuario(loginDTO.getId()));

            return new SimpleAuthenticationInfo(authToken.getLoginDTO(), credentials, ShiroRealm.class.getName());
        } else {
            throw new IncorrectCredentialsException();
        }
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return UserAuthenticationToken.class.equals(token.getClass());
    }
}