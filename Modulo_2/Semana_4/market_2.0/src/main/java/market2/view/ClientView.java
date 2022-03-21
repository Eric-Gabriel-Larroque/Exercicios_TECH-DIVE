package market2.view;

import market2.model.persistence.Client;
import market2.service.ClientService;
import market2.util.Input;

import javax.persistence.EntityManager;
import java.util.List;

public class ClientView extends Input {

    private EntityManager em;

    private ClientService clientService;

    public ClientView(EntityManager em) {
        this.em = em;
        this.clientService = new ClientService(em);
    }

    public void create() {
        Client client = createClient();
        this.clientService.create(client);
    }

    public void delete() {
        long id = validateNumber("Insira o id do cliente a ser deletado\n--> ");
        this.clientService.delete(id);
    }

    public void update() {
        Client client = createClient();
        long id = validateNumber("Insira o id do cliente a ser atualizado\n--> ");

        this.clientService.update(client, id);
    }

    public void listAll() {
        List<Client> clients = this.clientService.listAll();
        clients.forEach(System.out::println);
    }

    public void listByName() {
        String name = validateString("Insira o nome do(s) cliente(s) a ser(em) listado(s)\n--> ");
        List<Client> clients = this.clientService.listByName(name);
        clients.forEach(System.out::println);
    }

    public void findByCpf() {
        String cpf = validateString("Insira o cpf do cliente a ser procurado\n--> ");
        Client client = this.clientService.findByCpf(cpf);
        System.out.println(client);
    }

    public void getById() {
        long id = validateNumber("Insira o id do cliente a ser procurado\n--> ");
        Client client = this.clientService.getById(id);
        System.out.println(client);
    }

    public void operations(int chosenOperation) {

        switch (chosenOperation) {
            case 0 -> create();
            case 1 -> delete();
            case 2 -> update();
            case 3 -> listAll();
            case 4 -> listByName();
            case 5 -> findByCpf();
            case 6 -> getById();
            case 7 -> {
                System.out.println("Volte sempre!:)");
                System.exit(0);
            }
        }
    }

    public void chooseOperation() {
        String[] operations = {
                "Criar um novo cliente","Deletar um cliente",
                "Atualizar um cliente","Listar todos os clientes",
                "Listar clientes pelo nome",
                "Encontrar um cliente pelo CPF",
                "Encontrar um cliente pelo Id",
                "Sair"
        };
        int chosenOperation =  chooseOption("O que deseja fazer?",operations);
        operations(chosenOperation);
    }

    public Client createClient() {
        String name = validateString("Insira o nome do cliente\n--> ");
        String cpf = validateString("Insira o cpf do cliente (somente numeros)\n--> ");
        String birthDate = validateString("Insira a data de nascimento do cliente (dd/mm/aaaa)\n--> ");
       return new Client(name,cpf,birthDate);
    }
}
