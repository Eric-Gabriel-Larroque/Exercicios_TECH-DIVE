package batalhafinal;

import batalhafinal.inimigos.Alquimista;
import batalhafinal.inimigos.Armeiro;
import batalhafinal.modelos.Inimigo;
import batalhafinal.modelos.Jogador;
import batalhafinal.inimigos.Lider;

import javax.swing.*;

import static batalhafinal.util.Audio.tocarAudio;
import static batalhafinal.util.Interacao.*;



public class BatalhaFinal {

    public static void main(String[] args) {
        String nome = "";
        String sexo = "";
        String motivacao = "";
        Jogador jogador = null;
        BatalhaFinal batalhaFinal = new BatalhaFinal();

        tocarAudio("audio/conan_soundtrack.wav");
        JOptionPane.showMessageDialog(null,"Seja bem vindo(a) à BATALHA FINAL!");
         nome = Jogador.setNome();
         sexo = Jogador.setSexo();
         jogador = Jogador.setClasse(nome,sexo);
        tocarAudio("audio/suspense.wav");
        iniciar();
        Jogador.setMotivacao(jogador);
        mensagemMotivacao(jogador);
        continuarOuDesistir(jogador);
        passarPelaPorta(jogador);
        prologoArmeiro(jogador);
        Armeiro armeiro = new Armeiro();
        tocarAudio("audio/combat.wav");
        batalhaFinal.combate(jogador,armeiro,true);
        tocarAudio("audio/suspense.wav");
        pegarNovaArmadura(jogador);
        prologoAlquimista(jogador);
        Alquimista alquimista = new Alquimista();
        tocarAudio("audio/combat.wav");
        batalhaFinal.combate(jogador,alquimista,true);
        tocarAudio("audio/suspense.wav");
        beberPocao(jogador);
        prologoLider(jogador);
        boolean atacarOuEsperar = atacarOuEsperar();
        Lider liderMaligno = new Lider();
        tocarAudio("audio/combat.wav");
        batalhaFinal.combate(jogador,liderMaligno,atacarOuEsperar);
        tocarAudio("audio/conan_soundtrack.wav");
        JOptionPane.showMessageDialog(null,"Você conseguiu!");
        conclusao(jogador);
    }

    private void combate(Jogador jogador, Inimigo inimigo, boolean inimigoComeca) {
        if(inimigoComeca) {
            inimigo.atacar(jogador);
        }
        while(jogador.getSaude()>0&&inimigo.getSaude()>0) {
            jogador.atacar(inimigo);
            if (inimigo.getSaude()<=0) {
                JOptionPane.showMessageDialog(null,
                        "O inimigo não é páreo para o seu heroísmo, e jaz imóvel aos seus pés.");
                break;
            }
            inimigo.atacar(jogador);
            if(jogador.getSaude()<=0) {
                String mensagemDeMorte =
                        jogador.getMotivacao().getValue().equals("vingança") ?
                                "Você não estava preparado para a força do inimigo.\n" +
                                        "Não foi possível concluir sua vingança, e agora resta\n" +
                                        "saber se alguém se vingará por você." :

                                "Você não estava preparado para a força do inimigo.\n" +
                                        "A glória que buscavas não será sua, e a cidade aguarda\n" +
                                        "por seu(sua) próximo(a) herói(na)";
                JOptionPane.showMessageDialog(null,
                        mensagemDeMorte);
                System.exit(0);
            }
        }

    }

}