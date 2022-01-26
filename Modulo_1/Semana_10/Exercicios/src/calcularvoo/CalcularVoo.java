package calcularvoo;

import util.DataHandler;

import javax.swing.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

import static util.DataHandler.*;
import static util.TimeHandler.*;
public class CalcularVoo {
    private final LocalDate dataMinima = LocalDate.now();
    // Data máxima de desembarque é de 30 dias a contar de hoje
    private final LocalDate dataMaxima = LocalDate.now().plusDays(30);


    public void calcularVoo() {

        LocalDate dataEmbarque = setData(mensagensEmbarque(0),dataMinima,dataMaxima);
        LocalDate dataDesembarque = setData(mensagensDesembarque(dataEmbarque,0),
                dataEmbarque,dataMaxima);
        LocalTime horaEmbarque = setHorario(mensagensEmbarque(1));
        LocalTime horaDesembarque = setHorario(mensagensDesembarque(dataEmbarque,1));
        long duracaoVoo = Duration.between(horaEmbarque,horaDesembarque).toHours();
        int periodoVoo = duracaoVoo>0?Period.between(dataEmbarque,dataDesembarque).getDays():
                        Period.between(dataEmbarque,dataDesembarque).plusDays(1).getDays();
        String mensagemDuracao = "Uma viagem com embarque às "+horaEmbarque+" do dia "+
                formatar(dataEmbarque)+"\n" + "até às "+horaDesembarque+" do dia "+
                formatar(dataDesembarque)+" levaria "+periodoVoo+" dias e "+
                Math.abs(duracaoVoo)+" horas.";

        JOptionPane.showMessageDialog(null,
                mensagemDuracao);
    }

    public String mensagensEmbarque(int indice) {

        String [] mensagens =  {
                "Insira a data de embarque do voo\nentre "
                        +formatar(dataMinima)+" e "+formatar(dataMaxima)+".",
                "Insira o seu horario de embarque (no formato HH:mm): ",
        };
        return mensagens[indice];
    }

    public String mensagensDesembarque(LocalDate dataEmbarque, int indice) {
        String [] mensagens = {
          "Insira a data do desembarque\nentre "+formatar(dataEmbarque)+
          " e "+formatar(dataMaxima),
          "Agora, insira um horário para o desembarque (no formato HH:mm):"
        };
        return mensagens[indice];
    }

    public static void main(String[] args) {
        System.out.println(LocalTime.parse("06:00"));
        new CalcularVoo().calcularVoo();
    }
}
