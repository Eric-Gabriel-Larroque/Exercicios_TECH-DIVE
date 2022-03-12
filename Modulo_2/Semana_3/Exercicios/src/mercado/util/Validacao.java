package mercado.util;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Validacao {
    private static Scanner sc =  new Scanner(System.in);

    public static String validaString(String mensagem){
        boolean repetir = true;
        String entrada = null;
        while(repetir) {
            System.out.print(mensagem);
            entrada = sc.nextLine();
            if(entrada==null||entrada.isBlank()||entrada.isEmpty()) {
                System.err.println("Entrada de dados inválida. Tente novamente");
            }else {
                repetir = false;
            }
        }
        return entrada;
    }

    public static int escolherOpcao(String mensagem, Object... opcoesPossiveis) {

        List<Integer> opcoes = new ArrayList<>();
        String listarOpcoes = "";
        int resposta = 0;

        for(int i = 0; i < opcoesPossiveis.length;i++) {
            opcoes.add(i+1);
            listarOpcoes+= "\n"+(i+1)+" - "+opcoesPossiveis[i];
        }
        while(!opcoes.contains(resposta)) {
                resposta = validaNumeros(mensagem+listarOpcoes+"\n--> ");
        }
        --resposta;
        return  resposta;
    }

    public static int validaNumeros(String mensagem){
        boolean repetir = true;
        int resposta = 0;
        do{
            try{
                System.out.print(mensagem);
                resposta =
                        Integer.parseInt(sc.nextLine());
                repetir = false;
            }catch (NullPointerException nullPointerException ) {
                System.err.println("Entrada de dados vazia");
            }catch (NumberFormatException numberFormatException) {
                System.err.println("Somente números são permitidos");
            } catch (Exception e) {
                System.err.println("Erro: "+e.getMessage());
            }
        }while(repetir);
        return resposta;
    }
}
