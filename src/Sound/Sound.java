package Sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import model.MainModel;

import java.net.URL;

public class Sound {

    MainModel model;
    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound(MainModel m){
        model =m;
        soundURL[0] = getClass().getResource("bgm.wav");
        soundURL[1] = getClass().getResource("move.wav");
        // soundURL[2] = getClass().getResource(null);
        // soundURL[3] = getClass().getResource(null);
        // soundURL[4] = getClass().getResource(null);
        

    }

    public void setFile(int i){

        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch(Exception e){
        }

    }

    public void play (){
        clip.start();
    }

    public void stop(){
        clip.stop();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    //start play bgm music
    public void playMusic(int i){

        this.setFile(i);
        this.play();
        this.loop();
    }

    // stop play music
    public void stopMusic(){

        this.stop();
    }
    
    //start play effect music
    public void plsySE(int i){

        this.setFile(i);
        this.play();
    }
    
}
