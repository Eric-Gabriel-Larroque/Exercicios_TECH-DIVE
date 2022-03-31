package dive.tech.projeto.service;

import dive.tech.projeto.model.dao.CursoDao;
import dive.tech.projeto.model.entity.Curso;

import java.util.List;


public class CursoService {

    CursoDao cursoDao = new CursoDao();

    public List<Curso> obterCursos() {
        return cursoDao.obterCursos();
    }

    // Crie os métodos aqui!

    public Curso criarCurso(Curso curso) {
        return cursoDao.criarCurso(curso);
    }
}
