package excecoes;

import java.util.*;
import java.util.Scanner;

public class IncluindoExcecoes {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);
        boolean repetir = true;
        System.out.println("Eu sei dividir");

        try {
            do {
                try {
                    System.out.print("\nInforme o primeiro valor: ");
                    int x = teclado.nextInt();

                    System.out.print("\nInforme o segundo valor: ");
                    int y = teclado.nextInt();

                    double r =  x / y;
                    System.out.print("\nResultado da divisão: " + r);
                    repetir = false;

                } catch (ArithmeticException arithmeticException) {
                    System.err.printf("%nErro: %s %n",arithmeticException);
                    System.out.println("Denominador não pode ser zero.");
                } catch (InputMismatchException inputMismatchException) {
                    teclado.nextLine();
                    System.err.printf("%nErro: %s %n", inputMismatchException);
                    System.out.println("Favor inserir um número inteiro.");
                } catch (NullPointerException nullPointerException) {
                        System.err.printf("%nErro: %s %n", nullPointerException);
                        System.out.println("Favor inserir um número inteiro.");
                }
            } while (repetir);
        } finally {
            teclado.close();
        }
    }
}
