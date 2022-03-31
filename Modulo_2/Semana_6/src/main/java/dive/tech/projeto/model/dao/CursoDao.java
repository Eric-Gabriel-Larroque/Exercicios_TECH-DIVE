package dive.tech.projeto.model.dao;

import dive.tech.projeto.model.entity.Curso;
import dive.tech.projeto.model.entity.Disciplina;

import java.util.ArrayList;
import java.util.List;

import static dive.tech.projeto.model.dao.DisciplinaDao.disciplinas;

public class CursoDao {

        private static List<Curso> cursos = new ArrayList<>();

        private DisciplinaDao disciplinaDao = new DisciplinaDao();

    /**
     * Essa lista retornará três cursos: "Curso 1", "Curso 2" e "Curso 3".
     * Cada curso terá três disciplinas, e cada disciplina terá o nome "Disciplina X do Curso Y".
     * Os cursos estão no seguinte formado:
     * [
     *     {
     *         "id": 1,
     *         "nome": "Curso 1",
     *         "disciplinas": [
     *             {
     *                 "id": 1,
     *                 "nome": "Disciplina 1 do Curso 1"
     *             },
     *             {
     *                 "id": 2,
     *                 "nome": "Disciplina 2 do Curso 1"
     *             },
     *             {
     *                 "id": 3,
     *                 "nome": "Disciplina 3 do Curso 1"
     *             }
     *         ]
     *     },
     *     {
     *         "id": 2,
     *         "nome": "Curso 2",
     *         "disciplinas": [
     *             {
     *                 "id": 1,
     *                 "nome": "Disciplina 1 do Curso 2"
     *             },
     *             {
     *                 "id": 2,
     *                 "nome": "Disciplina 2 do Curso 2"
     *             },
     *             {
     *                 "id": 3,
     *                 "nome": "Disciplina 3 do Curso 2"
     *             }
     *         ]
     *     },
     *     {
     *         "id": 3,
     *         "nome": "Curso 3",
     *         "disciplinas": [
     *             {
     *                 "id": 1,
     *                 "nome": "Disciplina 1 do Curso 3"
     *             },
     *             {
     *                 "id": 2,
     *                 "nome": "Disciplina 2 do Curso 3"
     *             },
     *             {
     *                 "id": 3,
     *                 "nome": "Disciplina 3 do Curso 3"
     *             }
     *         ]
     *     }
     * ]
     */
    public List<Curso> obterCursos() {

        for (int i = 1; i < 4; i++) {
            Curso curso = new Curso(i);

            for (int j = 1; j < 4; j++) {
                Disciplina disciplina = new Disciplina(curso.getNome(), j);
                curso.getDisciplinas().add(disciplina);
            }

            cursos.add(curso);
        }
        return cursos;
    }

    public Curso criarCurso(Curso curso) {
        long listSize =  cursos.size()==0?0L:cursos.size();


        for(Disciplina disciplina: curso.getDisciplinas()) {
            if(!disciplinas.contains(disciplina)) {
                disciplinaDao.createDisciplina(disciplina);
            }
        }

        curso.setNome(curso.getNome());
        curso.setId((listSize+1));
        curso.setDisciplinas(curso.getDisciplinas());
        cursos.add(curso);
        return curso;
    }
}
