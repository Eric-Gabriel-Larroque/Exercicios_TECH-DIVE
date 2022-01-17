package batalhafinal.herois;

import batalhafinal.modelos.Jogador;

public class Arqueiro extends Jogador {

    public Arqueiro(String nome, String sexo) {
        super(nome,sexo);
        super.setPontosDeAtaque(18);
        super.setPontosDeDefesa(13);
        super.setArma(this);
    }

}
