package ex_09;

public class OrdemDeInicializacao {

    static {
        System.out.println("Dentro do bloco static");
    }
    {
        System.out.println("Dentro do bloco de instância");
    }
     OrdemDeInicializacao() {
        System.out.println("Dentro do Construtor");
    }

    public static void main(String[] args) {
        new OrdemDeInicializacao();
    }
}
