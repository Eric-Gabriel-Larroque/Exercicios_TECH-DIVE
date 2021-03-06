package market2.model.dao;

import javax.persistence.EntityManager;

import market2.model.persistence.Category;

import java.util.List;

public class CategoryDAO {

    private EntityManager em;

    public CategoryDAO(EntityManager em) {
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

    public List<Category> listAll() {
        String jpql = "SELECT c FROM Category AS c";
        return this.em
                .createQuery(jpql,Category.class)
                .getResultList();
    }

    public List<Category> listByName(String name) {
        String jpql = "SELECT c FROM Category AS c WHERE name =:name";
        return this.em
                .createQuery(jpql,Category.class)
                .setParameter("name",name)
                .getResultList();
    }

    public Category findByName(String name) {
        String jpql = "SELECT c FROM Category AS c WHERE c.name =:name";

        return this.em.
                createQuery(jpql, Category.class)
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
