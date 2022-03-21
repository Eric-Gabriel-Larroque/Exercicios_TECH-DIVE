package market2.model.dao;

import javax.persistence.EntityManager;
import java.util.List;
import market2.model.persistence.Client;

public class ClientDAO {

    private EntityManager em;

    public ClientDAO(EntityManager em) {
        this.em = em;
    }

    public void create(Client client) {
        this.em.persist(client);
    }

    public void delete(Client client) {
        this.em.remove(convertToMerge(client));
    }

    public void update(Client client) {
        convertToMerge(client);
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

    public Client findByCPF(String cpf) {
        String jpql = "SELECT c FROM Client AS c WHERE cpf =:cpf";

        return this.em
                .createQuery(jpql,Client.class)
                .setParameter("cpf",cpf)
                .getSingleResult();
    }

    public Client getById(Long id) {
        return this.em.find(Client.class,id);
    }

    public Client convertToMerge(Client client) {
        return this.em.merge(client);
    }

}
