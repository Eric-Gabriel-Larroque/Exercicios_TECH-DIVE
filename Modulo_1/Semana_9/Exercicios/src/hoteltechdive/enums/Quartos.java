package hoteltechdive.enums;

public enum Quartos {

    SIMPLES("simples",150,90,1),
    LUXO("luxo",400,140,10),
    SUITESUPREMA("suite suprema",800,500,10);

    private String nomeDoQuarto;
    private int diariaNasAltas;
    private int  diariaNasBaixas;
    private int quartosDisponiveis;

    private Quartos(String nomeDoQuarto,int diariaNasAltas,int diariaNasBaixas, int quartosDisponiveis) {
        this.nomeDoQuarto = nomeDoQuarto;
        this.diariaNasAltas = diariaNasAltas;
        this.diariaNasBaixas = diariaNasBaixas;
        this.quartosDisponiveis = quartosDisponiveis;
    }
    public String getNomeDoQuarto() {
        return nomeDoQuarto;
    }

    public int getDiariaNasAltas() {return diariaNasAltas;}

    public int getDiariaNasBaixas() {
        return diariaNasBaixas;
    }

    public int getQuartosDisponiveis() {return quartosDisponiveis;}
}
