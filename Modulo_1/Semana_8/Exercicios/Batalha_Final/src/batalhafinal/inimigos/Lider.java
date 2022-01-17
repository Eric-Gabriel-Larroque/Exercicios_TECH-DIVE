package batalhafinal.inimigos;

import batalhafinal.atributos.Arma;
import batalhafinal.modelos.Inimigo;

public class Lider extends Inimigo {

    private static final int SAUDE_MAXIMA  = 200;

    public Lider() {
        super(Arma.MACHADODUPLO);
        super.setSaude(SAUDE_MAXIMA);
        super.setPontosDeAtaque(15);
        super.setPontosDeDefesa(15);
    }
}
