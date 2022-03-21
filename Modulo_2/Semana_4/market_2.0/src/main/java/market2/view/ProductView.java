package market2.view;

import market2.model.persistence.Category;
import market2.model.persistence.Product;
import market2.service.ProductService;
import market2.util.Input;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class ProductView extends Input {

    private EntityManager em;

    private final Scanner INPUT = new Scanner(System.in);


    private ProductService productService;

    public ProductView(EntityManager em) {
        this.em = em;
        this.productService = new ProductService(em);
    }

    public ProductView() {
        this.em = em;
        this.productService = new ProductService(em);
    }

    public Void create() {
        Product product = createProduct();

        this.productService.create(product);
        return null;
    }

    public void delete() {
        long id = validateNumber("Insira o id do produto a ser deletado\n--> ");
        this.productService.delete(id);
    }

    public void update() {
        Product product = createProduct();
        long id = validateNumber("Insira o id do produto a ser atualizado\n--> ");

        this.productService.update(product, id);
    }

    public void listAll() {

        List<Product> products = this.productService.listAll();
        products.forEach(System.out::println);
    }

    public void listByName() {
        String name = validateString("Insira o nome do(s) produto(s) a ser(em) listado(s)\n--> ");
        List<Product> products = this.productService.listByName(name);
        products.forEach(System.out::println);
    }

    public void findByName() {
        String name = validateString("Insira o nome do produto a ser procurado\n--> ");
        Product product = this.productService.findByName(name);
        System.out.println(product);
    }

    public void getById() {
        long id = validateNumber("Insira o id do produto a ser procurado\n--> ");
        Product product = this.productService.getById(id);
        System.out.println(product);
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
                "Criar um novo produto","Deletar um produto",
                "Atualizar um produto","Listar todos os produtos",
                "Listar produtos pelo nome",
                "Encontrar um produto pelo nome",
                "Encontrar um produto pelo Id",
                "Sair"
        };
        int chosenOperation =  chooseOption("O que deseja fazer?",operations);
        operations(chosenOperation);
    }

    public Product createProduct() {
        String name = validateString("Insira o nome do produto\n--> ");
        System.out.print("Insira uma descricao para o produto\n--> ");
        String description = INPUT.nextLine();
        BigDecimal price = BigDecimal.valueOf(validateDouble("Insira o valor do produto\n--> "));
        String category = validateString("insira o nome da categoria do produto\n--> ");

        return new Product(name, description, price, new Category(category));
    }
}