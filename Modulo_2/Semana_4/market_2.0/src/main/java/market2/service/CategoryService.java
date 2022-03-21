package market2.service;

import market2.model.dao.CategoryDAO;
import market2.model.persistence.Category;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryService {

    private final Logger LOG = LogManager.getLogger(CategoryService.class);

    private EntityManager em;

    private CategoryDAO categoryDAO;

    public CategoryService(EntityManager em) {
        this.em = em;
        this.categoryDAO = new CategoryDAO(em);
    }

    public void create(Category category) {
        this.LOG.info("Preparando para a criação da categoria");

        this.LOG.info("Verificando se a categoria está nula...");
        validateNullCategory(category);

        this.LOG.info("Buscando se já existe categoria com nome: "+category.getName()+"...");
        Category category1 = this.findByName(category.getName());

        if(category1 != null) {
            this.LOG.info("Essa categoria já se encontra em nossa base de dados.");
            throw new EntityExistsException("Category already exists");
        }

        try {
            beginTransaction();
            this.categoryDAO.create(category);
            commitAndClose();
        }catch (Exception e) {
            this.LOG.error("Erro ao criar a categoria causado por: "+e.getMessage());
        }
        this.LOG.info("Criação realizada com sucesso!");

    }

    public void delete(Long id) {
        this.LOG.info("Preparando para encontrar a categoria...");

        this.LOG.info("Validando se o id informado é nulo...");
        validateNullId(id);

        this.LOG.info("Verificando se existe categoria com o id informado...");

        Category category = categoryDAO.getById(id);
        validateNullCategory(category);

        this.LOG.info("Categoria encontrada! Iniciando deleção...");

        try {
            beginTransaction();
            this.categoryDAO.delete(category);
            commitAndClose();
        }catch (Exception e) {
            this.LOG.error("Erro ao deletar categoria causado por: "+e.getMessage());
        }
        this.LOG.info("Deleção realizada com sucesso!");
    }

    public void update(Category newCategory, Long categoryId) {
        this.LOG.info("Verificando se a categoria informada é nula...");
        validateNullCategory(newCategory);
        this.LOG.info("Validando se o ID informado é nulo");
        validateNullId(categoryId);
        this.LOG.info("Validando existência da categoria com o Id informado...");
        Category category = this.categoryDAO.getById(categoryId);
        validateNullCategory(category);
        this.LOG.info("Produto encontrado! Iniciando atualização...");

        try {
            beginTransaction();
            this.categoryDAO.update(category);
            category.setName(newCategory.getName());
            commitAndClose();
        }catch (Exception e) {
            this.LOG.error("Erro ao atualizar o produto causado por: "+e.getMessage());
        }
        this.LOG.info("Atualização realizada com sucesso!");
    }

    public List<Category> listAll() {
        this.LOG.info("Preparando listagem das categorias...");
        List<Category> categories = this.categoryDAO.listAll();
        validadeNullList(categories);
        if(categories!=null) {
            this.LOG.info(categories.size()+" categoria(s) encontrada(s).");
        }
        return categories;
    }

    public List<Category> listByName(String name) {
        this.LOG.info("Verificando se o nome informado é nulo...");
        validateNullName(name);

        this.LOG.info("Preparando para listar os produtos com o nome "+name+"...");
        List<Category> categories = this.categoryDAO.listByName(name);
        this.LOG.info("Validando se a lista está vazia...");
        validadeNullList(categories);

        if(categories != null) {
            this.LOG.info(categories.size()+" categoria(s) encontrada(s) com o nome "+name);
        }
        return categories;
    }

    public Category findByName(String name) {
        this.LOG.info("Verificando se o nome informado é vazio ou nulo...");
        validateNullName(name);
        try {
           Category category = this.categoryDAO.findByName(name.toLowerCase());
            this.LOG.info("Categoria encontrada!");
            return category;

        }catch (NoResultException e) {
            this.LOG.info("Não foi encontrado uma categoria com esse nome.");
            return null;
        }catch (NonUniqueResultException e) {
            this.LOG.info("Foram encontrados mais de uma categoria com esse nome");
            return null;
        }
    }

    public Category getById(Long id) {
        this.LOG.info("Preparando para encontrar a categoria...");

        this.LOG.info("Verificando se o id informado é nulo...");
        validateNullId(id);

            this.LOG.info("Verificando se existe a categoria com o id informado...");
            Category category = this.categoryDAO.getById(id);
            validateNullCategory(category);

        if(category != null) {
            this.LOG.info("Categoria encontrada!");
        }
        return category;
    }

    private List<Category> validadeNullList(List<Category> categories) {
        if(categories == null) {
            this.LOG.info("Não foram encontradas categorias.");
            return new ArrayList<>();
        }
        return categories;
    }


    private void validateNullId(Long id) {
        if(id == null) {
            this.LOG.error("O id informado é nulo");
            throw new RuntimeException("Id cannot be null");
        }
    }

    private void validateNullCategory(Category category) {
        if(category == null) {
                this.LOG.error("Categoria não encontrada");
            throw new EntityNotFoundException("Category not found");
        }
    }


    private void validateNullName(String name) {
        if(name == null || name.isEmpty() || name.isBlank()) {
            this.LOG.error("O nome não pode ser nulo ou vazio");
            throw new RuntimeException("Name cannot be null or void");
        }
    }

    private void beginTransaction() {
        this.LOG.info("Iniciando transação...");
        this.em.getTransaction().begin();
    }

    private void commitAndClose() {
        this.LOG.info("Commitando e fechando transação...");
        this.em.getTransaction().commit();
        this.em.close();
    }

}
