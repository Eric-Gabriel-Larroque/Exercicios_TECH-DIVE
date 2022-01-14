package ex_06;

public class ClasseAbstrata {

    abstract class EmitirFrase{
        abstract void Mensagem();
    }

    class Frase {
        void Mensagem() {
            System.out.println("Esse método foi herdado sem extends");
        }
    }

    public static void main(String[] args) {
        Frase frase = new ClasseAbstrata().new Frase();
        frase.Mensagem();
    }
}
