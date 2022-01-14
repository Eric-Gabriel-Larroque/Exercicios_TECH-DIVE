package pa_e_pg;

import java.util.Scanner;

public class PA_PG {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Insira um número inteiro inicial da Progressão: ");
        final int PRIMEIRO_TERMO = Integer.parseInt(sc.nextLine());
        System.out.print("Agora, insira um valor inteiro para a razão da progressão: ");
        final int RAZAO = Integer.parseInt(sc.nextLine());
        System.out.print("Você gostaria de fazer o cálculo de uma Progressão Aritmética (PA)" +
                "ou uma Progressão Geométrica (PG) ? \n1. PA\n2. PG\n-> ");
        final int RESPOSTA = Integer.parseInt(sc.nextLine());
        int enesimo_termo = PRIMEIRO_TERMO;

        switch (RESPOSTA) {
            case 1:

                    System.out.print("Seu 1º termo é: "+enesimo_termo+"\n");
                for(int i = 2;i<=10;i++) {
                    enesimo_termo+=RAZAO;
                    System.out.print("Seu "+ i+"º"+" termo é "+enesimo_termo+"\n");
                }
                break;

            case 2:

                    System.out.print("Seu 1º termo é: "+enesimo_termo+"\n");
                for(int i = 2; i <= 10;i++) {
                    enesimo_termo*=RAZAO;
                    System.out.print("Seu "+i+"º"+" termo é "+enesimo_termo+"\n");
                }
                break;
            default:
                System.out.print("Comando não reconhecido.");
            break;
        }
    }
}