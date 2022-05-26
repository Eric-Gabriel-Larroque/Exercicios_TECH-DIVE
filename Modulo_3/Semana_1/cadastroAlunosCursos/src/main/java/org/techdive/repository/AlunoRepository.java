package org.techdive.repository;

import org.techdive.model.entity.Aluno;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequestScoped
@Transactional
public class AlunoRepository implements Serializable {

    @PersistenceContext(unitName = "EXM03S01")
    EntityManager em;

    public List<Aluno> obterAlunos(String nome) {
        TypedQuery<Aluno> query = montarHqlNomeAluno(nome);
        query = nome!=null&&!nome.isBlank() ? query.setParameter("nome",nome) : query;
        return query.getResultList();
    }

    private TypedQuery<Aluno> montarHqlNomeAluno(String nome) {
        String jpql = "SELECT a FROM Aluno a ";
        jpql = nome!=null&&!nome.isBlank() ? jpql.concat("WHERE a.nome LIKE :nome") : jpql;
        return em.createQuery(jpql,Aluno.class);
    }

    public Optional<Aluno> obterAlunoPorMatricula(int matricula) {
        Aluno aluno = em.find(Aluno.class,matricula);
        return aluno != null ? Optional.of(aluno) : Optional.empty();
    }

    public void inserirAluno(Aluno aluno) {
        em.persist(aluno);
    }

    public void atualizar(Aluno aluno) {
        Aluno alunoBD = em.find(Aluno.class, aluno.getMatricula());
        alunoBD.setMatricula(aluno.getMatricula());
        alunoBD.setNome(aluno.getNome());
        em.merge(alunoBD);
    }

    public void deletarAluno(Integer matricula) {
        Aluno aluno = em.find(Aluno.class, matricula);
        em.remove(aluno);
    }

}
