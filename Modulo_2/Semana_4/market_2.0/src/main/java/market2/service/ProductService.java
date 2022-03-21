package market2.service;

import market2.model.dao.ProductDAO;
import market2.model.persistence.Category;
import market2.model.persistence.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static market2.util.Input.*;

public class ProductService {

    private final Logger LOG = LogManager.getLogger(ProductService.class);

    private LinkedHashMap<Integer,Object> operations = new LinkedHashMap<>();

    private EntityManager em;

    private ProductDAO productDAO;

    private CategoryService categoryService;



    public ProductService(EntityManager em) {
        this.em = em;
        this.productDAO = new ProductDAO(em);
        this.categoryService = new CategoryService(em);
    }

    public void create(Product product) {
        this.LOG.info("Preparando a criação do produto...");

        validateNullProduct(product);

        String categoryName = product.getCategory().getName();
        this.LOG.info("Buscando se já existe categoria com nome: "+categoryName+"...");

        Category category = this.categoryService.findByName(categoryName);

        if(category!=null) {
            product.setCategory(category);
        }

        try {
            beginTransaction();
            this.productDAO.create(product);
            commitAndClose();
        }catch (Exception e) {
            this.LOG.error("Erro ao criar o produto causado por: "+e.getMessage());
        }
        this.LOG.info("O produto foi criado com sucesso!");
    }

    public void delete(Long id) {
        this.LOG.info("Preparando para encontrar o produto...");

        validateNullId(id);

        Product product = this.productDAO.getById(id);

        validateNullProduct(product);

        this.LOG.info("Produto encontrado! Realizando a deleção...");

        try {
            beginTransaction();
            this.productDAO.delete(product);
            commitAndClose();
        }catch (Exception e) {
            this.LOG.error("Erro ao deletar o produto causado por: "+e.getMessage());
        }
        this.LOG.info("Deleção realizada com sucesso!");
    }

    public void update(Product newProduct, Long productId) {
        this.LOG.info("Validando se o produto informado é nulo...");
        validateNullProduct(newProduct);
        this.LOG.info("Validando se o Id informado é nulo...");
        validateNullId(productId);
        this.LOG.info("Validando existência de produto com Id informado...");
        Product product = this.productDAO.getById(productId);
        validateNullProduct(product);
        this.LOG.info("Produto encontrado! Iniciando atualização...");

        try {
            beginTransaction();
            this.productDAO.update(product);
            product.setName(newProduct.getName());
            product.setDescription(newProduct.getDescription());
            product.setPrice(newProduct.getPrice());
            product.setCategory(this.categoryService.findByName(newProduct.getCategory().getName()));
            commitAndClose();
        }catch (Exception e) {
            this.LOG.error("Erro ao atualizar produto causado por: "+e.getMessage());
        }
        this.LOG.info("Atualização realizada com sucesso!");
    }

    public List<Product> listAll() {
        this.LOG.info("Preparando listagem dos produtos...");
        List<Product> products = this.productDAO.listAll();
        validateNullList(products);
        if(products!=null) {
            this.LOG.info("Foram encontrados "+products.size()+" produtos:\n");
        }
        return products;
    }

    public List<Product> listByName(String name) {
        this.LOG.info("Verificando se o nome não está nulo...");
        validateNullName(name);

        this.LOG.info("Preparando para listar os produtos com o nome "+name+"...");
        List<Product> products = this.productDAO.listByName(name);
        this.LOG.info("Validando se a lista está vazia...");
        validateNullList(products);

        if(products!=null) {
            this.LOG.info("Foram encontrados "+products.size()+" produtos com o nome "+name);
        }
        return products;
    }

    public Product findByName(String name) {
        this.LOG.info("Validando se o nome informado é nulo ou vazio...");
        validateNullName(name);

        try {
            return this.productDAO.findByName(name.toLowerCase());
        }catch (NoResultException e) {
            this.LOG.error("Não foi encontrado um produto com esse nome.");
            return null;
        }catch (NonUniqueResultException e) {
            this.LOG.info("Foram encontrados mais de um produto com esse nome");
            return null;
        }
    }

    public Product getById(Long id) {
        this.LOG.info("Verificando se o id informado é nulo...");
        validateNullId(id);

        this.LOG.info("Verificando se existe produto com o id informado...");
        Product product = this.productDAO.getById(id);
        validateNullProduct(product);
        return product;
    }

    public List<Product> validateNullList(List<Product> products) {
        if(products == null) {
            this.LOG.info("Não foram encontrados produtos");
            return new ArrayList<>();
        }
        return products;
    }

    private void validateNullProduct(Product product) {
        if(product == null) {
            this.LOG.error("O produto não foi encontrado");
            throw new EntityNotFoundException("Product not found");
        }
    }

    private void validateNullName(String name) {
        if(name == null || name.isEmpty() || name.isBlank()) {
            this.LOG.info("O nome não pode ser nulo ou vazio");
            throw new RuntimeException("Name cannot be null or empty");
        }
    }

    private void validateNullId(Long id) {
        if(id == null) {
            this.LOG.error("O id informado é nulo");
            throw new RuntimeException("The id is null");
        }
    }

    private void beginTransaction() {
        this.LOG.info("Abrindo transação");
        this.em.getTransaction().begin();
    }

    private void commitAndClose() {
        this.LOG.info("Commitando e fechando transação...");
        this.em.getTransaction().commit();
        this.em.close();
    }

    public void operations() {
        String[] operation = {"Criar produto","Deletar produto","Atualizar um produto",
                "Listar todos os produtos","Listar pelo nome","Buscar um produto pelo nome",
                "Buscar um produto pelo Id"};


        for(int i = 1; i < operation.length;i++) {
            this.operations.put(i,operation);
        }

        int chosenOperation = chooseOption("Bem-vindo(a), o que deseja fazer?",operation);
        this.operations.get(1);
    }

}