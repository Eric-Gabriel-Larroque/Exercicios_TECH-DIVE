package org.techdive.repository;

import org.techdive.model.entity.Curso;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequestScoped
@Transactional
public class CursoRepository implements Serializable {

    @PersistenceContext(unitName = "EXM03S01")
    EntityManager em;


    public List<Curso> obterCursos(String sortBy, int limit) {
        Query query = montarHqlBuscaCurso(sortBy);
        query = limit > 0 ? query.setMaxResults(limit) : query;
        return query.getResultList();
    }


    private TypedQuery<Curso> montarHqlBuscaCurso(String sortBy) {
        String jpql = "SELECT c FROM Curso c ";
        if(sortBy!=null&&!sortBy.isBlank()) {
            jpql = jpql.concat("ORDER BY c."+sortBy+" ");
        }
        return em.createQuery(jpql, Curso.class);
    }

    public Optional<Curso> obterCursoPeloCodigo(String codigo) {
        Curso curso = em.find(Curso.class,codigo);
        return curso != null ? Optional.of(curso) : Optional.empty();
    }

    public void inserirCurso(Curso curso) {
        em.persist(curso);
    }

    public void atualizarCurso(Curso cursoEncontrado) {
        Curso cursoBD = obterCursoPeloCodigo(cursoEncontrado.getCodigo()).get();
        cursoBD.setAssunto(cursoEncontrado.getAssunto());
        cursoBD.setDuracao(cursoEncontrado.getDuracao());
    }

    public void deletarCurso(String codigo) {
        Curso curso = em.find(Curso.class, codigo);
        em.remove(curso);
    }
}
