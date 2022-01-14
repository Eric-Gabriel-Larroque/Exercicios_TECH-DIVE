package nome_do_usuario;

import java.util.Scanner;

public class nomeDoUsuario {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Insira seu sobrenome: ");
        String sobrenome = sc.nextLine();
        System.out.print("Agora, insira seu nome: ");
        String nome = sc.nextLine();
        String nomeCompleto = nome+" "+sobrenome;
        int numeroDeLetras = nomeCompleto.replaceAll(" ","").length();

        System.out.print("Seu nome completo é "+ nomeCompleto+
                ". Seu nome completo tem "+numeroDeLetras+" letras.");
    }
}
