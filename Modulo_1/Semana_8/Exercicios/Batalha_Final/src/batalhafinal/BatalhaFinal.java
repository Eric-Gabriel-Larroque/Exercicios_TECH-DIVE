package batalhafinal;

import personagens.Guerreiro;
import util.Audio;

public class BatalhaFinal {

    public static void main(String[] args) {
        Audio.tocarAudio("audio/conan_soundtrack.wav");
        Guerreiro guerreiro = new Guerreiro();
        System.out.println(guerreiro.ge);

    }
}
