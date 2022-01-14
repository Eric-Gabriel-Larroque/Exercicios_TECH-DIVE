package ex_05_pacoteum;

import ex_05_pacotedois.PacoteDois;

public class PacoteUm {

    public static void main(String[] args) {
        PacoteDois pctDois = new PacoteDois();
        pctDois.metodoPacoteDois("Acessando método do pacote dois");
        PacoteDois.metodoEstaticoPacoteDois("Acessando método do pacote dois");
    }
}
