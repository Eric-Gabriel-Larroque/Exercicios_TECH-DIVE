package maior_ou_menor;

import java.time.LocalDate;
import java.util.Scanner;

public class MaiorOuMenorDeIdade {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Digite sua data de nascimento (no formato dd/mm/aaaa): ");
        final LocalDate DATA_ATUAL = LocalDate.now();
        final String DATA_DE_NASCIMENTO = sc.nextLine();
        final int DIA_DO_NASCIMENTO = Integer.parseInt(DATA_DE_NASCIMENTO.substring(0,2));
        final int MES_DO_NASCIMENTO = Integer.parseInt(DATA_DE_NASCIMENTO.substring(3,5));
        final int ANO_DE_NASCIMENTO = Integer.parseInt(DATA_DE_NASCIMENTO.substring(6));
        final int DIA_ATUAL = DATA_ATUAL.getDayOfMonth();
        final int MES_ATUAL = DATA_ATUAL.getMonthValue();
        final int ANO_ATUAL = DATA_ATUAL.getYear();
         int idade = ANO_ATUAL-ANO_DE_NASCIMENTO;
         int VERIFICA_IDADE = MES_ATUAL >= MES_DO_NASCIMENTO && DIA_ATUAL >= DIA_DO_NASCIMENTO ? idade : --idade;
        final String RESPOSTA_FINAL =  idade >= 18 ? "Você é maior de idade" : "Você é menor de idade";

        System.out.print(RESPOSTA_FINAL);
    }
}
