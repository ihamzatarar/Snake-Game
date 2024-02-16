import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Sounds.java 
 *
 * @version     1.0.0
 * @university  Forman Christian College
 * @course      CSCS 290 (Java)
 * @project     Snake Game
 * @category    OOP Based Game Using Java Swing
 * @author      Hamza Tarar
 */

public class Sounds {

    protected Clip eatSound;
    protected Clip music;


    public Sounds(){
        try {
            String fileName1 = "assets/sounds/eat.wav";
            
            AudioInputStream sound1 = AudioSystem.getAudioInputStream(this.getClass().getResource(fileName1));
            eatSound = AudioSystem.getClip();
            eatSound.open(sound1);

            String fileName2 = "assets/sounds/Music.wav";
            AudioInputStream sound2 = AudioSystem.getAudioInputStream(this.getClass().getResource(fileName2));
            music = AudioSystem.getClip();
            music.open(sound2);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //starts the Eat Sound
    public void playEatSound() {
        eatSound.setFramePosition(0); // Reset to the beginning of the sound
        eatSound.loop(0);
    }

    //stops the music
    public void stopEatSound() {
        eatSound.stop();
    }

    //starts the music
    public void playMusic() {
        music.loop(Clip.LOOP_CONTINUOUSLY);
    }

    //stops the music
    public void stopMusic() {
        music.stop();
    }
    
}
