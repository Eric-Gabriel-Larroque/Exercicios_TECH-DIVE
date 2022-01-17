package batalhafinal.modelos;

import batalhafinal.atributos.Arma;
import batalhafinal.atributos.Motivacao;
import batalhafinal.herois.Arqueiro;
import batalhafinal.herois.Guerreiro;
import batalhafinal.herois.Mago;
import batalhafinal.herois.Paladino;
import batalhafinal.util.Interacao;


import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Jogador extends Personagem implements Atacante {

    private String nome;
    private String sexo;
    private Motivacao motivacao;
    private Arma arma;
    public static final int SAUDE_MAXIMA = 200;

    public Jogador(String nome, String sexo) {
        super.setSaude(SAUDE_MAXIMA);
        this.nome = nome;
        this.sexo = sexo;
    }

    public int getSAUDE_MAXIMA() {
        return SAUDE_MAXIMA;
    }

    public static String setNome() {
        String nome = "";
        while (nome == null || nome.isBlank() || nome.isEmpty()) {
            nome = JOptionPane.showInputDialog(null, "Insira o nome do seu personagem:");
        }
        return nome;
    }

    public String getNome(){
        return this.nome;
    }

    public static String setSexo() {
        String[] sexos = {"M","m","F","f"};
        String sexo = "";
        while(!Arrays.asList(sexos).contains(sexo)) {
            sexo = JOptionPane.showInputDialog(null,
                    "Insira o sexo biológico do personagem (M ou F): ");
        }
        return sexo.toUpperCase();
    }


    public static Jogador setClasse(String nome, String sexo)
    throws IllegalArgumentException,
            NullPointerException {
        Jogador classeEscolhida = null;
        Integer[] opcoes = {1,2,3,4};
        int resposta = 0;


       do{
            try {
                resposta = Integer.parseInt( JOptionPane.showInputDialog(null,
                        "Escolha sua classe:\n1 - Arqueiro(a)\n2 - Guerreiro(a)" +
                                "\n3 - Mago(a)\n4 - Paladino(a)"));
            } catch (IllegalArgumentException illegalArgumentException) {
                JOptionPane.showMessageDialog(null,
                        "Somente números são permitidos. Tente novamente",
                        "Dado inválido",JOptionPane.ERROR_MESSAGE);
            } catch (NullPointerException nullPointerException) {
                JOptionPane.showMessageDialog(null,
                        "Entrada vazia. Tente novamente",
                        "Dado inválido",JOptionPane.ERROR_MESSAGE);
            }
        } while(resposta==0||!Arrays.asList(opcoes).contains(resposta));

       switch (resposta) {
           case 1:
               classeEscolhida = new Arqueiro(nome,sexo);
               break;
           case 2:
              classeEscolhida =  new Guerreiro(nome,sexo);
               break;
           case 3:
               classeEscolhida = new Mago(nome,sexo);
               break;
           case 4:
               classeEscolhida = new Paladino(nome,sexo);
               break;
       }
       return classeEscolhida;
    }

        public Arma setArma(Jogador classe) {
        List<Arma> listaArmas = new ArrayList();
        String [] opcoes = {"1","2"};
        String resposta = "";
        String todasAsArmas = "";

        switch (classe.getClass().getSimpleName()) {

            case "Arqueiro":
                listaArmas.add(Arma.ARCO);
                listaArmas.add(Arma.BESTA);
                break;
            case "Guerreiro":
                listaArmas.add(Arma.ESPADA);
                listaArmas.add(Arma.MACHADO);
                break;
            case "Mago":
                listaArmas.add(Arma.CAJADO);
                listaArmas.add(Arma.LIVRODEFEITICOS);
                break;
            case "Paladino":
                listaArmas.add(Arma.MARTELO);
                listaArmas.add(Arma.CLAVA);
                break;
        }

        for(int i = 0; i < listaArmas.size();i++) {
            todasAsArmas += "\n"+(i+1) + " - "+listaArmas.get(i).getArma();
        }

        while(!Arrays.asList(opcoes).contains(resposta)) {
            resposta = JOptionPane.showInputDialog(null,
                    "Escolha a sua arma: "+todasAsArmas);
        }
        this.arma = listaArmas.get(Integer.parseInt(resposta)-1);
        JOptionPane.showMessageDialog(null,"Sua arma é o(a) "+arma);

        JOptionPane.showMessageDialog(null,
                "Bem-vindo(a), "+ classe.getClass().getSimpleName()+"(a) "+classe.getNome()+"."+
                        "\nSeus atributos são:\nAtaque: "+classe.getPontosDeAtaque()+"\nDefesa: "+classe.getPontosDeDefesa()+
                        "\nAtaque do(a) "+classe.getArmaNome()+": "+classe.getArmaPontos());
        return this.arma;
    }

    public static void setMotivacao(Jogador jogador) {
    String motivacao = Interacao.escolherOpcao("Qual a sua motivação, aventureiro ?", Motivacao.GLORIA.getValue(),
                        Motivacao.VINGANCA.getValue());

    if(motivacao.equals("Vingança")) {
        jogador.motivacao = Motivacao.VINGANCA;
    } else {
        jogador.motivacao = Motivacao.GLORIA;
        }
    }

    public Motivacao getMotivacao() {
        return this.motivacao;
    }

    public String getArmaNome() {
        return arma.getArma();
    }

    public int getArmaPontos() {
        return arma.getPontosDeAtaque();
    }

    @Override
    public void atacar(Personagem alvo) {
        String fugirOuLutar = Interacao.escolherOpcao("Você quer atacar ou fugir?",
                "Atacar","Fugir");

        if(fugirOuLutar.equals("Fugir")) {
            JOptionPane.showMessageDialog(null,
                    "Você não estava preparado para a força do inimigo,\n" +
                            "e decide fugir para que possa tentar novamente em uma próxima vez.");

            System.exit(0);
        } else {
            int novaSaude = 0;
            int D20 = (int) Math.ceil(Math.random()*(20-1)+1);
            int danoDoAtaque = alvo.getPontosDeDefesa()-(D20+arma.getPontosDeAtaque()+this.getPontosDeAtaque());
            String mensagemPadrao = "";

                   switch (this.arma.getArma()) {
                       case "espada":
                       case "machado":
                       case "martelo":
                       case "clava":
                           mensagemPadrao+= "com seu/sua "+this.arma.getArma();
                           break;
                       case "arco":
                           mensagemPadrao+="com seu arco a flecha atingiu";
                           break;
                       case "besta":
                           mensagemPadrao+="com sua besta o virote atingiu";
                           break;
                       case "cajado":
                           mensagemPadrao+="com seu cajado, lançando uma bola de fogo";
                            break;
                       case "livro de feitiços":
                           mensagemPadrao+="absorvendo energia do livro com uma mão e liberando com a outra";
                           break;
                   }

            if(D20==1) {
                JOptionPane.showMessageDialog(null,
                        "Você errou seu ataque! O inimigo não sofreu dano algum.");
            } else if (D20 == 20) {
                danoDoAtaque = D20+arma.getPontosDeAtaque()+this.getPontosDeAtaque();
                novaSaude = alvo.getSaude()-Math.abs(danoDoAtaque);
                alvo.setSaude(novaSaude);
                JOptionPane.showMessageDialog(null,
                        "Você acertou um ataque crítico!\n"+mensagemPadrao+
                                "\ne causou "+Math.abs(danoDoAtaque)+" de dano no inimigo!");
            } else {
                novaSaude = alvo.getSaude()-Math.abs(danoDoAtaque);
                alvo.setSaude(novaSaude);
                JOptionPane.showMessageDialog(null,
                        "Você atacou "+mensagemPadrao +
                        "\ne causou "+Math.abs(danoDoAtaque)+" de dano no inimigo!");
            }
        }

    };
}