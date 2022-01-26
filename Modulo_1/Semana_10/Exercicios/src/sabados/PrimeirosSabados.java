package sabados;

import util.DataHandler;

import javax.swing.*;
import java.time.*;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.*;

public class PrimeirosSabados extends DataHandler {

    public static void primeirosSabados() {


        int anoAtual = LocalDate.now().getYear();
        String[] mesesDoAno = {"Janeiro","Fevereiro","Março","Abril","Maio","Junho","Julho",
        "Agosto","Setembro","Outubro","Novembro","Dezembro"};
        LocalDate primeiroDiaDoAno = LocalDate.of(anoAtual, Month.JANUARY,1);
        TemporalAdjuster primeiroSabado = TemporalAdjusters.firstInMonth(DayOfWeek.SUNDAY);
        Map<String,String> listaPrimeirosSabados = new LinkedHashMap<>() {
        };
        for(int i = 0; i < 12; i++) {
            listaPrimeirosSabados.put("\n"+mesesDoAno[i],
                    formatar(primeiroDiaDoAno.plusMonths(i).with(primeiroSabado)));
        }
        JOptionPane.showMessageDialog(null,
                "Primeiros sábados de cada mês: "+listaPrimeirosSabados);
    }

    public static void main(String[] args) {
        PrimeirosSabados.primeirosSabados();
    }
}
