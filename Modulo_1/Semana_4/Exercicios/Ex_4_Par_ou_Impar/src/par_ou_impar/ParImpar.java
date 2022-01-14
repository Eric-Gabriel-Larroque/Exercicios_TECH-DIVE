package par_ou_impar;

import java.util.Scanner;

public class ParImpar {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Insira um número inteiro: ");
        final String PAR_OU_IMPAR = Integer.parseInt(sc.nextLine()) % 2 == 0 ? "O número é par" : "O número é ímpar.";
        System.out.print(PAR_OU_IMPAR);
    }
}
