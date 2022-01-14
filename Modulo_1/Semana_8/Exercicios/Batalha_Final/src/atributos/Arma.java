package atributos;

public enum Arma {

    ESPADA("espada"),
    MACHADO("machado"),
    MARTELO("martelo"),
    CLAVA("clava"),
    ARCO("arco"),
    BESTA("besta"),
    CAJADO("cajado"),
    LIVRODEFEITICOS("livro de feitiços"),
    ARMADILHA("Armadilha"),
    MACHADODUPLO("machado duplo");


    private String arma;

    Arma(String arma) {
        this.arma = arma;
    }

    public String getArma() {
        return arma;
    }
}
