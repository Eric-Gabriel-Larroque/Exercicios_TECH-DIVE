package hoteltechdive;

import javax.swing.*;
import java.util.InputMismatchException;

public class Hospede {

    private String nome;
    private long RG;

    public Hospede(){
        this.RG = setRG();
        this.nome = setNome();
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
        String RG = "";
        do {
           try {
               RG = JOptionPane.showInputDialog(null,
                       "Insira seu RG: ");
               this.RG = Long.parseLong(RG);
           }catch (InputMismatchException inputMismatchException) {
               JOptionPane.showMessageDialog(null,
                       "Somente numeros são permitidos.\nerro: "+inputMismatchException.getMessage(),
                       "Tipo de dado inválido",JOptionPane.ERROR_MESSAGE);
           }catch (NullPointerException nullPointerException){
               JOptionPane.showMessageDialog(null,
                       "Entrada de dados vaziaz\nerro: "+nullPointerException.getMessage(),
                       "Entrada vazia",JOptionPane.ERROR_MESSAGE);
           }
        } while (!RG.matches(RG_TEMPLATE));
        return this.RG;
    }
}
