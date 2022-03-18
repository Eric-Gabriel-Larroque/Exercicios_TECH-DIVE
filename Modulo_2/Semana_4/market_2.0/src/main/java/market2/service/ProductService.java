package market2.service;

import market2.model.persistence.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;

public class ProductService {

    private final Logger LOG = LogManager.getLogger(ProductService.class);

    private EntityManager em;

    public ProductService(EntityManager em) {
        this.em = em;
    }

    public void create(Product product) {
        this.LOG.info("Preparando a criação do produto...");
    }
}
