package ex_07;

public class ClasseFinal {

    private ClasseFinal() {
    }
    public  void ClasseFinal(String frase){
        System.out.println(frase);
    }


    public static void main(String[] args) {

        ClasseFinal cf = new ClasseFinal();
        cf.ClasseFinal("Herdando uma classe final sem a palavra-chave final");
    }
}