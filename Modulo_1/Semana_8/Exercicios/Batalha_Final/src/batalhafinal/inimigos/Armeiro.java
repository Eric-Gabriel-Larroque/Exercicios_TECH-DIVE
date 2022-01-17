package batalhafinal.inimigos;

import batalhafinal.atributos.Arma;
import batalhafinal.modelos.Inimigo;

public class Armeiro extends Inimigo {

    private static final int SAUDE_MAXIMA  = 100;

    public Armeiro() {
        super(Arma.ESPADA);
        super.setSaude(SAUDE_MAXIMA);
        super.setPontosDeAtaque(10);
        super.setPontosDeDefesa(15);
    }
}
