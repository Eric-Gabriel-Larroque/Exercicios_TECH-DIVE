package com.techdive.cadastrodecursos.repository;


import com.techdive.cadastrodecursos.model.Usuario;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class UsuarioRepository {

    List<Usuario> usuarios = new ArrayList<>();

    public UsuarioRepository() {
        usuarios.add(new Usuario("adm@email.com","senha123","John Doe"));
        usuarios.add(new Usuario("user@email.com","senha321", "Janny Doe"));
    }

    public List<Usuario> obterUsuarios() {
        return usuarios.stream()
                .sorted(Comparator.comparing(Usuario::getNome))
                .collect(Collectors.toList());
    }

    public boolean validarCredenciais(Usuario usuarioSelecionado) {

        boolean isUsuarioValido = usuarios.stream().filter(u->u.getLogin().equals(usuarioSelecionado.getLogin()))
                                .anyMatch(u->u.getSenha().equals(usuarioSelecionado.getSenha()));
        if(isUsuarioValido) {

            String nome = usuarios.stream().filter(u->u.getLogin().equals(usuarioSelecionado.getLogin())&&
                    u.getSenha().equals(usuarioSelecionado.getSenha()))
                            .findFirst().get().getNome();
            usuarioSelecionado.setNome(nome);
        }
        return isUsuarioValido;
    }
}
