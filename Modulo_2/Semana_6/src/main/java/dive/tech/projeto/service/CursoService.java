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

    public Curso obterCursoPeloId(Long id) {
        return cursoDao.obterCursoPeloId(id);
    }

    public Curso obterCursoPeloNome(String nome) {
        return cursoDao.obterCursoPeloNome(nome);
    }


    public Curso criarCurso(Curso curso) {
        return cursoDao.criarCurso(curso);
    }

    public Curso atualizarCurso(Curso curso) {
        return cursoDao.atualizarCurso(curso);
    }

    public List<Curso> deletarCurso(Long id) {
        return cursoDao.deletarCurso(id);
    }
}
