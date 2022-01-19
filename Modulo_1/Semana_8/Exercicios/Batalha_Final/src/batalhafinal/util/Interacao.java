package batalhafinal.util;

import batalhafinal.modelos.Jogador;
import batalhafinal.inimigos.Armadilha;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static batalhafinal.util.MensagensHistoria.*;
import static batalhafinal.util.MensagensEscolhas.*;

public class Interacao {


    public static void iniciar() {
        dispararMensagens(mensagensInicio);
    }

    public static String escolherOpcao(String mensagem, String... opcoesPossiveis) {
        
        List<Integer> opcoes = new ArrayList<>();
        String listarOpcoes = "";
        int resposta = 0;
        
        for(int i = 0; i < opcoesPossiveis.length;i++) {
            opcoes.add(i+1);
            listarOpcoes+= "\n"+(i+1)+" - "+opcoesPossiveis[i];
        }
        
        do {
            try {
                resposta =
                        Integer.parseInt(JOptionPane.showInputDialog(null,
                                mensagem+listarOpcoes));
            }catch (NullPointerException nullPointerException) {
                JOptionPane.showMessageDialog(null,
                        "Entrada de dados vazia. Tente novamente.",
                        "Dados vazios",JOptionPane.ERROR_MESSAGE);
            }catch (IllegalArgumentException illegalArgumentException) {
                JOptionPane.showMessageDialog(null,
                        "Somente números são permitidos. Tente novamente.",
                        "Tipo inválido",JOptionPane.ERROR_MESSAGE);
            }

        }while(!opcoes.contains(resposta));

        --resposta;
        return opcoesPossiveis[resposta];
    }

    public static void mensagemMotivacao(Jogador jogador) {


        if(jogador.getMotivacao().getValue().equals("vingança")) {
            dispararMensagens(mensagensMotivacaoVinganca,jogador);
        } else{
            dispararMensagens(mensagensMotivacaoGloria,jogador);
        }
    }

    public static void continuarOuDesistir(Jogador jogador) {

        dispararMensagens(mensagensMotivacao,jogador);

        String continuarOuDesistir =
                escolherOpcao("Você quer ir em frente ou desistir?",
                        "Continuar","Desistir");

        if(continuarOuDesistir.equals("Desistir")) {
            dispararMensagens(mensagensDesistir);
            System.exit(0);
        }
    }

    public static void passarPelaPorta( Jogador jogador) {
        dispararMensagens(mensagensContinuar,jogador);

        String passarPelaPorta =
                escolherOpcao("Como você deseja passar pela porta?",
                        "Andando cuidadosamente","Correndo","Saltando");

        if(passarPelaPorta.equals("Saltando")) {
           dispararMensagens(mensagensPassarSaltando);
        }
        else if(passarPelaPorta.equals("Correndo")) {
            dispararMensagens(mensagensPassarCorrendo);
        }
        else {
            dispararMensagens(mensagensPassarDevagar);
            new Armadilha().atacar(jogador);
        }
    }


    public static void prologoArmeiro(Jogador jogador) {
        dispararMensagens(mensagensArmeiro,jogador);
    }

    public static void pegarNovaArmadura(Jogador jogador) {

        dispararMensagens(mensagensArmadura);
        String pegarArmadura = escolherOpcao("Você gostaria de pegar a armadura?","Pegar armadura","Deixar");

        if(pegarArmadura.equals("Pegar armadura")) {
            dispararMensagens(mensagensPegarArmadura,jogador);
            jogador.setPontosDeDefesa(jogador.getPontosDeDefesa()+5);
            Audio.tocarAudio("audio/02 - PauseMenu.wav",false);
            JOptionPane.showMessageDialog(null,"Pontos de defesa aumentados para "
                    +jogador.getPontosDeDefesa());
        }else {
            dispararMensagens(mensagensNaoPegarArmadura);
        }
    }

    public static void prologoAlquimista(Jogador jogador) {
        dispararMensagens(mensagensAlquimista,jogador);
    }

    public static void beberPocao(Jogador jogador) {

        dispararMensagens(mensagensPocao);

        String beberOuNao = escolherOpcao("Você gostaria de beber da poção do alquimista?",
                "Beber", "Não beber");

        if (beberOuNao.equals("Beber")) {
            dispararMensagens(mensagensBeberPocao, jogador);
            jogador.setSaude(jogador.getSAUDE_MAXIMA());
            Audio.tocarAudio("audio/24 - EnergyFill.wav",false);
            JOptionPane.showMessageDialog(null,
                    "Saúde restaurada para " + jogador.getSaude());
        } else {
            dispararMensagens(mensagensNaoBeber, jogador);
        }
    }

    public static void prologoLider(Jogador jogador) {

        dispararMensagens(mensagensLider);
    }
    public static boolean atacarOuEsperar() {
        String atacarOuEsperar = escolherOpcao("Gostaria de esperar o inimigo chegar mais perto?",
                "Aguardar","Atacar logo");
        if(atacarOuEsperar.equals("Atacar logo")) {
            return false;
        } else {
            return true;
        }
    }

    public static void conclusao(Jogador jogador) {

        if(jogador.getMotivacao().getValue().equals("vingança")) {
                dispararMensagens(mensagensVitoriaVinganca);
        } else {
                dispararMensagens(mensagensVitoriaGloria);
        }
      dispararMensagens(mensagensVitoria);
    }
}