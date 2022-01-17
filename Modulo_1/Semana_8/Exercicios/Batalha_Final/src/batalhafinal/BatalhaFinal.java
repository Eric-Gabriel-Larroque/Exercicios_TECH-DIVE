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
        continuarOuDesistir();
        passarPelaPorta(jogador);
        prologoArmeiro();
        // TODO: instanciar objeto Armeiro
        Armeiro armeiro = new Armeiro();
        // TODO: iniciar loop de combate, inimigo ataca primeiro
        batalhaFinal.combate(jogador,armeiro,true);
        pegarNovaArmadura(jogador);
        prologoAlquimista();
        // TODO: instanciar objeto Alquimista
        Alquimista alquimista = new Alquimista();
        // TODO: iniciar loop de combate, inimigo ataca primeiro
        batalhaFinal.combate(jogador,alquimista,true);
        beberPocao(jogador);
        prologoLider();
        // TODO: jogador deve decidir se ataca ou espera
        boolean atacarOuEsperar = atacarOuEsperar();
        // TODO: iniciar loop de combate, dependendo da resposta do jogador, ele ataca primeiro
        Lider liderMaligno = new Lider();
        batalhaFinal.combate(jogador,liderMaligno,atacarOuEsperar);
        JOptionPane.showMessageDialog(null,"Você conseguiu!");
        // TODO: exibir mensagem de vitória de acordo com a motivação do jogador:
        conclusao(jogador);
    }

    private void combate(Jogador jogador, Inimigo inimigo, boolean inimigoComeca) {
        if(inimigoComeca) {
            inimigo.atacar(jogador);
        }
        while(jogador.getSaude()>0&&inimigo.getSaude()>0) {
            jogador.atacar(inimigo);
            inimigo.atacar(jogador);
        }
        if(jogador.getSaude()<=0) {
            String mensagemDeMorte =
                    jogador.getMotivacao().getValue().equals("vingança") ?
                            "Você não estava preparado para a força do inimigo.\n"+
                            "Não foi possível concluir sua vingança, e agora resta\n"+
                            "saber se alguém se vingará por você." :

                            "Você não estava preparado para a força do inimigo.\n"+
                            "A glória que buscavas não será sua, e a cidade aguarda\n"+
                            "por seu(sua) próximo(a) herói(na)";
            JOptionPane.showMessageDialog(null,
                    mensagemDeMorte);
            System.exit(0);
        }else if (inimigo.getSaude()<=0) {
            JOptionPane.showMessageDialog(null,
                    "O inimigo não é páreo para o seu heroísmo, e jaz imóvel aos seus pés.");
        }

    }

}