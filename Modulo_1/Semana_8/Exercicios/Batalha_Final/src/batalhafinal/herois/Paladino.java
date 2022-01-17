package batalhafinal.herois;

import batalhafinal.modelos.Jogador;

public class Paladino extends Jogador {

    public Paladino(String nome, String sexo) {
        super(nome,sexo);
        super.setPontosDeAtaque(13);
        super.setPontosDeDefesa(18);
        super.setArma(this);
    }

}
