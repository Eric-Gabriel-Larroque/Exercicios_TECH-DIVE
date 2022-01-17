package batalhafinal.modelos;

public abstract class Personagem {

    private int saude;
    private int pontosDeAtaque;
    private int pontosDeDefesa;


    public Personagem() {
        this.saude = setSaude(saude);
        this.pontosDeAtaque = setPontosDeAtaque(pontosDeAtaque);
        this.pontosDeDefesa = setPontosDeDefesa(pontosDeDefesa);
    }

    public int getPontosDeAtaque() {
        return pontosDeAtaque;
    }

    public int setPontosDeAtaque(int pontosDeAtaque) {
        this.pontosDeAtaque = pontosDeAtaque;
        return  this.pontosDeAtaque;
    }

    public int getPontosDeDefesa() {
        return pontosDeDefesa;
    }

    public int setPontosDeDefesa(int pontosDeDefesa) {
        this.pontosDeDefesa = pontosDeDefesa;
        return this.pontosDeDefesa;
    }

    public int getSaude() {
        return saude;
    }

    public int setSaude(int saude) {
        this.saude = saude;
        return this.saude;
    }
}
