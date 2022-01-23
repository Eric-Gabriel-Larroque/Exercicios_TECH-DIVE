package hoteltechdive;

import hoteltechdive.hospede.Hospede;

import static hoteltechdive.util.Validacao.*;


import javax.swing.*;

public class Hotel {

    public void iniciarAtendimento() {

        JOptionPane.showMessageDialog(null, "Bem-vindo(a) ao hotel TECH-DIVE!");
        Hospede hospede = new Hospede();
        opcoes(hospede);

    }

    public void opcoes(Hospede hospede){
        String mensagemPadrao = "Bem-vindo(a) senhor(a) "+hospede.getNome()+". O" +
                "que deseja fazer?";
        int opcaoEscolhida = escolherOpcao(mensagemPadrao,
                "Ver detalhes da reserva","Simular Diária","Cancelar Reserva","sair");

        switch (opcaoEscolhida){

            case 0:
                JOptionPane.showMessageDialog(null,hospede);
                break;
            case 1:
                hospede.getReserva().simularDiaria();
                break;
            case 2:
                hospede.cancelarReserva();
                sair();
                break;
            case 3:
                sair();
                break;
        }
        opcoes(hospede);
    }

    public void sair() {
        JOptionPane.showMessageDialog(null,"Volte sempre!");
        System.exit(0);
    }

    public static void main(String[] args){
        new Hotel().iniciarAtendimento();
    }
}
