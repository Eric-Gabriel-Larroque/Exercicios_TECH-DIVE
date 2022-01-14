package personagens;

import modelos.Jogador;

public class Mago extends Jogador {
    public Mago() {
        super.setNome();
        super.setSexo();
        super.setPontosDeAtaque(19);
        super.setPontosDeDefesa(11);
        super.setArma(this);
    }
}
