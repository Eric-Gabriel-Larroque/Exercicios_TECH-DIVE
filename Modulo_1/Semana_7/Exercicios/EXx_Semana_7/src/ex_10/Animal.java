package ex_10;

import java.util.Scanner;

public abstract class Animal {
    private String nome;
    private boolean corre;
    private String emitirSom = "";

    public Animal() {
    }

    public void correndo() {
        if(corre){
            System.out.printf("\nO(A) %s está correndo.",nome);
        }
    }

    String setNome(String nome) {
        this.nome = nome;
        return nome;
    }

    boolean setCorre(boolean corre) {
        this.corre = corre;
        return corre;
    }
    String setEmitirSom(String som) {
        this.emitirSom = som;
        return som;
    }

    public void emitirSom() {
        System.out.printf("\nO(a) %s está %s.",nome,emitirSom);
    }
}
