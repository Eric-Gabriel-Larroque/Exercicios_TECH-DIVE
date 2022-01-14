package personagens;

import modelos.Jogador;

public class Paladino extends Jogador {
    public Paladino() {
        super.setNome();
        super.setSexo();
        super.setPontosDeAtaque(13);
        super.setPontosDeDefesa(18);
        super.setArma(this);
    }
}
