package projeto.dto;

import projeto.entity.Escola;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class EscolaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idEscola;

    private String nome;

    private Long idEndereco;

    private Date criacao;

    private List<TurmaDTO> turmas;

    public EscolaDTO() {}

    public EscolaDTO(Escola escola) {
        this.idEscola = escola.getIdEscola();
        this.nome = escola.getNome();
        this.idEndereco = escola.getEndereco() != null ? escola.getEndereco().getIdEndereco()  :null;
        this.criacao = escola.getCriacao();
        this.turmas = escola.getTurmas()
                .stream()
                .map(TurmaDTO::new)
                .sorted(Comparator.comparing(TurmaDTO::getNome))
                .collect(Collectors.toList());
    }

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

    public Long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }

    public Date getCriacao() {
        return criacao;
    }

    public void setCriacao(Date criacao) {
        this.criacao = criacao;
    }

    public List<TurmaDTO> getTurmas() {
        return turmas;
    }

    public void setTurmas(List<TurmaDTO> turmas) {
        this.turmas = turmas;
    }
}
