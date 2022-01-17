package batalhafinal.herois;

import batalhafinal.modelos.Jogador;

public class Guerreiro extends Jogador {


    public Guerreiro(String nome, String sexo) {
        super(nome,sexo);
        super.setPontosDeAtaque(15);
        super.setPontosDeDefesa(15);
        super.setArma(this);
    }
}
