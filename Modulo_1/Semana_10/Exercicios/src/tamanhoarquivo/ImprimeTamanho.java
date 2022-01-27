package tamanhoarquivo;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ImprimeTamanho {

    public static void tamanhoArquivo(String caminho) {

        Path caminhoArquivo = Paths.get(caminho);

        try {
            long bytes = Files.size(caminhoArquivo);

            JOptionPane.showMessageDialog(null,
                    "Tamanho do arquivo em bytes: "+bytes+"\n"+
                    "Tamanho em Kylobytes: "+(bytes/1024)+"\n"+
                    "Tamanho em Megabytes: "+(bytes/1024/1024));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ImprimeTamanho.tamanhoArquivo("arquivo.txt");
    }
}
