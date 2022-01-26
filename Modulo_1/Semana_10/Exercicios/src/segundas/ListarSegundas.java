package segundas;
import javax.swing.*;
import java.time.*;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static util.DataHandler.*;


public class ListarSegundas {

    public static void segundasFeiras() {
        Month mes = setMes();
        Year ano = setAno(Year.MIN_VALUE,Year.MAX_VALUE);
        TemporalAdjuster segundasDoMes =  TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY);
        TemporalAdjuster ultimoDia = TemporalAdjusters.lastDayOfMonth();
        LocalDate dataEscolhida = LocalDate.of(ano.getValue(),mes.getValue(),1).with(segundasDoMes);
        LocalDate diaFinal = LocalDate.of(ano.getValue(),mes.getValue(),1).with(ultimoDia);

        List<String> listaAnoMes = new ArrayList<>();
        listaAnoMes.add(formatar(dataEscolhida));
        while(dataEscolhida.isBefore(diaFinal)){
            dataEscolhida = dataEscolhida.plusWeeks(1);
            listaAnoMes.add(formatar(dataEscolhida));
        }

        JOptionPane.showMessageDialog(null,
                "Lista de segundas no mês "+mes.getValue()+" do ano "+ano.getValue()+":\n"+
                listaAnoMes);
    }

    public static void main(String[] args) {
        ListarSegundas.segundasFeiras();
    }
}
