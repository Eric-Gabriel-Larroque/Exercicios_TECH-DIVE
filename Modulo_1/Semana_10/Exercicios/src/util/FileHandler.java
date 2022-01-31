package util;

import registrocontas.contas.Conta;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class FileHandler {

    public static void escreverNoArquivo(String caminho, String mensagem) throws IOException {

        try (FileWriter fw = new FileWriter(caminho, true);
        BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(mensagem);
        } catch (IOException e) {
            System.err.println("Ocorreu um erro: "+ e.getMessage());
        }
    }

    public static  void atualizaSaldo(String caminho, Conta conta, String saldoAntigo) {
        try {
            List<String> listaCadastros = Files.readAllLines(Paths.get(caminho),StandardCharsets.UTF_8);
            String mensagem = "";
            for(String linha: listaCadastros) {
                if(linha.equals("Nome: "+conta.getCliente().getNome()+", Conta: 0"+conta.getNumeroConta()+
                        ", Saldo: "+saldoAntigo)) {
                    mensagem+=linha.replaceAll("Saldo: "+saldoAntigo,String.format(
                            new Locale("pt","BR"),"Saldo: %.2f%n",
                            conta.getSaldo())
                    );
                }else{
                    mensagem+=linha+"\n";
                }
            }
            try (FileWriter fw = new FileWriter(caminho, false);
                 BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write(mensagem);
            } catch (IOException e) {
                System.err.println("Ocorreu um erro: "+ e.getMessage());
            }
        }catch (IOException e ) {
            System.out.println("Ocorreu um erro: "+e.getMessage());
        }
    }
}