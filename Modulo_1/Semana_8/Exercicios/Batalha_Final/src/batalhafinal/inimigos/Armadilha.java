package batalhafinal.inimigos;

import batalhafinal.enums.Arma;
import batalhafinal.modelos.Atacante;
import batalhafinal.modelos.Personagem;

import javax.swing.*;

public class Armadilha implements Atacante {

    private int pontosDeAtaque;
    private Arma arma;

    public Armadilha() {
        this.arma = Arma.ARMADILHA;
        this.pontosDeAtaque = 5;
    }

    @Override
    public void atacar(Personagem alvo) {

            int D10 = (int) Math.ceil(Math.random()*(10-1)+1);
            int danoDaArmadilha= alvo.getPontosDeDefesa()-(D10+pontosDeAtaque);

        if(D10==1) {
                JOptionPane.showMessageDialog(null,
                        "O ataque da armadilha pegou de raspão e você não sofreu dano.");
            } else {

                if (danoDaArmadilha >= 0) {
                    JOptionPane.showMessageDialog(null,
                            "Sua armadura projeteu você contra o dano da armadilha.");
                } else {
                    alvo.setSaude(alvo.getSaude()+(danoDaArmadilha));
                    JOptionPane.showMessageDialog(null,
                            "Você sofreu "+Math.abs(danoDaArmadilha)+" de dano e agora possui "+
                                    alvo.getSaude()+" pontos de vida.");
                }
            }
    }
}
