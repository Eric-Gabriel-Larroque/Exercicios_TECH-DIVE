package maiordeidade;

import javax.swing.*;
import java.time.LocalDate;
import java.time.Period;
import static util.DataHandler.*;

public class MaiorDeIdade {

    public void verificaIdade() {
        LocalDate dataNascimento;
        LocalDate dataMinima = LocalDate.now().minusYears(120);
        LocalDate dataMaxima = LocalDate.now();
        boolean repetir = true;
        dataNascimento = setData("Insira sua data de nascimento:",dataMinima,dataMaxima);
        String mensagem = Period.between(dataNascimento,dataMaxima).getYears()<18 ?
                "Você é menor de idade, portanto não pode acessar o sistema.":
                "Você é maior de idade. Pode acessar o sistema.";
            JOptionPane.showMessageDialog(null,mensagem);
    }
    public static void main(String[] args) {
        new MaiorDeIdade().verificaIdade();
    }
}