package com.techdive.cadastrodecursos.repository;


import com.techdive.cadastrodecursos.model.Usuario;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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

    public boolean validarCredenciais(String email, String senha) {

        boolean isUsuarioValido = usuarios.stream().filter(u->u.getEmail().equals(email))
                                .anyMatch(u->u.getSenha().equals(senha));
        if(isUsuarioValido) {

        }
        return isUsuarioValido;
    }

    public Usuario obterPor(String email, String senha) {
        Usuario user = usuarios.stream().filter
                (u->u.getSenha().equals(senha))
                .filter(u->u.getEmail().equals(email))
                .findFirst().get();

        return new Usuario(user.getEmail(), user.getNome());
    }
}
