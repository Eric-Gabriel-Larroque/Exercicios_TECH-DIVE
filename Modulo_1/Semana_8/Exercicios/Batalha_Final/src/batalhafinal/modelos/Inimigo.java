package batalhafinal.modelos;

import batalhafinal.atributos.Arma;

import javax.swing.*;

public class Inimigo extends Personagem implements Atacante {

    private Arma arma;
    public Inimigo(Arma arma) {
        this.arma = arma;
    }


    @Override
    public void atacar(Personagem alvo) {
        int novaSaude = 0;
        int D20 = (int) Math.ceil(Math.random()*(20-1)+1);
        int danoDoAtaque = alvo.getPontosDeDefesa()-(D20+arma.getPontosDeAtaque()+this.getPontosDeAtaque());
        String mensagemPadrao = "";

        JOptionPane.showMessageDialog(null,
                "Turno do inimigo");

        if(D20==1) {
            JOptionPane.showMessageDialog(null,
                    "   O inimigo errou o ataque! Você não sofreu dano.");
        } else if (D20 == 20) {
            danoDoAtaque = D20+arma.getPontosDeAtaque()+this.getPontosDeAtaque();
            novaSaude = alvo.getSaude()-danoDoAtaque;
            alvo.setSaude(novaSaude);
            JOptionPane.showMessageDialog(null,
                    "O inimigo acertou um ataque crítico!\n"+
                            "Você sofreu "+Math.abs(danoDoAtaque)+" de dano e agora possui\n"+
                            alvo.getSaude()+" pontos de vida.");
        } else {
            novaSaude = alvo.getSaude()-Math.abs(danoDoAtaque);
            alvo.setSaude(novaSaude);
            JOptionPane.showMessageDialog(null,
                    "O inimigo atacou!"+

                            "Você sofreu "+Math.abs(danoDoAtaque)+" de dano e agora possui\n"+
                            alvo.getSaude()+" pontos de vida.");
        }
    }

    public Arma getArma() {
        return this.arma;
    }
}
