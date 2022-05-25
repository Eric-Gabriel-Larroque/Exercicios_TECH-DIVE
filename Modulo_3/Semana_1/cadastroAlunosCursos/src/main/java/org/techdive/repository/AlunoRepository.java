package org.techdive.repository;

import org.techdive.model.entity.Aluno;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@RequestScoped
@Transactional
public class AlunoRepository implements Serializable {

    @PersistenceContext(unitName = "EXM03S01")
    EntityManager em;

    public List<Aluno> obterAlunos(String nome) {
        TypedQuery<Aluno> query = popularCampoNome(nome);
        query = nome!=null&&!nome.isBlank() ? query.setParameter("nome",nome) : query;
        return query.getResultList();
    }

    private TypedQuery<Aluno> popularCampoNome(String nome) {
        String jpql = "SELECT a FROM Aluno a ";
        jpql = nome!=null&&!nome.isBlank() ? jpql.concat("WHERE a.nome LIKE :nome") : jpql;
        return em.createQuery(jpql,Aluno.class);
    }
}
