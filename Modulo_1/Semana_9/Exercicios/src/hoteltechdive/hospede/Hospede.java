package hoteltechdive.hospede;

import hoteltechdive.reserva.Reserva;
import javax.swing.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import  java.lang.NullPointerException;
import java.lang.Exception;
import java.util.List;
import static hoteltechdive.reserva.Reserva.getListaReserva;
import static hoteltechdive.util.DataHandler.*;


public class Hospede {
    private String nome;
    private long RG;
    private LocalDate dataNascimento;
    private Reserva reserva;
    private static List<Long> listaRG = new ArrayList<>();

    public Hospede(){
        this.RG = setRG();
        listaRG.add(this.RG);
        this.nome = setNome();
        this.dataNascimento = setDataNascimento();
        setReserva();
    }

    public String getNome() {
        return nome;
    }

    public String setNome() {

        do {
            try {
                this.nome = JOptionPane.showInputDialog(null,"Insira seu nome");
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,
                        "Erro: "+e.getMessage(),"Entrada de dados vazia",
                        JOptionPane.ERROR_MESSAGE);
            }
        }while (this.nome==null||this.nome.isEmpty()||this.nome.isBlank());
        return this.nome;
    }

    public long getRG() {
        return RG;
    }

    public long setRG() {
        final String RG_TEMPLATE = "\\d\\d\\d\\d\\d\\d\\d\\d\\d\\d";

        while (!String.valueOf(RG).matches(RG_TEMPLATE)||listaRG.contains(RG)) {
              try {
                  RG = Long.parseLong(JOptionPane.showInputDialog(null,
                          "Insira seu RG: "));
              } catch (NumberFormatException numberFormatException) {
                  JOptionPane.showMessageDialog(null,
                          "O RG é um conjunto de dez numeros inteiros." +
                          " Insira de forma correta.",
                          "Isso não é um RG válido",JOptionPane.ERROR_MESSAGE);
                  System.err.println(numberFormatException.getMessage());
              } catch (NullPointerException nullPointerException) {
                  JOptionPane.showMessageDialog(null,
                          "Entrada de dados vazia.\nErro: "+nullPointerException.getMessage(),
                          "Dados inválidos",JOptionPane.ERROR_MESSAGE);
              }

              if(listaRG.contains(RG)) {
                  JOptionPane.showMessageDialog(null,
                          "Esse RG já consta em nossa base de dados");
              }
        }
        return this.RG;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public LocalDate setDataNascimento() {

        boolean repetir =  true;
        do {
            try{
                this.dataNascimento = LocalDate.parse(JOptionPane.showInputDialog(null,
                        "Insira sua data de nascimento (dd/mm/aaaa):"),
                        DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                repetir = false;
                if(this.dataNascimento.isBefore(LocalDate.parse("1900-01-01"))){
                    JOptionPane.showMessageDialog(null,
                            "Mermão, tu não tem mais de 120 anos.");
                    repetir = true;
                }else if(this.dataNascimento.isAfter(LocalDate.now().minusYears(18))){
                    JOptionPane.showMessageDialog(null,
                            "Vocẽ deve ter pelo menos 18 anos para poder se hospedar");
                    repetir = true;
                }
            }catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Data inválida.\nErro: "+e.getMessage(),
                        "Data inválida",JOptionPane.ERROR_MESSAGE);
            }

        }while(repetir);
        return dataNascimento;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva() {
        if(reserva==null) {
            this.reserva = new Reserva();
            while(getReserva().quartoDisponivel()) {
                JOptionPane.showMessageDialog(null,
                        "Não temos quartos do tipo "+getReserva().getQuarto()+
                                " disponíveis para o período estipulado.");
                this.reserva = new Reserva();
            }
        }
    }

    public void cancelarReserva() {
        getListaReserva().remove(getReserva());
        this.reserva=null;
        JOptionPane.showMessageDialog(null,
                "Reserva removida com sucesso!");
    }

    @Override
    public String toString() {

        return "\nTitular da reserva: "+this.getNome()+
                "\nRegistro nº: "+this.getRG()+
                "\nNascimento: "+formatar(this.getDataNascimento())+
                "\nDiária: de "+formatar(this.getReserva().getDataInicialDaDiaria())+
                " até "+formatar(this.getReserva().getDataTerminoDaDiaria())+
                "\nQuantidade de Dias: "+this.getReserva().getDiasDaDiaria()+
                "\nAcompanhantes: "+this.getReserva().getAcompanhantes()+
                "\nTipo do Quarto: "+this.getReserva().getQuarto().getNomeDoQuarto()+
                "\nValor total da diária: R$: "+
                new DecimalFormat("0.00").format(this.getReserva().getValorTotal());
    }
}
