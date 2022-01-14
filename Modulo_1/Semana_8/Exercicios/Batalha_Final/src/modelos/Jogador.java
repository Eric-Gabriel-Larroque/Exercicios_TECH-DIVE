package modelos;

import atributos.Arma;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Jogador extends Personagem implements Atacante {

    private String nome;
    private String sexo;
    private String motivaco;
    private String arma;
    private static final int SAUDE_MAXIMA = 200;

    public Jogador() {
        setSaude(100);
    }

    public int getSAUDE_MAXIMA() {
        return SAUDE_MAXIMA;
    }

    public String setNome() {

        while(nome==null||nome.isBlank()||nome.isEmpty()) {
            this.nome = JOptionPane.showInputDialog(null,"Insira o nome do seu personagem:");
        }
        return this.nome;
    }

    public String setSexo() {
        String[] sexos = {"M","m","F","f"};

        while(!Arrays.asList(sexos).contains(this.sexo)) {
            this.sexo = JOptionPane.showInputDialog(null,
                    "Insira o sexo biológico do personagem (M ou F): ");
        }
        return this.sexo.toUpperCase();
    }


    public String setArma(Object classe) {
        List<String> listaArmas = new ArrayList();
        String [] opcoes = {"1","2"};
        String resposta = "";
        String todasAsArmas = "";

        switch (classe.getClass().getSimpleName()) {

            case "Arqueiro":
                listaArmas.add(Arma.ARCO.getArma());
                listaArmas.add(Arma.BESTA.getArma());
                break;
            case "Guerreiro":
                listaArmas.add(Arma.ESPADA.getArma());
                listaArmas.add(Arma.MACHADO.getArma());
                break;
            case "Mago":
                listaArmas.add(Arma.CAJADO.getArma());
                listaArmas.add(Arma.LIVRODEFEITICOS.getArma());
                break;
            case "Paladino":
                listaArmas.add(Arma.MARTELO.getArma());
                listaArmas.add(Arma.CLAVA.getArma());
                break;
        }

        for(int i = 0; i < listaArmas.size();i++) {
            todasAsArmas += "\n"+(i+1) + " - "+listaArmas.get(i);
        }

        while(!Arrays.asList(opcoes).contains(resposta)) {
            resposta = JOptionPane.showInputDialog(null,
                    "Escolha a sua arma: "+todasAsArmas);
        }
        this.arma = listaArmas.get(Integer.parseInt(resposta)-1);
        JOptionPane.showMessageDialog(null,"Sua arma é o(a) "+arma);
        return this.arma;
    }
}
