package market2.view;

import market2.model.persistence.Category;
import market2.service.CategoryService;
import market2.util.Input;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class CategoryView extends Input {

    private EntityManager em;

    private CategoryService categoryService;

    public CategoryView(EntityManager em) {
        this.em = em;
        this.categoryService = new CategoryService(em);
    }

    public CategoryView() {
        this.em = em;
        this.categoryService = new CategoryService(em);
    }

    public void create() {
        String categoryName = validateString("Insira o nome da categoria a ser criada\n--> ");
        this.categoryService.create(new Category(categoryName));
    }

    public void delete() {
        long id = validateNumber("Insira o id da categoria a ser deletada\n--> ");
        this.categoryService.delete(id);
    }

    public void update() {
        String categoryName = validateString("Insira o nome da nova categoria\n--> ");
        long id = validateNumber("Insira o id da categoria a ser atualizada\n--> ");

        this.categoryService.update(new Category(categoryName), id);
    }

    public void listAll() {

        List<Category> categories = this.categoryService.listAll();
        categories.forEach(System.out::println);
    }

    public void listByName() {
        String name = validateString("Insira o nome da(s) categoria(s) a ser(em) listada(s)\n--> ");
        List<Category> products = this.categoryService.listByName(name);
        products.forEach(System.out::println);
    }

    public void findByName() {
        String name = validateString("Insira o nome da categoria a ser procurada\n--> ");
        Category category = this.categoryService.findByName(name);
        System.out.println(category);
    }

    public void getById() {
        long id = validateNumber("Insira o id da categoria a ser procurada\n--> ");
        Category category = this.categoryService.getById(id);
        System.out.println(category);
    }

    public void operations(int chosenOperation) {

        switch (chosenOperation) {
            case 0 -> create();
            case 1 -> delete();
            case 2 -> update();
            case 3 -> listAll();
            case 4 -> listByName();
            case 5 -> findByName();
            case 6 -> getById();
            case 7 -> {
                System.out.println("Volte sempre!:)");
                System.exit(0);
            }
        }
    }

    public void chooseOperation() {
        String[] operations = {
                "Criar uma nova categoria","Deletar uma categoria",
                "Atualizar uma categoria","Listar todas as categorias",
                "Listar categorias pelo nome",
                "Encontrar uma categoria pelo nome",
                "Encontrar uma categoria pelo Id",
                "Sair"
        };
        int chosenOperation =  chooseOption("O que deseja fazer?",operations);
        operations(chosenOperation);
    }
}