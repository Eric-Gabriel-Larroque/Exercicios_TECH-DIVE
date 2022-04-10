package projeto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Role {

    private static final String ADMIN = "ADMIN";

    @Id
    @GeneratedValue
    @Column(name = "id_role")
    private Long idRole;

    @Column(name = "nome")
    private String nome;

    public Long getId() {
        return idRole;
    }

    public void setId(Long id) {
        this.idRole = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
