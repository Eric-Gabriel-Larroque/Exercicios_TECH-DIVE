package contabancaria;

import contabancaria.contas.Conta;
import contabancaria.contas.ContaCorrente;
import contabancaria.contas.ContaPoupanca;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Agencia {

    private List<Conta> contas = new ArrayList<>();

    void adiciona(Conta c) {
        contas.add(c);
    }

    int getQuantidadeDeContas() {
        return contas.size();
    }

    Conta buscaPorTitular(String nomeDoTitular) {
        Conta contaEscolhida = null;
        String tipoDeConta = "";
        for(Conta conta: contas) {
            if(conta.getTitular().getNome().equals(nomeDoTitular)){
                contaEscolhida = conta;
                tipoDeConta+=conta.getClass().getSimpleName();
            }
        }

        if(contaEscolhida==null){
            JOptionPane.showMessageDialog(null,"Não foi encontrado nenhuma conta com" +
                    " esse titular.");
        } else {
            JOptionPane.showMessageDialog(null,
                    "O titular com o nome "+contaEscolhida.getTitular().getNome()+" tem uma "+tipoDeConta);
        }
        return contaEscolhida;
    }

    public static void main(String[] args) {
        Agencia agencia = new Agencia();

        //populando lista
                agencia.adiciona( new ContaPoupanca());
        agencia.adiciona(new ContaCorrente());

        //verificando tamanho da lista:
        System.out.println("Tamanho da lista de contas: "+agencia.getQuantidadeDeContas());

        agencia.buscaPorTitular("Gabriel");
    }
}
