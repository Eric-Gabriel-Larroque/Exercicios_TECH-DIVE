package mercado.models.entitys;

import java.util.LinkedHashSet;

import static mercado.util.Validacao.*;

public class Produto {

    private int id;
    private String nomeProduto;
    private double preco;
    private Classificacao classificacao;
    private Marca marca;
    private LinkedHashSet<Produto> listaProdutos = new LinkedHashSet<>();

    public Produto(String nomeProduto, double preco, int id, Classificacao classificacao, Marca marca){
        this.nomeProduto = nomeProduto;
        this.preco = preco;
        this.id = id;
        this.classificacao = classificacao;
        this.marca = marca;
        listaProdutos.add(this);
    }

    public Produto(){
        setNomeProduto();
        setPreco();
        listaProdutos.add(this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto() {
        this.nomeProduto = validaString("Insira um nome\n--> ");
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco() {
        this.preco = validaDouble("Insira um preço\n--> ");
    }

    public Classificacao getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(Classificacao classificacao) {
        this.classificacao = classificacao;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
}