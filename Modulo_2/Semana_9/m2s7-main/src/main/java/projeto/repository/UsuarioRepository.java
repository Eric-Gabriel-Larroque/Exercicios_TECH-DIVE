package projeto.repository;


import projeto.dto.LoginDTO;
import projeto.dto.RoleDTO;
import projeto.entity.Usuario;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository extends GenericRepository {

    private static final long serialVersionUID = 1L;

    public boolean existeAdministradorJaCadastrado() {
        return (boolean) entityManager.createNativeQuery("SELECT EXISTS(SELECT 1 FROM public.usuario " +
                "JOIN public.usuario_role ON usuario_role.id_usuario = usuario.id_usuario " +
                "JOIN public.role ON usuario_role.id_role = role.id_role " +
                "WHERE role.nome = 'ADMIN')")
                .getSingleResult();
    }

    public LoginDTO login(LoginDTO loginDto) {
        try {
            return entityManager.createNamedQuery(Usuario.GET_LOGINDTO_BY_EMAIL, LoginDTO.class)
                    .setParameter("email", loginDto.getEmail())
                    .getSingleResult();
        } catch (NoResultException e) {
            return loginDto;
        }
    }

    public List<RoleDTO> consultarRolesPorIdUsuario(Long idUsuario) {
        if (idUsuario == null) {
            return new ArrayList<>();
        }

        return entityManager.createQuery("SELECT new projeto.dto.RoleDTO(r.id, r.nome) " +
                "FROM Usuario u " +
                "JOIN u.roles r " +
                "WHERE u.id = :idUsuario", RoleDTO.class)
                .setParameter("idUsuario", idUsuario)
                .getResultList();
    }
}
