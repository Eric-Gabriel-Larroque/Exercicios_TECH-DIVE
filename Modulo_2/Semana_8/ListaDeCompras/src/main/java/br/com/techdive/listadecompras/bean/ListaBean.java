package br.com.techdive.listadecompras.bean;

import br.com.techdive.listadecompras.model.Item;
import br.com.techdive.listadecompras.repository.ItemRepository;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.ViewMap;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SessionScoped
@Named
public class ListaBean implements Serializable {

    @Inject
    private ItemRepository itemRepository;

    private boolean desabilitar = true;

    private Item item = new Item();

    private List<Item> listaItens = new ArrayList<>();

    private List<Item> itensSelecionados = new ArrayList<>();

    public void init() {
        List<Item> itensBD = itemRepository.obterItens();
        listaItens = itensBD;
    }


    public String adicionar(Item itemNovo) {
        System.out.println("Valor do item: "+itemNovo+"\nValor do nome: "+itemNovo.getNome());

        boolean isItemNovo = listaItens.stream().noneMatch(i->i.getNome().equalsIgnoreCase(itemNovo.getNome()));
        System.out.println("Nome do item: "+itemNovo.getNome());
        System.out.println("Ele é novo: "+isItemNovo);

        if(isItemNovo) {
            boolean jaCadastrado = itemRepository.existe(item.getNome());
            if(jaCadastrado) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro de validação", "Item já cadastrado."));
                return null;
            }
            itemRepository.adicionar(item);
            listaItens = itemRepository.obterItens();
        }
        item = new Item();
        return "lista?faces-redirect=true";
    }

    public void removerItens() {
        itemRepository.removerItens(itensSelecionados);
        listaItens = itemRepository.obterItens();
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<Item> getItensSelecionados() {
        return itensSelecionados;
    }

    public void setItensSelecionados(List<Item> itensSelecionados) {
        this.itensSelecionados = itensSelecionados;
    }

    public List<Item> getListaItens() {
        return listaItens;
    }

    public void setListaItens(List<Item> listaItens) {
        this.listaItens = listaItens;
    }

    public boolean isHabilitar() {
        return desabilitar;
    }

    public List<String> getMedidas() {
        return Arrays.asList("Quilo","Pacote","Saco","Garrafa","Lata","Litros","Unidades");
    }

}

