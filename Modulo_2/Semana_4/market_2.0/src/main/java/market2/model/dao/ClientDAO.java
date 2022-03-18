package market2.model.dao;

import market2.model.persistence.Category;
import market2.model.persistence.Client;

import javax.persistence.EntityManager;
import javax.print.DocFlavor;
import java.util.List;

public class ClientDAO {

    private EntityManager em;

    public ClientDAO(EntityManager em) {
        this.em = em;
    }

    public void create(Category category) {
        this.em.persist(category);
    }

    public void delete(Category category) {
        this.em.remove(convertToMerge(category));
    }

    public void update(Category category) {
        convertToMerge(category);
    }

    public List<Client> listAll() {
        String jpql = "SELECT c FROM Client AS c";

        return this.em
                .createQuery(jpql,Client.class)
                .getResultList();
    }

    public List<Client> listByName(String name) {
        String jpql = "SELECT c FROM Client AS c WHERE name =:name";

        return  this.em.
                createQuery(jpql,Client.class)
                .setParameter("name",name)
                .getResultList();
    }

    public Client findByName(String name) {
        String jpql = "SELECT c FROM Client AS c WHERE name =:name";

        return this.em
                .createQuery(jpql,Client.class)
                .setParameter("name",name)
                .getSingleResult();
    }

    public Category getById(Long id) {
        return this.em.find(Category.class,id);
    }

    public Category convertToMerge(Category category) {
        return this.em.merge(category);
    }

}
