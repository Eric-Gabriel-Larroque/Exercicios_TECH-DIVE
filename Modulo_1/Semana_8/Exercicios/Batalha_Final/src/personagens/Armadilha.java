package personagens;

import atributos.Arma;
import modelos.Atacante;

public class Armadilha implements Atacante {

    private int pontosDeAtaque = 5;
    private String arma = Arma.ARMADILHA.getArma();

    public Armadilha() {

    }
}
