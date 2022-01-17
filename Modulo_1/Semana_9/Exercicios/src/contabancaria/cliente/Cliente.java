package contabancaria.cliente;

import javax.swing.*;

public class Cliente {
    String nome;

    public Cliente() {
        setNome();
    }

    public String setNome() {
        do {
            try {
                this.nome = JOptionPane.showInputDialog(null,
                        "Insira o nome do cliente: ");
            }catch (Exception e) {
                JOptionPane.showMessageDialog(null,
                        "Ocorreu um erro: "+e.getMessage()+"\nTente novamente.");
            }
        }while(this.nome==null||this.nome.isEmpty()||this.nome.isBlank());
        return this.nome;
    }

    public String getNome() {
        return nome;
    }
}
