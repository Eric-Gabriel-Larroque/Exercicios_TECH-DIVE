package contabancaria.contas;

import contabancaria.cliente.Cliente;

import javax.swing.*;

public abstract class Conta {
    Cliente titular;

    public Conta() {
        this.titular = new Cliente();
    }


    public Cliente getTitular() {
        return titular;
    }

}
