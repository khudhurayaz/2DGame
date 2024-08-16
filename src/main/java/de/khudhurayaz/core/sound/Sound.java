package de.khudhurayaz.core.sound;

import de.khudhurayaz.core.GameConstant;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sound {

    private Clip clip;
    private FloatControl volumeControl;
    private final URL[] soundURL;
    public static final int BACKGROUND_SOUND = 0;
    public static final int COIN_SOUND = 1;
    public static final int UNLOCK_SOUND = 2;
    public static final int FAN_FARE_SOUND = 3;
    private static final Logger SOUND_LOGGER = Logger.getLogger(Sound.class.getSimpleName());

    public Sound(){
        soundURL = new URL[30];
        setSoundPath();
    }

    private void setSoundPath(){
        soundURL[BACKGROUND_SOUND] = getClass().getResource(GameConstant.SOUND_PATH + "BlueBoyAdventure.wav");
        soundURL[COIN_SOUND] = getClass().getResource(GameConstant.SOUND_PATH + "coin.wav");
        soundURL[UNLOCK_SOUND] = getClass().getResource(GameConstant.SOUND_PATH + "unlock.wav");
        soundURL[FAN_FARE_SOUND] = getClass().getResource(GameConstant.SOUND_PATH + "fanfare.wav");
    }

    public void setFile(int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volumeControl.setValue(-10);
        }catch (Exception e){
            SOUND_LOGGER.log(Level.WARNING, e.getMessage());
        }
    }

    public void play(){
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        clip.stop();
    }

    public float getVolume(){
        return volumeControl.getValue();
    }

    public void setVolume(float volume){
        if (volume > volumeControl.getMaximum() && volume < volumeControl.getMinimum())
            return;
        volumeControl.setValue(volume);
    }

}
