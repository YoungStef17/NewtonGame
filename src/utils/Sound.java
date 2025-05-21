package src.utils;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {
    
    private Clip clip;
    private final URL[] soundURL = new URL[10];

    public Sound() {

        soundURL[0] = getClass().getResource("/src/resource/sounds/8-bit-arcade.wav");       //game music
        soundURL[1] = getClass().getResource("/src/resource/sounds/appleEffect.wav");        
        soundURL[2] = getClass().getResource("/src/resource/sounds/speedDown.wav");
        soundURL[3] = getClass().getResource("/src/resource/sounds/speedUp.wav");
        soundURL[4] = getClass().getResource("/src/resource/sounds/success_fanfare.wav");
        soundURL[5] = getClass().getResource("/src/resource/sounds/click_sound.wav");
        soundURL[6] = getClass().getResource("/src/resource/sounds/gravityInversion.wav");
        soundURL[7] = getClass().getResource("/src/resource/sounds/gameover.wav");
        
    }
    public void setFile(int ind) {

        try{
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundURL[ind]);
            clip = AudioSystem.getClip();
            clip.open(audioStream);

        }catch(IOException | LineUnavailableException | UnsupportedAudioFileException e){
        }

    }
    public void play() {

        clip.start();
    }
    public void loop() {

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop() {

        clip.stop();
    }

}

