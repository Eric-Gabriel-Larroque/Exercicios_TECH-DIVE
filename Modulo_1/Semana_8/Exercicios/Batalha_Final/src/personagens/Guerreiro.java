package personagens;

import modelos.Jogador;

public class Guerreiro extends Jogador {
    public Guerreiro() {
        super.setNome();
        super.setSexo();
        super.setPontosDeAtaque(15);
        super.setPontosDeDefesa(15);
        super.setArma(this);
    }
}
