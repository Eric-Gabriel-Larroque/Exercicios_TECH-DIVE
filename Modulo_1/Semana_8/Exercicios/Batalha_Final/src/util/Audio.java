package util;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Audio {

    public static void tocarAudio(String audioCaminho) {
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(audioCaminho)
                    .getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }catch (Exception e) {
            System.out.printf("Falha ao executar o audio: %s ",e);
        }
    }

}