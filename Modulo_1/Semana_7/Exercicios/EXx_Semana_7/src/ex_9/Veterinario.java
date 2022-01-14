package ex_9;

import ex_7.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Veterinario {
    private static List<Object> listaAnimais = new ArrayList<>();

    public static void examinar(Animal[] animais) {
        for(Animal animal: animais) {
            animal.emitirSom();
        }
    }

    public static void main(String[] args) {
        Animal[] animais = {new Cavalo("Mimoso",10), new Cachorro("Rex",5),
                new Preguica("Flash",15)};
        Veterinario.examinar(animais);
    }
}
