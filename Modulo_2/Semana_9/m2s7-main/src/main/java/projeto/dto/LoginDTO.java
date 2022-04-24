package projeto.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LoginDTO implements Serializable {

    private static final long serialVersionUID = 505864841350030607L;

    private Long id;

    private String email;

    private String senha;

    List<RoleDTO> roles = new ArrayList<>();

    public LoginDTO() {
    }

    public LoginDTO(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public LoginDTO(Long id, String email, String senha) {
        this.id = id;
        this.email = email;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginDTO loginDTO = (LoginDTO) o;
        return Objects.equals(id, loginDTO.id) && Objects.equals(email, loginDTO.email) && Objects.equals(senha, loginDTO.senha) && Objects.equals(roles, loginDTO.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, senha, roles);
    }
}
