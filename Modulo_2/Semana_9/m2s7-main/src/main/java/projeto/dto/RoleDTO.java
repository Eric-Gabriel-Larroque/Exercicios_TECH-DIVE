package projeto.dto;

import java.io.Serializable;
import java.util.Objects;

public class RoleDTO implements Serializable {

    private static final long serialVersionUID = 505864841350030607L;

    private Long idRole;

    private String nome;

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public RoleDTO() {
    }

    public RoleDTO(Long idRole, String nome) {
        this.idRole = idRole;
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDTO roleDTO = (RoleDTO) o;
        return Objects.equals(idRole, roleDTO.idRole) && Objects.equals(nome, roleDTO.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRole, nome);
    }
}
