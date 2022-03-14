package mercado.models.entitys;

import java.util.LinkedHashSet;

public class Classificacao {

    private int id;
    private String nomeClassificacao;
    private static LinkedHashSet<Classificacao> listaClassificacoes = new LinkedHashSet<>();
    public  static LinkedHashSet<String> listaNomes = new LinkedHashSet<>();

    public Classificacao(String nomeClassificacao,int id) {
        this.nomeClassificacao = nomeClassificacao;
        this.id = id;
        listaNomes.add(nomeClassificacao);
        listaClassificacoes.add(this);
    }

    public Classificacao() {}

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNomeClassificacao() {
        return nomeClassificacao;
    }

    public void setNomeClassificacao(String nomeClassificacao) {

        this.nomeClassificacao = nomeClassificacao;
    }

    public static LinkedHashSet<Classificacao> getListaClassificacoes() {
        return listaClassificacoes;
    }
}
