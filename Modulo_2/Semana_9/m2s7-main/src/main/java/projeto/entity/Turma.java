package projeto.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NamedQuery(
        name = Turma.GET_TURMASDTO,
        query = "SELECT new projeto.dto.TurmaDTO(t.idTurma, t.nome) " +
                "FROM Turma t " +
                "ORDER BY t.idTurma")
public class Turma {

    public static final String GET_TURMASDTO = "GET_TURMASDTO";

    @Id
    @GeneratedValue
    @Column(name = "id_turma")
    private Long idTurma;

    private String nome;

    private Date dataInicio;

    private Date dataTermino;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "turma")
    private List<Estudante> estudantes = new ArrayList<>();

    public Long getIdTurma() {
        return idTurma;
    }

    public void setIdTurma(Long idTurma) {
        this.idTurma = idTurma;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public List<Estudante> getEstudantes() {
        return estudantes;
    }

    public void setEstudantes(List<Estudante> estudantes) {
        this.estudantes = estudantes;
    }
}
