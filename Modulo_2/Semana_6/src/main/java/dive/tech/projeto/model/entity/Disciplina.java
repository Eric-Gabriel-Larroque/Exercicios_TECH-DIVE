package dive.tech.projeto.model.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class Disciplina implements Serializable{

    /*
    Isso faz parte da serialização/desserialização da classe Disciplina.
    Ou seja: O sistema automaticamente pega os dados do request (requisição),
    cria um objeto do tipo Disciplina e coloca os dados dentro dele.
     */
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    @NotEmpty
    @NotBlank
    private String nome;

    public Disciplina(String nomeCurso, int i) {
        this.id = (long) i;
        this.nome = "Disciplina " + i + " do " + nomeCurso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Disciplina{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
