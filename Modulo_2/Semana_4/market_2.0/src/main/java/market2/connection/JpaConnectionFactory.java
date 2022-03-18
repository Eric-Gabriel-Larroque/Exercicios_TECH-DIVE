package market2.connection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaConnectionFactory {

    private EntityManagerFactory entityManagerFactory;

    public JpaConnectionFactory(){
        this.entityManagerFactory = Persistence.createEntityManagerFactory("market_2.0");
    }

    public EntityManager getEntityManage() {
        return this.entityManagerFactory.createEntityManager();
    }
}