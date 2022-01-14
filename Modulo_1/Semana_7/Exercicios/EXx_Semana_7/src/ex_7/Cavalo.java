package ex_7;

public class Cavalo extends Animal{

    public Cavalo(String nome, int idade) {
        super(nome, idade);
    }
    @Override  public void emitirSom(){
        System.out.println("O cavalo está relinchando");
    }

    @Override public void locomocao() {
        System.out.println("O cavalo corre");
    }
}
