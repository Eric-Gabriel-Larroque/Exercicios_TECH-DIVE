package projeto.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(
        name = Usuario.GET_LOGINDTO_BY_EMAIL,
        query = "SELECT new projeto.dto.LoginDTO(u.idUsuario, u.email, u.senha) " +
                "FROM Usuario u " +
                "WHERE u.email = :email")
public class Usuario {

    public static final String GET_LOGINDTO_BY_EMAIL = "GET_LOGINDTO_BY_EMAIL";

    @Id
    @GeneratedValue
    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "usuario_role", joinColumns = {
            @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    }, inverseJoinColumns = {
            @JoinColumn(name = "id_role", referencedColumnName = "id_role")
    })
    private List<Role> roles = new ArrayList<>();

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
