package hoteltechdive.util;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Validacao {

    public static int escolherOpcao(String mensagem, Object... opcoesPossiveis) {

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
        return  resposta;
    }

    public static int validaNumeros(String mensagem){
        boolean repetir = true;
        int resposta = 0;
        do{
            try{
                resposta =
                        Integer.parseInt(JOptionPane.showInputDialog(
                                null,
                                mensagem));
                repetir = false;
            }catch (NullPointerException nullPointerException ) {
                JOptionPane.showMessageDialog(null,
                        "Entrada de dados vazia",
                        "Dados inválidos",JOptionPane.ERROR_MESSAGE);
            }catch (NumberFormatException numberFormatException) {
                JOptionPane.showMessageDialog(null,
                        "Somente números são permitidos","Erro",JOptionPane.ERROR_MESSAGE);
            }
        }while(repetir);
        return resposta;
    }
}
