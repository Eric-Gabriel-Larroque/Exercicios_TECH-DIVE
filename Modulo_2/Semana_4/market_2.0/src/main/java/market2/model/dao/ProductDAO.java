package market2.model.dao;

import market2.model.persistence.Product;

import javax.persistence.EntityManager;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;

public class ProductDAO {

    private EntityManager em;

    public ProductDAO(EntityManager em) {
        this.em = em;
    }

    public void create(Product product) {
        this.em.persist(product);
    }

    public void delete(Product product) {
        this.em.remove(convertToMerge(product));
    }

    public void update(Product product) {
        convertToMerge(product);
    }

    public List<Product> listAll() {
        String jpql = "SELECT p FROM Product AS p";
        return this.em
                .createQuery(jpql,Product.class)
                .getResultList();
    }

    public List<Product> listByName(String name) {
        String jpql = "SELECT p FROM Product AS p WHERE p.name =:name";

        return this.em
                .createQuery(jpql,Product.class)
                .setParameter("name",name)
                .getResultList();
    }

    public Product findByName(String name) {
        String jpql = "SELECT p FROM Product AS p WHERE p.name =:name";

        return this.em
                .createQuery(jpql,Product.class)
                .setParameter("name",name)
                .getSingleResult();
    }

    public Product getById(Long id) {
        return this.em.find(Product.class,id);
    }

    public Product convertToMerge(Product product) {
        return this.em.merge(product);
    }
}