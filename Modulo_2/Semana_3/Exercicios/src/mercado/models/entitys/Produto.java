package mercado.models.entitys;

import java.util.LinkedHashSet;

public class Produto {

    private int id;
    private String nomeProduto;
    private double preco;
    private Classificacao classificacao;
    private Marca marca;
    private LinkedHashSet<Produto> listaProdutos = new LinkedHashSet<>();

    public Produto(String nomeProduto, double preco, int id){
        this.nomeProduto = nomeProduto;
        this.preco = preco;
        this.id = id;
        listaProdutos.add(this);
    }

    public Produto(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
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