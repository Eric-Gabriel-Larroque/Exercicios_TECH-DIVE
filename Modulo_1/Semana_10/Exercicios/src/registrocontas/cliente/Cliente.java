package registrocontas.cliente;

import static util.Validacao.*;

public class Cliente {

    String nome;

    public Cliente() {
        setNome();
    }

    private String setNome() {
        this.nome = validaString("Insira seu nome");
        return this.nome;
    }

    public String getNome() {
        return nome;
    }

}
