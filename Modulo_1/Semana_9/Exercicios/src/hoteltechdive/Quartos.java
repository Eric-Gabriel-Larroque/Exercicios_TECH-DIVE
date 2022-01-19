package hoteltechdive;

public enum Quartos {

    SIMPLES("simples",150,90),
    LUXO("luxo",400,140),
    SUITESUPREMA("suite suprema",500,800);

    private String nomeDoQuarto;
    private int diariaNasAltas;
    private int  diariaNasBaixas;

    private Quartos(String nomeDoQuarto,int diariaNasAltas,int diariaNasBaixas) {
        this.nomeDoQuarto = nomeDoQuarto;
        this.diariaNasAltas = diariaNasAltas;
        this.diariaNasBaixas = diariaNasBaixas;
    }

    public String getNomeDoQuarto() {
        return nomeDoQuarto;
    }

    public int getDiariaNasAltas() {
        return diariaNasAltas;
    }

    public int getDiariaNasBaixas() {
        return diariaNasBaixas;
    }
}
