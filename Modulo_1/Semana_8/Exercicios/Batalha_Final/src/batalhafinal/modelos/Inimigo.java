package batalhafinal.modelos;

import batalhafinal.enums.Arma;

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

        if(D20==1||danoDoAtaque>=0) {
            JOptionPane.showMessageDialog(null,
                    "   O inimigo errou o ataque! Você não sofreu dano.");
        } else if (D20 == 20) {
            danoDoAtaque = D20+arma.getPontosDeAtaque()+this.getPontosDeAtaque();
            novaSaude = alvo.getSaude()-danoDoAtaque;
            String mensagemCritico = arma.getArma().matches("espada") ?
                    "O inimigo acertou um ataque crítico com a sua "+arma.getArma()+"!\n":
                    "O inimigo acertou um ataque critico com o seu "+arma.getArma()+"!\n";
                    alvo.setSaude(novaSaude);
            JOptionPane.showMessageDialog(null,
                    mensagemCritico+
                            "Você sofreu "+Math.abs(danoDoAtaque)+" de dano e agora possui\n"+
                            alvo.getSaude()+" pontos de vida.");
        } else {
            novaSaude = alvo.getSaude()-Math.abs(danoDoAtaque);
            alvo.setSaude(novaSaude);
            String mensasgemAtaque = arma.getArma().matches("espada") ?
                    "O inimigo atacou com a sua "+arma.getArma()+"!\n":
                    "O inimigo atacou com o seu "+arma.getArma()+"!\n";
            JOptionPane.showMessageDialog(null,
                    mensasgemAtaque+"Você sofreu "
                            +Math.abs(danoDoAtaque)+" de dano e agora possui\n"+
                            alvo.getSaude()+" pontos de vida.");
        }
    }

    public Arma getArma() {
        return this.arma;
    }
}
