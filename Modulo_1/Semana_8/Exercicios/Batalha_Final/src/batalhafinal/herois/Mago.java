package batalhafinal.herois;

import batalhafinal.modelos.Jogador;

public class Mago extends Jogador {

    public Mago(String nome, String sexo) {
        super(nome,sexo);
        super.setPontosDeAtaque(19);
        super.setPontosDeDefesa(11);
        super.setArma(this);
    }

}
