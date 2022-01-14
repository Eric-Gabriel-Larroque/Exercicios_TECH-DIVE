package ex_7;

public class Preguica extends Animal{


    public Preguica(String nome, int idade) {
        super(nome, idade);
    }

    @Override  public void emitirSom(){
        System.out.println("A preguica está emitindo o som que preguiça faz (não sei o nome kkkkkkk)");
    }

    @Override public void locomocao() {
        System.out.println("A preguiça escala as árvores");
    }
}
