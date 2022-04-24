package projeto.service;

import org.apache.commons.lang3.StringUtils;
import projeto.business.UsuarioBusiness;
import projeto.dto.LoginDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Stateless
public class UsuarioService {

    @Inject
    private UsuarioBusiness usuarioBusiness;

    List<String> nomesUsados = new ArrayList<>();

    public String buscarNome() {
        return nomesUsados.isEmpty() ? null : nomesUsados.get(0);
    }

    public void salvarNome(String nome) throws Exception {
        if (StringUtils.isBlank(nome)) {
            throw new Exception("Nome em branco!");
        }

        nomesUsados.add(nome.trim());
        nomesUsados.sort(Comparator.reverseOrder());
    }

    public List<String> buscarNomesUsados() {
        return nomesUsados;
    }

    public void criarUsuarioAdmin() {
        usuarioBusiness.criarUsuarioAdmin();
    }

    public LoginDTO login(LoginDTO login) throws Exception {
        return usuarioBusiness.login(login);
    }
}
