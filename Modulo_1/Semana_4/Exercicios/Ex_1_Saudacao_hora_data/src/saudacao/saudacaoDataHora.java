package saudacao;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class saudacaoDataHora {
    public static void main(String[] args) {
        final String HORARIO_ATUAL = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
        final String DATA_ATUAL = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
        final int HORA = Integer.parseInt(HORARIO_ATUAL.substring(0,2));
        String saudacao = HORA >= 1 && HORA <= 12 ? "Bom dia!" :
                     HORA >12 && HORA <=17 ? "Boa tarde!" : "Boa noite!";


        System.out.println(saudacao + "Agora é "+HORARIO_ATUAL+", dia "+DATA_ATUAL+".");


    }
}
