package random_number;

import java.util.Scanner;

public class RandomNumber {
    public static void main(String[] args) {
        int RANDOM_NUMBER = (int) Math.ceil(Math.random() * 5);
        Scanner sc = new Scanner(System.in);
        System.out.print("Adivinhe qual número de 1 a 5 eu estou pensando: ");
        final int ANSWER = Integer.parseInt(sc.nextLine());
        final String RESULT = ANSWER == RANDOM_NUMBER ?
                "Você acertou!" : "Você errou, o número correto é "+RANDOM_NUMBER+".";
        System.out.print(RESULT);
    }
}
