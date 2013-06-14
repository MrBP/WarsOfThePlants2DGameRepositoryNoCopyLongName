package net.mrblockplacer.WarsOfThePlants.sound;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import net.mrblockplacer.WarsOfThePlants.Game;

public class Sound {
	public static final String SOUND_BOUNCE = "zapit";
	public static final String SOUND_CHANGE_WORLD_1 = "changeWorld1";
	public static final String SOUND_WATER_1 = "water1";
	public static final String LOADUP = "loading";
	public static long lastTime;
	public static long millisIn48Hours = 1000 * 60 * 7;

	public static void playSound(String sound) {
		if (Boolean.parseBoolean(Game.mc.readFromKey("game-sounds"))) {
			try {
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Sound.class.getResource("/sounds/" + sound + ".wav"));
				Clip clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
				FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				volume.setValue(-6);
			} catch (Exception ex) {
				System.out.println("Error with playing sound.");
				ex.printStackTrace();
			}
		}
	}

	public static void playMusic(String music) {
		if (Boolean.parseBoolean(Game.mc.readFromKey("game-sounds"))) {
			Date timestamp = new Date(lastTime);
			Date hours48ago = new Date(new Date().getTime() - millisIn48Hours);
			if (timestamp.before(hours48ago)) {
				try {
					AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Sound.class.getResource("/music/MARS.wav"));// " //+ music + ".wav"));
					Clip clip = AudioSystem.getClip();
					clip.open(audioInputStream);
					clip.start();
					FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					volume.setValue(-10);
					lastTime = new Date().getTime();
				} catch (Exception ex) {
					System.out.println("Error with playing sound.");
					ex.printStackTrace();
				}
			}
		}
	}

}
