package personagens;

import modelos.Jogador;

public class Arqueiro extends Jogador {

    public Arqueiro() {
        super.setNome();
        super.setSexo();
        super.setPontosDeAtaque(18);
        super.setPontosDeDefesa(13);
        super.setArma(this);
    }
}
