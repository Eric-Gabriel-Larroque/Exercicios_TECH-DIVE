package dive.tech.projeto.model.entity;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Curso {


    /*
    Isso faz parte da serialização/desserialização da classe Curso.
    Ou seja: O sistema automaticamente pega os dados do request (requisição),
    cria um objeto do tipo Curso e coloca os dados dentro dele.
     */
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    @NotBlank
    @NotEmpty
    private String nome;

    private List<@Valid Disciplina> disciplinas = new ArrayList<>();

    public Curso(int i) {
        this.id = (long) i;
        this.nome = "Curso " + i;
    }

    public Curso(){}

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

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", disciplinas=" + disciplinas +
                '}';
    }
}
