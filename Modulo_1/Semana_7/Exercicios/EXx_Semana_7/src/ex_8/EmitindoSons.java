package ex_8;

import ex_7.*;

import java.util.ArrayList;
import java.util.List;

public class EmitindoSons {

    public static void main(String[] args) {
        List<Animal> listaAnimais = new ArrayList<>();

        listaAnimais.add(new Cavalo("Pé de pano",25));
        listaAnimais.add(new Cachorro("Joca",1));
        listaAnimais.add(new Preguica("Sloth",3));
        for(Animal animal: listaAnimais) {
                animal.emitirSom();
        }
    }
}
