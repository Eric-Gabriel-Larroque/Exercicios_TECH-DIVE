package projeto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Role {

    public static final String ADMIN = "ADMIN";

    @Id
    @GeneratedValue
    @Column(name = "id_role")
    private Long idRole;

    @Column(name = "nome")
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
}
