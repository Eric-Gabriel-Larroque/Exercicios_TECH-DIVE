package registrocontas;
import registrocontas.contas.ContaCorrente;
import javax.swing.*;
import java.util.Arrays;

public class Agencia {

    public static void atendimento(ContaCorrente conta1, ContaCorrente conta2) {
        String[] opcoes = {"1","2","3","4"};
        String resposta = "0";
        while(!Arrays.asList(opcoes).contains(resposta)) {
          resposta = JOptionPane.showInputDialog("O que deseja fazer,"+conta1.getCliente().getNome()+"?\n"
                  +"\n1 - Deposito" +
                   "\n2 - Saque" +
                   "\n3 - Tranferência" +
                   "\n4 - Sair");
        }
        switch (resposta) {
            case "1":
                conta1.depositar();
            break;
            case "2":
                conta1.saque();
                break;
            case "3":
                conta1.transferir(conta2);
                break;
            case "4":
                JOptionPane.showMessageDialog(null,"Volte sempre! :)");
                System.exit(0);
        }
        atendimento(conta1,conta2);
    }

    public static void preAtendimento() {
        JOptionPane.showMessageDialog(null,
                "Exercício criado somente para fins de aprendizado referente" +
                        "\nàs classes criadas para leitura e escrita de arquivo.");
        JOptionPane.showMessageDialog(null,
                "Cada conta já vem com saldo de R$: 1000,00 e de início serão criados 2 " +
                        "\ninstâncias de clientes para poder simular uma transferência.");
        JOptionPane.showMessageDialog(null,
                "Cada um precisa apenas de um nome. O resto será criado automáticamente.");
    }

    public static void main(String[] args) {
        Agencia.preAtendimento();
        ContaCorrente conta1 = new ContaCorrente(1000);
        ContaCorrente conta2 = new ContaCorrente(1000);
        Agencia.atendimento(conta1,conta2);
    }
}