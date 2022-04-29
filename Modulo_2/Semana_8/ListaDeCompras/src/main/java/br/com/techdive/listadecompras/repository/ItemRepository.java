package br.com.techdive.listadecompras.repository;

import br.com.techdive.listadecompras.model.Item;

import javax.enterprise.context.ApplicationScoped;
import java.util.*;

@ApplicationScoped
public class ItemRepository {

    private List<Item> itens = new ArrayList<>();

    public ItemRepository() {}

    public List<Item> obterItens() {
        return itens;
    }

    public void adicionar(Item itemNovo) {
        itens.add(itemNovo);
    }

    public void remover(String nomeDoItem) {
        itens.removeIf(i-> i.getNome().equalsIgnoreCase(nomeDoItem));
    }
    public void removerItens(List<Item> itensSelecionados) {
       itens.removeAll(itensSelecionados);
    }

    public boolean existe(String nomeDoItem) {
        return itens.stream().anyMatch(i->i.getNome().equalsIgnoreCase(nomeDoItem));
    }

}
