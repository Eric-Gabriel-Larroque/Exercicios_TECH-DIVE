package util;

import javax.swing.*;

public class Validacao {


    public static String validaString(String mensagem) {
        String entrada = null;
        while(entrada==null||entrada.isEmpty()||entrada.isBlank()) {
            entrada = JOptionPane.showInputDialog(null,mensagem);
        }
        return entrada;
    }

    public static double validaNumero(String mensagem) {
        double entrada = 0d;
        boolean repetir = true;
        while(repetir) {
            try {
                entrada = Double.parseDouble(
                        JOptionPane.showInputDialog(null,mensagem)
                );
                repetir = false;
            }catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Somente numeros são permitidos.",
                        "Dado inválido",JOptionPane.ERROR_MESSAGE);
            }
        }
        return entrada;
    }
}
