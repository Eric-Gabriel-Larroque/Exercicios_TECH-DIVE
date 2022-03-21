package market2.service;

import market2.model.dao.ClientDAO;
import market2.model.persistence.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class ClientService {

    private EntityManager em;

    private ClientDAO clientDAO;

    private final Logger LOG = LogManager.getLogger(ClientService.class);

    public ClientService(EntityManager em) {
        this.em = em;
        this.clientDAO = new ClientDAO(em);
    }

    public void create(Client client) {
        this.LOG.info("Preparando a criação de cliente...");

        this.LOG.info("Verificando se o cliente informado é nulo...");
        validateNullClient(client);

        this.LOG.info("Buscando se existe cliente com o cpf informado...");

        Client client1 = this.findByCpf(client.getCPF());

        if(client1 != null) {
            this.LOG.info("Esse cliente já se encontra em nossa base de dados.");
            throw new EntityExistsException("Client already exists");
        }

        try {
            beginTransaction();
            this.clientDAO.create(client);
            commitAndClose();
        }catch (Exception e) {
            this.LOG.error("Erro ao criar cliente causado por: "+e.getMessage());
        }
        this.LOG.info("Criação realizada com sucesso!");
    }

    public void delete(Long id) {
        this.LOG.info("Preparando para encontrar o cliente...");

        this.LOG.info("Validando se o id informado é nulo...");
        validateNullId(id);

        this.LOG.info("Verificando se existe cliente com o id informado...");

        Client client = this.clientDAO.getById(id);
        validateNullClient(client);

        this.LOG.info("Cliente encontrado! Iniciando deleção...");

        try {
            beginTransaction();
            this.em.remove(client);
            commitAndClose();
        }catch (Exception e) {
            this.LOG.error("Erro ao deletar cliente causado por: "+e.getMessage());
        }
        this.LOG.info("Deleção realizada com sucesso!");
    }

    public void update(Client newClient, Long clientId) {
        this.LOG.info("Preparando para atualizar cliente...");

        this.LOG.info("Verificando se o cliente informado é nulo...");
        validateNullClient(newClient);

        this.LOG.info("Validando se o Id informado é nulo...");
        validateNullId(clientId);

        this.LOG.info("Validando existência do cliente com o id informado");
        Client client = this.clientDAO.getById(clientId);
        validateNullClient(client);
        this.LOG.info("Cliente encontrado. Iniciando atualizacao...");

        try {
            beginTransaction();
            this.clientDAO.update(client);
            client.setName(newClient.getName());
            client.setCPF(newClient.getCPF());
            client.setBirthDate(newClient.getBirthDate());
            commitAndClose();
        }catch (Exception e) {
            this.LOG.error("Erro ao tentar atualizar o cliente causado por: "+e.getMessage());
        }
        this.LOG.info("Atualização realizada com sucesso!");
    }

    public List<Client> listAll() {
        this.LOG.info("Preparando listagem de cliente(s)...");
        List<Client> clients = this.clientDAO.listAll();

        this.LOG.info("Verificando se a lista de clientes é nula...");
        validateNullList(clients);

        if(clients != null) {
            this.LOG.info(clients.size()+" cliente(s) encontrado(s)");
        }
        return clients;
    }

    public List<Client> listByName(String name) {

        this.LOG.info("Verificando se o nome informado é nulo ou  vazio...");
        validateNullName(name);

        this.LOG.info("Preparando listagem do(s) cliente(s) com nome "+name+"...");
        List<Client> clients = this.clientDAO.listByName(name);

        this.LOG.info("Validando se a lista está vazia...");
        validateNullList(clients);

        if(clients != null) {
            this.LOG.info(clients.size()+" cliente(s) encontrado(s)");
        }
        return clients;
    }

    public Client findByCpf(String cpf) {
        this.LOG.info("Verificando se o cpf informado é nulo...");
        validateNullCpf(cpf);
        try {
            this.LOG.info("Verificando se existe cliente com o cpf informado...");
            Client client = this.clientDAO.findByCPF(cpf);
            this.LOG.info("Cliente com o CPF informado foi encontrado.");
            return client;
        }catch (NoResultException e) {
            this.LOG.info("Não foi possível enconrtar o cliente com o cpf informado.");
            return null;
        }
    }

    public Client getById(Long id) {
        this.LOG.info("Preparando para encontar cliente...");

        this.LOG.info("Verificando se o id informado é nulo...");
        validateNullId(id);

        this.LOG.info("Verificando se existe cliente com o id informado...");
        Client client = this.clientDAO.getById(id);
        validateNullClient(client);

        if(client != null) {
            this.LOG.info("Cliente encontrado!");
        }
        return client;
    }

    private void validateNullName(String name) {
        if(name == null || name.isEmpty() || name.isBlank()) {
            this.LOG.error("Nome não pode ser vazio ou nulo");
            throw new RuntimeException("Name cannot be empty or null");
        }
    }

    private List<Client> validateNullList(List<Client> clients) {
        if(clients == null) {
            this.LOG.info("Não foram encontrados clientes");
            return new ArrayList<>();
        }
        return clients;
    }

    private void validateNullId(Long id) {
        if(id == null) {
            this.LOG.error("O id informado é nulo");
            throw new RuntimeException("Id is null");
        }
    }

    private void validateNullCpf(String cpf) {
        if(cpf == null || cpf.isEmpty() || cpf.isBlank()) {
            this.LOG.error("O cpf não pode ser vazio ou nulo");
            throw new RuntimeException("Name cannot be empty or null");
        }
    }

    private void validateNullClient(Client client) {
        if(client == null) {
            this.LOG.error("Cliente não foi encontrado");
        throw new EntityNotFoundException("Client not found");
        }
    }

    private void beginTransaction() {
        this.LOG.info("Abrindo transação...");
        this.em.getTransaction().begin();
    }

    private void commitAndClose() {
        this.LOG.info("Commitando e fechando transação...");
        this.em.getTransaction().commit();
        this.em.close();
    }
}
