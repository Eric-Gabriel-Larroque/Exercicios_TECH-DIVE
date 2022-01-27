package quantidadecaracteres;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class QuantidadeCaracteres {

    public static void ContaCaractere(String caminho, char caractere) {
        String todoOTexto = "";
        String linha = "";
        BufferedReader br = null;
        try(FileInputStream fis = new FileInputStream(caminho);
            InputStreamReader isr = new InputStreamReader(fis);){
            br = new BufferedReader(isr);
            while((linha = br.readLine())!=null) {
               todoOTexto+=linha;
            }
            String regex = "[^"+caractere+"]";
            todoOTexto = todoOTexto.replaceAll(regex,"");
            JOptionPane.showMessageDialog(null,
                    "O texto contém "+todoOTexto.length()+" ocorrências do caractere "+caractere+".");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        QuantidadeCaracteres.ContaCaractere("qntdCaracteres.txt",'v');
    }
}