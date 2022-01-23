package hoteltechdive.reserva;

import hoteltechdive.enums.Quartos;

import javax.swing.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static hoteltechdive.util.DataHandler.*;
import static hoteltechdive.util.Validacao.escolherOpcao;
import static hoteltechdive.util.Validacao.validaNumeros;

//RESERVA DEVE SER COM 60 DIAS DE ANTECEDÊNCIA

//PERIODO MÁXIMO PARA AGENDAMENTO É DE ATÉ 1 ANO
//SEM CONTAR COM OS DIAS DE ANTECEDENCIA
public class Reserva {

    private LocalDate dataMinimaParaReserva;
    private Quartos quartoEscolhido;
    private int diasDaDiaria;
    private LocalDate dataInicialDaDiaria;
    private LocalDate dataTerminoDaDiaria;
    private LocalDate dataLimiteDaReserva;
    private int acompanhantes;
    private static List<Reserva> listaReserva = new ArrayList<>();
    private double valorTotal;
    public Reserva() {
        this.dataMinimaParaReserva = setDataMinimaParaReserva();
        this.quartoEscolhido = setQuarto(this.quartoEscolhido);
        this.acompanhantes = setAcompanhantes();
        this.dataLimiteDaReserva = setDataLimiteDaReserva();
        this.dataInicialDaDiaria = setDataInicialDaDiaria();
        this.dataTerminoDaDiaria = setDataTerminoDaDiaria();
        this.diasDaDiaria = setDiasDaDiaria();
        this.valorTotal = setValorTotal();
        listaReserva.add(this);
    }



    public void reservar() {

    }

    public LocalDate getDataMinimaParaReserva() {
        setDataMinimaParaReserva();
        return dataMinimaParaReserva;
    }

     LocalDate setDataMinimaParaReserva() {
        if(dataMinimaParaReserva==null) {
            dataMinimaParaReserva = LocalDate.now().plusDays(60);
        }
        return this.dataMinimaParaReserva;
    }

    public Quartos getQuarto() {
        return quartoEscolhido;
    }

     Quartos setQuarto(Quartos quartoEscolhido) {
        List<Quartos> todosOsQuartos = new ArrayList<>(List.of(Quartos.values()));
        List<String> listaQuartos = new ArrayList<>();
        List.of(Quartos.values()).forEach(s->listaQuartos.add(
                s.getNomeDoQuarto()+ " - (Altas - "+ s.getDiariaNasAltas()+" / Baixas - "+
                s.getDiariaNasBaixas()+")"));

        int quarto =
                escolherOpcao("Escolha o tipo do seu quarto", listaQuartos.toArray());

        quartoEscolhido = todosOsQuartos.get(quarto);
        JOptionPane.showMessageDialog(null, "Você escolheu o quarto do tipo" +
                ": "+quartoEscolhido.getNomeDoQuarto());
        return quartoEscolhido;
        }

    public int getDiasDaDiaria() {
        return diasDaDiaria;
    }

     int setDiasDaDiaria() {
        if(dataInicialDaDiaria.equals(dataTerminoDaDiaria)) {
            this.diasDaDiaria = 1;
            JOptionPane.showMessageDialog(null,
                    "Dias da diária: "+this.diasDaDiaria);
            return this.diasDaDiaria;
        }
        LocalDate dataInicial = dataInicialDaDiaria;
        for(int i = 0;!dataInicial.equals(dataTerminoDaDiaria);i++){
            diasDaDiaria++;
            dataInicial = dataInicialDaDiaria.plusDays(i);
        }
        JOptionPane.showMessageDialog(null,
                "Dias da diária: "+this.diasDaDiaria);
        return this.diasDaDiaria;
    }

    public LocalDate getDataInicialDaDiaria() {
        return dataInicialDaDiaria;
    }

    LocalDate setDataInicialDaDiaria() {
        String mensagem = "Insira a data que você gostaria de marcar a reserva:\nOBS:" +
                " não pode ser antes de "+formatar(getDataMinimaParaReserva())+
                "\n e nem depois de "+formatar(getDataLimiteDaReserva());
        this.dataInicialDaDiaria = setData(mensagem,dataMinimaParaReserva.minusDays(1),dataLimiteDaReserva.plusDays(1));
        return this.dataInicialDaDiaria;
    }


    public LocalDate getDataTerminoDaDiaria() {
        return dataTerminoDaDiaria;
    }

    public LocalDate setDataTerminoDaDiaria() {
        String mensagem = "Agora, insira a data de término da sua estadia.\nDeve ser entre "+
                formatar(getDataInicialDaDiaria())+" e "
                +formatar(getDataLimiteDaReserva())+".";

        this.dataTerminoDaDiaria = setData(mensagem,dataInicialDaDiaria.minusDays(1),dataLimiteDaReserva.plusDays(1));

        return dataTerminoDaDiaria;
    }

    public LocalDate getDataLimiteDaReserva() {
        return dataLimiteDaReserva;
    }

    LocalDate setDataLimiteDaReserva() {
        this.dataLimiteDaReserva = dataMinimaParaReserva.plusYears(1);
        return this.dataLimiteDaReserva;
    }

    public boolean quartoDisponivel(){
        final LocalDate dataInicial = this.getDataInicialDaDiaria();
        final LocalDate dataTermino = this.getDataTerminoDaDiaria();
        final Quartos quarto = this.getQuarto();

       int quartosDisponiveis =
               listaReserva.stream()
                       .filter(r->r.getQuarto().equals(quarto)
                       &&r.getDataInicialDaDiaria().equals(dataInicial)
                       &&r.getDataTerminoDaDiaria().equals(dataTermino))
                       .toArray().length;

        if(quartosDisponiveis > this.getQuarto().getQuartosDisponiveis()) {
            listaReserva.remove(this);
        }

       return quartosDisponiveis > this.getQuarto().getQuartosDisponiveis();
    }

    public static List<Reserva> getListaReserva() {
        return listaReserva;
    }

    public int getAcompanhantes() {
        return acompanhantes;
    }

    int setAcompanhantes() {
        this.acompanhantes = -1;
        while(getAcompanhantes()<0||getAcompanhantes()>10) {
            acompanhantes = validaNumeros("Quantos acompanhantes vão com você? (máx 10)");
        }
        return this.acompanhantes;
    }

    public double getValorTotal() {
        return valorTotal;
    }

     double setValorTotal() {
        for(int i = 0; i<getDiasDaDiaria();i++) {
            this.valorTotal+=getDataInicialDaDiaria().plusDays(i).getMonthValue()<11&&
                        getDataInicialDaDiaria().plusDays(i).getMonthValue()>3 ?
                        getQuarto().getDiariaNasBaixas() : getQuarto().getDiariaNasAltas();
        }
        return this.valorTotal;
    }

    public void simularDiaria() {
        int qtdHospedes = validaNumeros("Insira a quantidade de hospedes");
        int contador = 0;

        String mensagemDataInicial = "Insira uma data para iniciar a reserva " +
                "(entre "+formatar(dataMinimaParaReserva)+" e "
                +formatar(dataLimiteDaReserva)+")";

        LocalDate dataInicial = setData(mensagemDataInicial,getDataMinimaParaReserva().minusDays(1),getDataLimiteDaReserva().plusDays(1));

        String mensagemDataFinal = "Agora, uma data final para a estadia entre "+
                formatar(dataInicial)+" e "+formatar(getDataLimiteDaReserva())+")";

        LocalDate dataFinal = setData(mensagemDataFinal,dataInicial.minusDays(1),getDataLimiteDaReserva().plusDays(1));

        double valorDaDiaria = 0;
        Quartos quartoSimulado = null;
        quartoSimulado = setQuarto(quartoSimulado);
        while (contador<qtdHospedes) {
            for(int i = 0;!(dataInicial.minusDays(1).plusDays(i).equals(dataFinal));i++) {
                valorDaDiaria+=dataInicial.plusDays(i).getMonthValue()<11&&
                        dataInicial.plusDays(i).getMonthValue()>3 ?
                        getQuarto().getDiariaNasBaixas() : getQuarto().getDiariaNasAltas();
            }
            contador++;
        }
        JOptionPane.showMessageDialog(null,
                "O valor total da reserva para "+qtdHospedes+" hóspede(s) para o quarto do tipo "+quartoSimulado.getNomeDoQuarto()+
                "\nentre os dias "+formatar(dataInicial)+" e "+formatar(dataFinal)+" é de R$: "+
                new DecimalFormat("0.00").format(valorDaDiaria));
    }
}