package util;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimeHandler {

    public static boolean horarioValido(String horario) {
        boolean repetir = true;
        LocalTime horaFormatada = LocalTime.now();
        while(repetir) {
            try {
                horaFormatada = LocalTime.parse(horario);
                repetir = false;
            } catch (DateTimeParseException | NullPointerException dateTimeParseException) {
                JOptionPane.showMessageDialog(null,
                        "Formato de horário errado ou fora do intervalo de tempo permitido.");
                return false;
            }
        }
        return horaFormatada.equals(LocalTime.parse(horario));
    }

    public static LocalTime setHorario(String mensagem) {
        String horario;
        do {
            horario = JOptionPane.showInputDialog(null,
                    mensagem);
        }  while(!horarioValido(horario));
        return formatar(horario);
    }

    public static LocalTime formatar(String horario) {
        return LocalTime.parse(horario);
    }

    public static String formatar(LocalTime horario) {
        return LocalTime.from(horario).format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
