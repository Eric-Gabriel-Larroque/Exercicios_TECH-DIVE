package util;

import javax.swing.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class DataHandler {

    public static boolean dataValida(String data,LocalDate dataMinima,LocalDate datalimite) {
        boolean repetir = true;
        LocalDate dataFormatada = LocalDate.now();
        while(repetir) {
            try {
                dataFormatada = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                repetir = false;
            } catch (DateTimeParseException | NullPointerException dateTimeParseException) {
                JOptionPane.showMessageDialog(null,
                        "Formato de data errada ou fora do intervalo de tempo permitido.");
                return false;
            }
        }
        if(datalimite.equals(LocalDate.MAX))    {
            return dataFormatada.isBefore(datalimite)&&dataFormatada.isAfter(dataMinima);
         }else {
            return dataFormatada.isBefore(datalimite.plusDays(1)) && dataFormatada.isAfter(dataMinima.minusDays(1));
        }

    }

    public static LocalDate formatar(String data) {
        return LocalDate.parse(data,DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static String formatar(LocalDate data) {
            return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static LocalDate setData(String mensagem, LocalDate dataMinima,LocalDate dataLimite) {
        String data;
        do {
            data = JOptionPane.showInputDialog(null,
                    mensagem);
        }  while(!dataValida(data,dataMinima,dataLimite));
        return formatar(data);
    }

    public static Month setMes() {
        int numeroDoMes = 0;
        Month mes =  Month.from(LocalDate.now());
        do {
            try {
                numeroDoMes = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Insira um número de um mês (de 1 a 12):"));
                mes = Month.of(numeroDoMes);
            }catch (DateTimeException | NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Tipo de dado inválido ou vazio. Somente números de 1 a 12 são aceitos.",
                        "Dados inválidos",JOptionPane.ERROR_MESSAGE);
            }
        }while(!(numeroDoMes>=1&&numeroDoMes<=12));
        return mes;
    }

    public static Year setAno(int anoMinimo, int anoLimite) {
        boolean repetir = true;
        Year ano = Year.now();
        while(repetir) {
            try {
                ano = Year.parse(JOptionPane.showInputDialog(null,
                "Agora, insira um ano válido (formato xxxx)"));
                repetir = false;
            } catch (DateTimeParseException | NullPointerException dateTimeParseException) {
                JOptionPane.showMessageDialog(null,
                        "Entrada de dados vazio ou ano fora do intervalo de tempo permitido.");
            }
        }
        return ano;
    }
}
