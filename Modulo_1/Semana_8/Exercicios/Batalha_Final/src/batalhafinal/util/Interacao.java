package batalhafinal.util;

import batalhafinal.modelos.Jogador;
import batalhafinal.inimigos.Armadilha;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Interacao {


    public static void iniciar() {
        JOptionPane.showMessageDialog(null,
                "A noite se aproxima, a lua já surge no céu, estrelas vão se acendendo,\n"
                        + "e sob a luz do crepúsculo você está prestes a entrar na fase final da sua missão.");

        JOptionPane.showMessageDialog(null,
                "Você olha para o portal à sua frente, e sabe que a partir desse ponto, sua vida mudará para sempre.");

        JOptionPane.showMessageDialog(null,
                "Memórias do caminho percorrido para chegar até aqui invadem sua mente.\n"
                        + "Você se lembra de todos os inimigos já derrotados para alcançar o covil do líder maligno.");

        JOptionPane.showMessageDialog(null,
                "Olha para seu equipamento de combate, já danificado e desgastado depois de tantas lutas.\n"
                        + "Você está a um passo de encerrar para sempre esse mal.");

        JOptionPane.showMessageDialog(null,
                "Buscando uma injeção de ânimo, você se força a lembrar o que te trouxe até aqui.");
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
            JOptionPane.showMessageDialog(null,
                    "Imagens daquela noite trágica invadem sua mente.\n"
                            + "Você nem precisa se esforçar para lembrar,\npois essas memórias estão sempre presentes,\n"
                            + "mesmo que de pano de fundo, quando você\ntem outros pensamentos em foco, elas nunca o deixaram.\n"
            );
            JOptionPane.showMessageDialog(null,
                    "Elas são o combustível que te fizeram chegar até aqui.\n"
                            + "E você sabe que não irá desistir até ter vingado a morte\n"
                            + "daqueles que foram - e pra sempre serão -\nsua fonte de amor e desejo de continuar vivo.\n");

            JOptionPane.showMessageDialog(null,
                    "O maldito líder finalmente pagará por tanto mal causado\n" +
                            " na vida de tantos"
                            + "(e principalmente na sua).");
        } else{
            // TODO else

            JOptionPane.showMessageDialog(null,
                    "Você já consegue visualizar na sua mente o povo da cidade\n" +
                            "te recebendo de braços abertos, bardos criando canções\n" +
                            "sobre seus feitos heróicos, nobres te presenteando\n" +
                            "com jóias e diversas riquezas,taberneiros se\n" +
                            "recusando a cobrar por suas bebedeiras e comilanças.\n");
            JOptionPane.showMessageDialog(null,
                    "Desde já, você sente o amor do público, te louvando\n" +
                            "a cada passo que dá pelas ruas, depois de\n" +
                            "destruir o vilão que tanto assombrou a paz de todos.");
            JOptionPane.showMessageDialog(null,
                    "Porém, você sabe que ainda falta o último ato dessa história.\n"
                            +"Você se concentra na missão.");
            JOptionPane.showMessageDialog(null,
                    "A glória o aguarda, mas não antes da última batalha.");
        }
    }

    public static void continuarOuDesistir() {
        JOptionPane.showMessageDialog(null,
                "Inspirado pelo motivo que te trouxe até aqui, você sente seu coração ardendo em chamas,\n"
                        +       "suas mãos formigarem em volta da sua arma. Você a segura com firmeza.\n" +
                        "Seu foco está renovado.Você avança pelo portal.");

        JOptionPane.showMessageDialog(null,
                "A escuridão te envolve. Uma iluminação muito fraca entra pelo portal às suas costas.\n"
                        + "À sua frente, só é possível perceber que você se encontra em um corredor extenso.");

        JOptionPane.showMessageDialog(null,
                "Você só pode ir à frente, ou desistir.");

        String continuarOuDesistir =
                escolherOpcao("Você quer ir em frente ou desistir?",
                        "Continuar","Desistir");
        // TODO: if (desiste)
        if(continuarOuDesistir.equals("Desistir")) {

            JOptionPane.showMessageDialog(null,
                    "O medo invade o seu coração e você sente que ainda não está à altura do desafio.\n"
                            + "Você se volta para a noite lá fora, e corre em direção à segurança.");

            System.exit(0);
        }
    }

    public static void passarPelaPorta( Jogador jogador) {
        JOptionPane.showMessageDialog(null,
                "Você caminha, atento a todos os seus sentidos, por vários metros,\n"
                        +       "até visualizar a frente uma fonte de luz, que você imagina ser\n" +
                        "a chama de uma tocha,vindo de dentro de uma porta aberta.");

        JOptionPane.showMessageDialog(null,
                "Você se pergunta se dentro dessa sala pode haver inimigos,\n" +
                        "ou alguma armadilha, e pondera sobre como passar pela porta.");

        String passarPelaPorta =
                escolherOpcao("Como você deseja passar pela porta?",
                        "Andando cuidadosamente","Correndo","Saltando");

        if(passarPelaPorta.equals("Saltando")) {
            JOptionPane.showMessageDialog(null,
                    "Você se concentra e pula em direção à luz," +
                            "\nsaltando de antes da porta até o interior da sala.");
        }
        else if(passarPelaPorta.equals("Correndo")) {
            JOptionPane.showMessageDialog(null,
                    "Você respira fundo e desata a correr em direção à sala.");
            JOptionPane.showMessageDialog(null,
                    "Quando passa pela porta, sente que pisou em uma pedra solta,\n"
                            +"mas não dá muita importância e segue para dentro da sala,\n" +
                            "olhando ao redor à procura de inimigos.");
            JOptionPane.showMessageDialog(null,
                    "Não tem ninguém, mas você ouve sons de flechas\n" +
                            "batendo na pedra atrás de você,e quando se vira,\n" +
                            "vê várias flechas no chão.");
            JOptionPane.showMessageDialog(null,
                    "Espiando pela porta, você entende que pisou em uma armadilha\n" +
                            "que soltou flechas de uma escotilha aberta no teto,\n"
                            +"mas por sorte você entrou correndo e conseguiu escapar desse ataque surpresa.");
        }

        else {
            JOptionPane.showMessageDialog(null,
                    "Você toma cuidado e vai caminhando vagarosamente em direção à luz.");
            JOptionPane.showMessageDialog(null,
                    "Quando você pisa exatamente embaixo da porta, você sente o chão ceder levemente,\n" +
                            "como se tivesse pisado em uma pedra solta.\n");
            JOptionPane.showMessageDialog(null,
                    "Você ouve um ruído de mecanismos se movimentando,\n" +
                            "e uma escotilha se abre no teto atrás de você, no corredor.\n");
            JOptionPane.showMessageDialog(null,
                    "Flechas voam da escotilha em sua direção,\n" +
                            "e você salta para dentro da sala,\n" +
                            "porém uma delas te acerta na perna.");

            new Armadilha().atacar(jogador);
        }
    }

    public static void prologoArmeiro() {
        JOptionPane.showMessageDialog(null,
                "Você se encontra sozinho em uma sala quadrada,\n" +
                        "contendo uma porta em cada parede. Uma delas foi\n" +
                        "aquela pela qual você entrou, que estava aberta," +
                        "e as outras três estão fechadas.\n");
        JOptionPane.showMessageDialog(null,
                "A porta à sua frente é a maior das quatro,\n" +
                        "com inscrições em seu entorno em uma língua que você não sabe ler,\n" +
                        "mas reconhece como sendo a língua antiga utilizada pelo inimigo.");
        JOptionPane.showMessageDialog(null,
                "Você se aproxima da porta e percebe que ela está trancada por\n" +
                        "duas fechaduras douradas,e você entende que precisará primeiro\n" +
                        "derrotar o que estiver nas outras duas portas laterais,\n" +
                        "antes de conseguir enfrentar o líder.");


        JOptionPane.showMessageDialog(null,
                "Você se dirige para a porta à direita.");

        JOptionPane.showMessageDialog(null,
                "Você se aproxima, tentando ouvir o que acontece porta adentro,\n" +
                        "mas não escuta nada. Segura com mais força sua arma com uma mão,\n" +
                        " enquanto empurra a porta com a outra.");
        JOptionPane.showMessageDialog(null,
                "Ao entrar, você se depara com uma sala espaçosa,\n" +
                        "com vários equipamentos de batalha pendurados nas\n" +
                        "paredes e dispostos em armários e mesas.\n");
        JOptionPane.showMessageDialog(null,
                "Você imagina que este seja o arsenal do inimigo, onde\n" +
                        "estão guardados os equipamentos que seus soldados utilizam\n" +
                        "quando saem para espalhar o terror nas cidades e vilas da região.\n"
        );
        JOptionPane.showMessageDialog(null,
                "Enquanto seu olhar percorre a sala, você ouve a porta se fechando\n" +
                        "e gira rapidamente para olhar para trás.Ali, de pé entre você e a porta\n" +
                        "fechada, bloqueando o caminho do seu destino, está um dos capitães do inimigo."
        );

        JOptionPane.showMessageDialog(null,
                "Um orque horrendo, de armadura, capacete e espada\n" +
                        "em punho, em posição de combate. Ele avança em sua direção."
        );
    }

    public static void pegarNovaArmadura(Jogador jogador) {

        JOptionPane.showMessageDialog(null,
                "Após derrotar o Armeiro, você percebe que seus equipamentos estão muito danificados.\n"
                +       "Olha em volta, encarando todas aquelas peças de armaduras resistentes e em ótimo estado.");


        String pegarArmadura = escolherOpcao("Você gostaria de pegar a armadura?","Pegar armadura","Deixar");

        if(pegarArmadura.equals("Pegar armadura")) {
            JOptionPane.showMessageDialog(null,
                    "Você resolve usar os equipamentos do inimigo contra ele," +
                            "\ne trocar algumas peças suas, que estavam danificadas," +
                            "\npelas peças de armaduras existentes na sala.\n");
            JOptionPane.showMessageDialog(null,
                    "De armadura nova, você se sente mais protegido para os desafios à sua frente.");
            // TODO: deve ser acrescentado +5 pontos de defesa para o jogador.
            jogador.setPontosDeDefesa(jogador.getPontosDeDefesa()+5);
            JOptionPane.showMessageDialog(null,
                    "Pontos de defesa aumentados para "+jogador.getPontosDeDefesa());
        }else {

            JOptionPane.showMessageDialog(null,
                    "Você decide que não precisa utilizar nada que venha das mãos do inimigo.");
        }
    }

    public static void prologoAlquimista() {
        JOptionPane.showMessageDialog(null,
                "Em uma mesa, você encontra uma chave dourada,\n" +
                        "e sabe que aquela chave abre uma das fechaduras da\n" +
                        "porta do líder inimigo. Você pega a chave e guarda\n" +
                        "numa pequena bolsa que leva presa ao cinto.");

        JOptionPane.showMessageDialog(null,
                "Você retorna à sala anterior e se dirige à porta da esquerda.\n"
                        +"Você se aproxima, tentando ouvir o que acontece porta adentro,\n" +
                        "mas não escuta nada. Segura com mais força sua arma com uma mão,\n" +
                        "enquanto empurra a porta com a outra.");
        JOptionPane.showMessageDialog(null,
                "Ao entrar, você se depara com uma sala parecida com a do arsenal,\n" +
                        "mas em vez de armaduras, existem vários potes e garrafas de vidro\n" +
                        "com conteúdos misteriosos e de cores diversas, e você entende que\n" +
                        "o capitão que vive ali, realiza experimentos com diversos ingredientes,\n" +
                        "criando poções utilizadas pelos soldados para aterrorizar a região.");
        JOptionPane.showMessageDialog(null,
                "No fundo da sala, olhando em sua direção, está outro dos capitães do inimigo.\n" +
                        "Um orque horrendo, de armadura, cajado em punho, em posição de combate.");
        JOptionPane.showMessageDialog(null,
                "Ele avança em sua direção.");
    }

    public static void beberPocao(Jogador jogador) {

        JOptionPane.showMessageDialog(null,
                "Após derrotar o Alquimista, você olha em volta,\n" +
                        "tentando reconhecer alguma poção do estoque do inimigo.");
        JOptionPane.showMessageDialog(null,
                "Em uma mesa, você reconhece uma pequena garrafa de vidro contendo um\n" +
                        "líquido levemente rosado, pega a garrafa e pondera se deve beber um gole.");


        String beberOuNao = escolherOpcao("Você gostaria de beber da poção do alquimista?",
                "Beber", "Não beber");

        if(beberOuNao.equals("Beber")) {
            JOptionPane.showMessageDialog(null,
                    "Você se sente revigorado para seguir adiante!");
            jogador.setSaude(jogador.getSAUDE_MAXIMA());

            JOptionPane.showMessageDialog(null,
                    "Saúde restaurada para "+jogador.getSaude());
        } else {

            JOptionPane.showMessageDialog(null,
                    "Você fica receoso de beber algo produzido pelo inimigo.");
        }
    }

    public static void prologoLider() {

        JOptionPane.showMessageDialog(null,
                "Ao lado da porta, você vê uma chave dourada em cima de uma mesa,\n"
                        +"e sabe que aquela chave abre a outra fechadura da porta do líder inimigo.\n"
                        +"Você pega a chave e guarda na pequena bolsa que leva presa ao cinto.");

        JOptionPane.showMessageDialog(null,
                "De volta à sala das portas, você se dirige à porta final.\n"
                        +"Coloca as chaves nas fechaduras, e a porta se abre.\n"
                        +"Seu coração acelera, memórias de toda a sua vida passam pela sua mente,\n"
                        +"e você percebe que está muito próximo do seu objetivo final.\n");
        JOptionPane.showMessageDialog(null,
                "Segura sua arma com mais firmeza, foca no combate\n" +
                        "que você sabe que irá se seguir, e adentra a porta.");

        JOptionPane.showMessageDialog(null,
                "Lá dentro, você vê o líder sentado em uma poltrona dourada,\n" +
                        "com duas fogueiras de cada lado, e prisioneiros acorrentados às paredes.");

        JOptionPane.showMessageDialog(null,
                "Ele percebe sua chegada e se levanta com um salto,\n" +
                        "apanhando seu machado de guerra de lâmina dupla.");
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
                JOptionPane.showMessageDialog(null,
                        "Você obteve sua vingança. Você se ajoelha e cai no choro,\n" +
                                "invadido por uma sensação de alívio e felicidade. Você se vingou,\n" +
                                "tudo que sempre quis, está feito. Agora você pode seguir sua vida.");
        } else {
            JOptionPane.showMessageDialog(null,
                    "O êxtase em que você se encontra não cabe dentro de si.\n" +
                            " Você se ajoelha e grita de alegria.\n" +
                            "A glória o aguarda, você a conquistou.");
        }
        JOptionPane.showMessageDialog(null,
                "Você se levanta, olha para os prisioneiros, vai de um em um e os liberta,\n"
                        +"e todos vocês saem em direção à noite, retornando à cidade.\n"
                        +"Seu dever está cumprido.");
    }
}
