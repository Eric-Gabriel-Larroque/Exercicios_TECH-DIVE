package calcula_media;

import java.util.Scanner;

public class MediaSemestre {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Insira a primeira nota do semestre (com pelo menos duas casas decimais): ");
        final float PRIMEIRA_NOTA = Float.parseFloat(sc.nextLine());
        System.out.print("Agora, insira a segunda nota (também com no mínimo duas casas decimais): ");
        final float SEGUNDA_NOTA = Float.parseFloat(sc.nextLine());
        System.out.print("Por fim, a terceira e última nota (com duas casas decimais, pelo menos.): ");
        final float TERCEIRA_NOTA = Float.parseFloat(sc.nextLine());
        final float MEDIA = (PRIMEIRA_NOTA+SEGUNDA_NOTA+TERCEIRA_NOTA)/3;
        System.out.printf("Sua nota final é de %.2f",MEDIA);
    }
}
