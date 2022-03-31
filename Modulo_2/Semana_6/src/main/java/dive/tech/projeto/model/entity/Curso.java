package dive.tech.projeto.model.entity;

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

    private String nome;

    private List<Disciplina> disciplinas = new ArrayList<>();

    public Curso(int i) {
        this.id = (long) i;
        this.nome = "Curso " + i;
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

    public List<Disciplina> getDisciplinas() {
        return disciplinas;
    }

    public void setDisciplinas(List<Disciplina> disciplinas) {
        this.disciplinas = disciplinas;
    }

}
