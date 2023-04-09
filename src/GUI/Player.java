package GUI;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Player {
	private Clip clip;

	public void playMusic(String musicLoc) {
		try {
			File musicPath = new File(musicLoc);
			if (musicPath.exists()) {
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
				clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.start();
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			} else {
				System.out.println("Couldn't find Music file");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void stop() {
		try {
			this.clip.stop();
			this.clip.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}