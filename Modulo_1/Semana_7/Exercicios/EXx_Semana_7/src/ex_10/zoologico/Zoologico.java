package ex_10.zoologico;

import ex_10.*;

import java.util.ArrayList;
import java.util.List;

public class Zoologico {
    public static Animal[] jaula = {new Abelha(), new Cavalo(),new Cobra(),new Coruja(), new Leao(), new Lobo(),
                      new Ovelha(), new Porco(), new Rato(), new Sapo()};
    public static List<Animal> jaulas = new ArrayList<>();

    public static void main(String[] args) {
        jaulas.addAll(List.of(jaula));
        for(Animal animal: jaulas) {
            animal.emitirSom();
            animal.correndo();
        }
    }
}
