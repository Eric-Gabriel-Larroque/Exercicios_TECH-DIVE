package org.techdive.repository;

import org.techdive.model.entity.Inscricao;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;

@RequestScoped
@Transactional
public class InscricaoRepository implements Serializable {

    @PersistenceContext(unitName = "EXM03S01")
    EntityManager em;

    public void inserirInscricao(Inscricao inscricao) {
        em.persist(inscricao);
    }
}
