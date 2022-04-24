package projeto.dto;

import projeto.entity.Estudante;

import java.io.Serializable;
import java.util.Date;

public class EstudanteDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idEstudante;

    private Long idTurma;

    private String nome;

    private String email;

    private Date dataNascimento;

    public EstudanteDTO() {
    }

    public EstudanteDTO(Long idEstudante, String nome) {
        this.idEstudante = idEstudante;
        this.nome = nome;
    }

    public EstudanteDTO(Estudante estudante) {
        this.idEstudante = estudante.getIdEstudante();
        this.idTurma = estudante.getTurma() != null ? estudante.getTurma().getIdTurma() : null;
        this.nome = estudante.getNome();
        this.email = estudante.getEmail();
        this.dataNascimento = estudante.getDataNascimento();
    }

    public Long getIdEstudante() {
        return idEstudante;
    }

    public void setIdEstudante(Long idEstudante) {
        this.idEstudante = idEstudante;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
