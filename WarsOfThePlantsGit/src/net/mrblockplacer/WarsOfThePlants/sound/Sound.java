package net.mrblockplacer.WarsOfThePlants.sound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	public static String SOUND_BOUNCE = "bounce";
	public static String SOUND_CHANGE_WORLD_1 = "changeWorld1";
	public static String SOUND_WATER_1 = "water1";

	public static void playSound(String sound) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Sound.class.getResource("/sounds/" + sound + ".wav"));
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception ex) {
			System.out.println("Error with playing sound.");
			ex.printStackTrace();
		}
	}

}
