package batalhafinal.inimigos;

import batalhafinal.atributos.Arma;
import batalhafinal.modelos.Inimigo;

public class Alquimista extends Inimigo {

    private static final int SAUDE_MAXIMA  = 100;

    public Alquimista() {
        super(Arma.CAJADO);
        super.setSaude(SAUDE_MAXIMA);
        super.setPontosDeAtaque(15);
        super.setPontosDeDefesa(10);
    }
}
