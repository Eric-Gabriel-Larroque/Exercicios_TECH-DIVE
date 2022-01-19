package batalhafinal.enums;

public enum Arma {

    ESPADA("espada",5),
    MACHADO("machado",5),
    MARTELO("martelo",5),
    CLAVA("clava",5),
    ARCO("arco",5),
    BESTA("besta",5),
    CAJADO("cajado",5),
    LIVRODEFEITICOS("livro de feitiços",5),
    ARMADILHA("Armadilha",2),
    MACHADODUPLO("machado duplo",7);


    private String arma;
    private int pontosDeAtaque;

    Arma(String arma,int pontosDeAtaque) {

        this.arma = arma;
        this.pontosDeAtaque = pontosDeAtaque;
    }

    public String getArma() {
        return arma;
    }

    public int getPontosDeAtaque(){
        return pontosDeAtaque;
    }
}
