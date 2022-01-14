package calcular_imc;

import java.awt.*;
import java.util.Scanner;
public class Imc {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Insira seu peso (com pelo menos duas casas decimais): ");
        final float PESO = Float.parseFloat(sc.nextLine());
        System.out.print("Digite agora sua altura (também com pelo menos duas casas decimais): ");
        final float ALTURA = Float.parseFloat(sc.nextLine());
        final float IMC = PESO / (ALTURA*ALTURA);
        final String TAXA_IMC = IMC < 18.5 ? "Sua taxa indica que você está abaixo do peso." :
                IMC <= 24.9 ? "Sua taxa indica que você está no peso normal." :
                IMC <= 29.9 ? "Sua taxa indica obesidade Grau 1" :
                IMC <= 34.9 ? "Sua taxa indica obesidade Grau 2" : "Você está com obesidade mórbida.";
        System.out.printf("Seu IMC é de %.2f%s%s",IMC,". ",TAXA_IMC);


    }
}
