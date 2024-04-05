import View.ViewPrincipal;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Main {
    public static void main(String[] args) {

        /// muzica fundal
        try {
            String path = "resources/birturimuzica.wav";

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path).getAbsoluteFile());

            Clip clip = AudioSystem.getClip();

            clip.open(audioInputStream);

            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

        new ViewPrincipal();

    }
}
