package calculadora;

import java.util.Scanner;

public class Calculadora {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Insira um número: ");
        final int PRIMEIRO_OPERANDO = Integer.parseInt(sc.nextLine());
        System.out.print("Insira o segundo número: ");
        final int SEGUNDO_OPERANDO = Integer.parseInt(sc.nextLine());
        System.out.print("Escolha dentre as quatro opções:\n1.Adição;\n2.Subtração;\n3.Divisão\n4.Multiplicação\n-> ");
        final int ESCOLHA = sc.nextInt();

        switch (ESCOLHA) {
            case 1:
                System.out.print("Você escolheu adição. O resultado é: "+(PRIMEIRO_OPERANDO+SEGUNDO_OPERANDO));
            break;
            case 2:
                System.out.print("Você escolheu subtração. o resultado é "+(PRIMEIRO_OPERANDO-SEGUNDO_OPERANDO));
            break;
            case 3:
                System.out.print("Você escolheu divisão. O resultado é "+(PRIMEIRO_OPERANDO/SEGUNDO_OPERANDO));
            break;
            case 4:
                System.out.print("Você escolheu multiplicação. O resultado é "+(PRIMEIRO_OPERANDO*SEGUNDO_OPERANDO));
            break;
            default:
                System.out.print("Comando não reconhecido.");
            break;
        }
    }
}
