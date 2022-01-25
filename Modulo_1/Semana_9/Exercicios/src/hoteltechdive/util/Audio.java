package hoteltechdive.util;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Audio {

  private static boolean isRunning = false;
  private static List<Clip> listaDeReproducao = new ArrayList<>();

    public static void tocarAudio(String audioCaminho, boolean loop) {

        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(new File(audioCaminho)
                    .getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            if(isRunning&&loop) {
                listaDeReproducao.get(0).stop();
                listaDeReproducao.get(0).close();
                listaDeReproducao.remove(0);
                isRunning = false;
                listaDeReproducao.add(clip);
            } else {
                if(loop) {
                    listaDeReproducao.add(clip);
                }
            }
            clip.open(audio);
            clip.start();
            if(loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                isRunning = true;
            }
        }catch (Exception e) {
            System.err.printf("Falha ao executar o audio. %nErro: %s ",e.getMessage());
        }
    }

}