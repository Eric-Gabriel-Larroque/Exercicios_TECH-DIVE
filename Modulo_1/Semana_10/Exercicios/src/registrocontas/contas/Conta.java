package registrocontas.contas;

import registrocontas.cliente.Cliente;

import javax.swing.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static util.FileHandler.*;
import static util.Validacao.*;
import static util.DataHandler.*;
import static util.TimeHandler.*;
public abstract class Conta {

    private int numeroConta = (int) Math.ceil(Math.random()*(99999-10000)+10000);
    private double saldo;
    private static List<Integer> listaNumerosConta = new ArrayList<>();
    private Cliente cliente;

    public Conta(double saldo) {
        this.saldo = saldo;
        setCliente();
        setNumeroConta();
        listaNumerosConta.add(numeroConta);
        cadastrarConta();
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    private void setNumeroConta() {
        while (numeroConta==0&&listaNumerosConta.contains(numeroConta)) {
            numeroConta = (int) Math.ceil(Math.random()*(99999-10000)+10000);
        }
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    private void setCliente() {
        if(this.cliente==null) {
            this.cliente = new Cliente();
        }else{
            this.cliente = this.getCliente();
        }
    }

    void setSaldo(double valor){
        this.saldo = valor;
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar()  {
        double valor = validaNumero("Quanto você quer depositar?");
        double valorAntigo = this.saldo;
        if(valor<=0) {
            JOptionPane.showMessageDialog(null,"Valor menor ou igual a zero",
                    "Valor inválido",JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.saldo += valor;
        String mensagemDeposito = String.format(
                new Locale("pt","BR"),
                "----------------DEPÓSITO---------------%n" +
                        "Nome: %s%n" +
                        "Conta: %06d%n" +
                        "Deposito de: %.2f%n" +
                        "Dia: %s%n" +
                        "Hora: %s%n" +
                        "---------------------------------------%n",
                this.getCliente().getNome(),this.getNumeroConta(),valor,formatar(LocalDate.now()),
                formatar(LocalTime.now()));
        atualizaSaldo("cadastro.txt",this,String.format("%.2f",valorAntigo));

        try{
            escreverNoArquivo("deposito.txt",mensagemDeposito);
        }catch (Exception e) {
            System.err.println("Ocorreu um erro: "+e.getMessage());
        }
    }

    public void transferir(ContaCorrente conta) {
        double valorASerTransferido = validaNumero("Quanto você gostaria de tranferir?");
        while(this.saldo-valorASerTransferido<0){
            valorASerTransferido = validaNumero("Insira o valor a ser tranferido corretamente:");
        }
        this.setSaldo(this.getSaldo()-valorASerTransferido);
        conta.setSaldo(conta.getSaldo()+valorASerTransferido);
        double saldoAntigo = this.getSaldo()+valorASerTransferido;
        double saldoAntigoDoDestinatario = conta.getSaldo()-valorASerTransferido;
        atualizaSaldo("cadastro.txt",conta,String.format("%.2f",saldoAntigoDoDestinatario));
        atualizaSaldo("cadastro.txt",this,String.format("%.2f",saldoAntigo));
        String mensagemTransferencia = String.format(
                new Locale("pt","BR"),
                "----------------TRANSFERENCIA---------------%n" +
                        "Nome Rementente: %s%n" +
                        "Conta Remetente: %06d%n" +
                        "Tranferencia de: %.2f%n" +
                        "Nome Destinatario: %s%n" +
                        "Conta Destinatario: %06d%n" +
                        "Dia: %s%n" +
                        "Hora: %s%n" +
                        "---------------------------------------%n",
                this.getCliente().getNome(),this.getNumeroConta(),valorASerTransferido,
                conta.getCliente().getNome(),conta.getNumeroConta(), formatar(LocalDate.now()),
                formatar(LocalTime.now()));
        try {
            escreverNoArquivo("transferencia.txt",mensagemTransferencia);
        }catch (Exception e) {
            System.err.println("Ocorreu um erro: "+e.getMessage());
        }
    }

    public void saque() {
        double valor = validaNumero("Quanto você quer sacar?");
        double valorAntigo = this.saldo;
        if(valor>this.saldo||valor<=0) {
            JOptionPane.showMessageDialog(null,"Valor inválido para saque",
                    "valor inválido",JOptionPane.ERROR_MESSAGE);
        }
        this.saldo-=valor;
        String mensagemSaque = String.format(
                new Locale("pt","BR"),
                "----------------SAQUE---------------%n" +
                        "Nome: %s%nConta: %06d%nSaque de: %.2f%nDia: %s%nHora: %s%n" +
                        "------------------------------------%n",
                this.getCliente().getNome(),this.getNumeroConta(),valor,formatar(LocalDate.now()),
                formatar(LocalTime.now()));
        atualizaSaldo("cadastro.txt",this,String.format("%.2f",valorAntigo));

        try {
            escreverNoArquivo("saque.txt",mensagemSaque);
        }catch (Exception e) {
            System.err.println("Ocorreu um erro: "+e.getMessage());
        }
    }

    public void cadastrarConta() {
        String cadastro = String.format(new Locale("pt","BR"),
                "Nome: %s, Conta: %06d, Saldo: %.2f%n",
                this.getCliente().getNome(),this.getNumeroConta(),this.getSaldo());
        try {
            escreverNoArquivo("cadastro.txt",cadastro);
        }catch (IOException e) {
            System.err.println("Erro: "+e.getMessage());
        }
    }

}
