package hoteltechdive.util;

import org.freedesktop.dbus.interfaces.Local;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
        return dataFormatada.isBefore(datalimite) && dataFormatada.isAfter(dataMinima);
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

}
