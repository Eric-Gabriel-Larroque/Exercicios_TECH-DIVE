package calcular_fatorial;

import java.util.Scanner;

public class Fatorial {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Escolha um número de 0 a 10 para obter o produto do fatorial: ");
        final int NUMERO_ESCOLHIDO =  Integer.parseInt(sc.nextLine());
        if (NUMERO_ESCOLHIDO < 0 || NUMERO_ESCOLHIDO > 10) {
            System.out.print("Número não permitido.");
        } else {
            int fatorial = 1;
            for (int i = 1; i < NUMERO_ESCOLHIDO; i++) {
                fatorial += fatorial * i;
            }
            System.out.print("O fatorial de " + NUMERO_ESCOLHIDO + " é " + fatorial);
        }
    }

}
