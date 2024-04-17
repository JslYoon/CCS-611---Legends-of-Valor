package src.Misc;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.*;

public class AudioPlayer {
    
    private Clip audioClip;
    private AudioInputStream audioStream;

    public AudioPlayer(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File audioFile = new File(path).getAbsoluteFile();
        this.audioStream = AudioSystem.getAudioInputStream(audioFile);
        
        this.audioClip = AudioSystem.getClip();
        this.audioClip.open(audioStream);
        this.audioClip.start();
    }


    public void loop() {
        audioClip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        audioClip.stop();
    }

    public void terminate() {
        audioClip.close();
    }
}
