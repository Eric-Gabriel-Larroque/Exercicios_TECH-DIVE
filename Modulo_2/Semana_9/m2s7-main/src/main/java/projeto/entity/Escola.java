package projeto.entity;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Entity
@NamedQuery(
        name = Escola.GET_ESCOLASDTO,
        query = "SELECT new projeto.dto.EscolaDTO(e.idEscola, e.nome) " +
                "FROM Escola e " +
                "ORDER BY e.idEscola")
public class Escola {

    public static final String GET_ESCOLASDTO = "GET_ESCOLASDTO";

    @Id
    @GeneratedValue
    @Column(name = "id_escola")
    private Long idEscola;

    @NotBlank
    private String nome;


    @ManyToOne
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;

    private Date criacao;


    @OneToMany(mappedBy = "escola", fetch = FetchType.LAZY)
    List<Turma> turmas;

    public Long getIdEscola() {
        return idEscola;
    }

    public void setIdEscola(Long idEscola) {
        this.idEscola = idEscola;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Date getCriacao() {
        return criacao;
    }

    public void setCriacao(Date criacao) {
        this.criacao = criacao;
    }

    public List<Turma> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<Turma> turmas) {
        this.turmas = turmas;
    }
}
