package modelos;

public abstract class Personagem {

    private int saude;
    private int pontosDeAtaque;
    private int pontosDeDefesa;


    public Personagem(int saude,int pontosDeAtaque, int pontosDeDefesa) {
        this.saude = saude;
        this.pontosDeAtaque = setPontosDeAtaque(pontosDeAtaque);
        this.pontosDeDefesa = setPontosDeDefesa(pontosDeDefesa);
    }

    public Personagem() {

    }

    public int setPontosDeAtaque(int pontosDeAtaque){
        this.pontosDeAtaque = pontosDeAtaque;
        return this.pontosDeAtaque;
    }

    public int getPontosDeAtaque() {
        return this.pontosDeAtaque;
    }

    public int setPontosDeDefesa(int pontosDeDefesa) {
        this.pontosDeDefesa = pontosDeDefesa;
        return this.pontosDeDefesa;
    }

    public int getPontosDeDefesa() {
        return this.pontosDeDefesa;
    }

    public int setSaude(int saude) {
        this.saude = saude;
        return this.saude;
    }
}
