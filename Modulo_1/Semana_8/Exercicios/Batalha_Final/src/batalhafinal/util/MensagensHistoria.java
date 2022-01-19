package batalhafinal.util;

import batalhafinal.modelos.Jogador;

import javax.swing.*;
import java.util.Arrays;

import static batalhafinal.util.MensagensEscolhas.*;

public class MensagensHistoria {

    public static String[] mensagensInicio = {
            "A noite se aproxima, a lua já surge no céu, estrelas vão se acendendo,\n"
            +"e sob a luz do crepúsculo você está prestes a entrar na fase final da sua missão.",

            "Você olha para o portal à sua frente, e sabe que a partir desse ponto, sua vida"+
            " mudará para sempre.",

            "Memórias do caminho percorrido para chegar até aqui invadem sua mente.\n"+
            "Você se lembra de todos os inimigos já derrotados para alcançar o covil do líder maligno.",

            "Olha para seu equipamento de combate, já danificado e desgastado depois de tantas lutas.\n"+
            "Você está a um passo de encerrar para sempre esse mal.",

            "Buscando uma injeção de ânimo, você se força a lembrar o que te trouxe até aqui."
    };

    public static String[] mensagensMotivacao = {
            "Inspirado pelo motivo que te trouxe até aqui, você sente seu coração ardendo em chamas,\n"+
            "suas mãos formigarem em volta da sua arma. Você a segura com firmeza.\n"+
            "Seu foco está renovado.Você avança pelo portal.",

            "A escuridão te envolve. Uma iluminação muito fraca entra pelo portal às suas costas.\n"+
            "À sua frente, só é possível perceber que você se encontra em um corredor extenso.",

            "Você só pode ir à frente, ou desistir."
    };

    public static String[] mensagensArmeiro = {
            "Você se encontra sozinho em uma sala quadrada,\n"+
            "contendo uma porta em cada parede. Uma delas foi\n"+
            "aquela pela qual você entrou, que estava aberta,"+
            "e as outras três estão fechadas.\n",

            "A porta à sua frente é a maior das quatro,\n"+
            "com inscrições em seu entorno em uma língua que você não sabe ler,\n"+
            "mas reconhece como sendo a língua antiga utilizada pelo inimigo.",

            "Você se aproxima da porta e percebe que ela está trancada por\n"+
            "duas fechaduras douradas,e você entende que precisará primeiro\n"+
            "derrotar o que estiver nas outras duas portas laterais,\n"+
            "antes de conseguir enfrentar o líder.",

            "Você se dirige para a porta à direita.",

            "Você se aproxima, tentando ouvir o que acontece porta adentro,\n"+
            "mas não escuta nada. Segura com mais força sua arma com uma mão,\n"+
            "enquanto empurra a porta com a outra.",

            "Ao entrar, você se depara com uma sala espaçosa,\n"+
            "com vários equipamentos de batalha pendurados nas\n"+
            "paredes e dispostos em armários e mesas.\n",

            "Você imagina que este seja o arsenal do inimigo, onde\n"+
            "estão guardados os equipamentos que seus soldados utilizam\n"+
            "quando saem para espalhar o terror nas cidades e vilas da região.\n",

            "Enquanto seu olhar percorre a sala, você ouve a porta se fechando\n"+
            "e gira rapidamente para olhar para trás. Ali, de pé entre você e a porta\n"+
            "fechada, bloqueando o caminho do seu destino, está um dos capitães do inimigo.",

            "Um orque horrendo, de armadura, capacete e espada\n"+
            "em punho, em posição de combate. Ele avança em sua direção."
    };

    public static String[] mensagensArmadura = {
            "Após derrotar o Armeiro, você percebe que seus equipamentos estão muito danificados.\n"+
            "Olha em volta, encarando todas aquelas peças de armaduras resistentes e em ótimo estado."
    };

    public static String[] mensagensAlquimista = {
            "Em uma mesa, você encontra uma chave dourada,\n"+
            "e sabe que aquela chave abre uma das fechaduras da\n"+
            "porta do líder inimigo. Você pega a chave e guarda\n"+
            "numa pequena bolsa que leva presa ao cinto.",

            "Você retorna à sala anterior e se dirige à porta da esquerda.\n"+
            "Você se aproxima, tentando ouvir o que acontece porta adentro,\n"+
            "mas não escuta nada. Segura com mais força sua arma com uma mão,\n"+
            "enquanto empurra a porta com a outra.",

            "Ao entrar, você se depara com uma sala parecida com a do arsenal,\n"+
            "mas em vez de armaduras, existem vários potes e garrafas de vidro\n"+
            "com conteúdos misteriosos e de cores diversas, e você entende que\n"+
            "o capitão que vive ali, realiza experimentos com diversos ingredientes,\n"+
            "criando poções utilizadas pelos soldados para aterrorizar a região.",

            "No fundo da sala, olhando em sua direção, está outro dos capitães do inimigo.\n"+
            "Um orque horrendo, de armadura, cajado em punho, em posição de combate.",

            "Ele avança em sua direção.",
    };

    public static String[] mensagensPocao = {
            "Após derrotar o Alquimista, você olha em volta,\n"+
            "tentando reconhecer alguma poção do estoque do inimigo.",

            "Em uma mesa, você reconhece uma pequena garrafa de vidro contendo um\n"+
            "líquido levemente rosado, pega a garrafa e pondera se deve beber um gole.",
    };

    public static String[] mensagensLider = {
            "Ao lado da porta, você vê uma chave dourada em cima de uma mesa,\n"+
            "e sabe que aquela chave abre a outra fechadura da porta do líder inimigo.\n"+
            "Você pega a chave e guarda na pequena bolsa que leva presa ao cinto.",

            "De volta à sala das portas, você se dirige à porta final.\n"+
            "Coloca as chaves nas fechaduras, e a porta se abre.\n"+
            "Seu coração acelera, memórias de toda a sua vida passam pela sua mente,\n"+
            "e você percebe que está muito próximo do seu objetivo final.\n",

            "Segura sua arma com mais firmeza, foca no combate\n"+
            "que você sabe que irá se seguir, e adentra a porta.",

            "Lá dentro, você vê o líder sentado em uma poltrona dourada,\n",
            "com duas fogueiras de cada lado, e prisioneiros acorrentados às paredes.",

            "Ele percebe sua chegada e se levanta com um salto,\n"+
            "apanhando seu machado de guerra de lâmina dupla."
    };

    public static String[] mensagensVitoria = {
            "Você se levanta, olha para os prisioneiros, vai de um em um e os liberta,\n"+
            "e todos vocês saem em direção à noite, retornando à cidade.\n",
            "Seu dever está cumprido.",
            "Parabéns! Você completou o desafio",
            "O fim de sua jornada está prestes a acabar, depois de tantas lutas e vitórias,\n"+
            "você relembra todas as suas aventuras já vivenciadas\n"+
            "que levaram você até aqui, onde jaz o fim.\n"+
            "O fim de apenas uma aventura, pois enquanto há de viver,\n"+
            "a sua última batalha vencida nunca será sua BATALHA FINAL.",
            "Fim da Jornada."
    };

    public static void dispararMensagens(String[] listaDeMensagens) {

        Arrays.asList(listaDeMensagens).forEach(mensagem->
                JOptionPane.showMessageDialog(null,mensagem));
    }

    public static void dispararMensagens(String[] listaDeMensagens, Jogador jogador) {

        if(jogador.getSexo().equals("F")) {
            mensagensMotivacao[0]=mensagensMotivacao[0].replace("Inspirado","Inspirada");
            mensagensMotivacaoGloria[3]=mensagensMotivacaoGloria[3].replace("o aguarda","a aguarda");
            mensagensContinuar[0]=mensagensContinuar[0].replace("atento","atenta");
            mensagensArmeiro[0]=mensagensArmeiro[0].replace("sozinho","sozinha");
            mensagensPegarArmadura[1]=mensagensPegarArmadura[1].replace("protegido","protegida");
            mensagensBeberPocao[0]=mensagensBeberPocao[0].replace("revigorado","revigorada");
            mensagensNaoBeber[0]=mensagensNaoBeber[0].replace("receoso","receosa");
        }
        Arrays.asList(listaDeMensagens).forEach(mensagem->
                JOptionPane.showMessageDialog(null,mensagem));
    }
}