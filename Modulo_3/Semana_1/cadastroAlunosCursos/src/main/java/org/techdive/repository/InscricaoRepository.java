package org.techdive.repository;

import org.techdive.model.entity.Inscricao;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@RequestScoped
@Transactional
public class InscricaoRepository implements Serializable {

    @PersistenceContext(unitName = "EXM03S01")
    EntityManager em;

    public void inserirInscricao(Inscricao inscricao) {
        em.persist(inscricao);
    }

    public List<Inscricao> obterCursos() {

        String jpql = "SELECT i FROM Inscricao i";
        return em.createQuery(jpql,Inscricao.class).getResultList();
    }

    public Optional<Inscricao> obterInscricaoPorId(Integer id) {
        Inscricao inscricao = em.find(Inscricao.class,id);
        return inscricao != null ? Optional.of(inscricao) : Optional.empty();
    }

    public void deletarInscricao(Integer id) {
        Inscricao inscricao = em.find(Inscricao.class,id);
        em.remove(inscricao);
    }
}
