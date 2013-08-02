package net.mrblockplacer.WarsOfThePlants.sound;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import net.mrblockplacer.WarsOfThePlants.MainClass;

public class Audio {
	public static final String SOUND_BOUNCE = "zapit";
	public static final String SOUND_CHANGE_WORLD_1 = "changeWorld1";
	public static final String SOUND_WATER_1 = "water1";
	public static final String SOUND_LOADUP = "loading";
	public static final String MUSIC_BACKGROUND1 = "HUMORSK1";
	public static final String MUSIC_BOSS1 = "MARS";
	public static AudioInputStream audioInputStream;
	public static Clip clip;
	public static long lastTime;
	public static long millisIn48Hours = 1000 * 60 * 5;

	public static void playSound(String sound) {
		if (Boolean.parseBoolean(MainClass.mc.readFromKey("game-sounds"))) {
			try {
				AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Audio.class.getResource("/sounds/" + sound + ".wav"));
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
		if (Boolean.parseBoolean(MainClass.mc.readFromKey("game-sounds")) && MainClass.doneDownloading) {
			Date timestamp = new Date(lastTime);
			Date hours48ago = new Date(new Date().getTime() - millisIn48Hours);
			if (timestamp.before(hours48ago)) {
				try {
					audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(MainClass.APPDATA + File.separator + "WarsOfThePlants" + File.separator + "music" + File.separator + music + ".wav")));// " //+ music + ".wav"));
					// audioInputStream = AudioSystem.getAudioInputStream(new
					// FileInputStream(MainClass.APPDATA + File.separator +
					// "WarsOfThePlants" + File.separator + "music" +
					// File.separator + music + ".wav"));//
					// " //+ music + ".wav"));
					clip = AudioSystem.getClip();
					clip.open(audioInputStream);
					clip.start();
					FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					volume.setValue(-8);
					lastTime = new Date().getTime();
				} catch (Exception ex) {
					System.out.println("Error with playing sound. " + ex.getMessage());
					ex.printStackTrace();

				}
			}
		}
	}

	public static void playBoss(String music) {
		if (Boolean.parseBoolean(MainClass.mc.readFromKey("game-sounds")) && MainClass.doneDownloading) {
			// Date timestamp = new Date(lastTime);
			// Date hours48ago = new Date(new Date().getTime() -
			// millisIn48Hours);
			// if (timestamp.before(hours48ago)) {
			try {
				audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(new FileInputStream(MainClass.APPDATA + File.separator + "WarsOfThePlants" + File.separator + "music" + File.separator + music + ".wav")));// " //+ music + ".wav"));
				clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.loop(Clip.LOOP_CONTINUOUSLY);
				clip.start();
				FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				volume.setValue(-8);
				lastTime = new Date().getTime();
			} catch (Exception ex) {
				System.out.println("Error with playing sound.");
				ex.printStackTrace();
			}
			// }
		}
	}

	public static void stopMusic() {
		if (clip != null) {
			clip.stop();
			clip.close();
		}
	}

	public static void clear() {
		// clip.flush();
		// clip.close();
		// clip = null;
		// try {
		// audioInputStream.close();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// audioInputStream = null;
	}

}
