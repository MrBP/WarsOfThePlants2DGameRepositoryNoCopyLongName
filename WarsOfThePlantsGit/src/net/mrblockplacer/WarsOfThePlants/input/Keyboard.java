package net.mrblockplacer.WarsOfThePlants.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	public static boolean isTabDown;
	// private boolean[] keys = new boolean[120];
	private static boolean[] keys = new boolean[525];
	public boolean up, down, left, right, exit, crouch, zoomIn;
	public boolean speedUp, speedDown;
	public boolean boss;

	public void update() {
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		right = keys[KeyEvent.VK_D];
		left = keys[KeyEvent.VK_A];
		exit = keys[KeyEvent.VK_ESCAPE];
		speedUp = keys[KeyEvent.VK_UP];// || keys[KeyEvent.VK_W];
		speedDown = keys[KeyEvent.VK_DOWN];
		crouch = keys[KeyEvent.VK_SHIFT];
		boss = keys[KeyEvent.VK_ENTER];
//		if (keys[KeyEvent.VK_T]) {
//			isTabDown = !isTabDown;
//			System.out.println(isTabDown);
//
//		}// || keys[KeyEvent.VK_S];

		// right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		// left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		// exit = keys[KeyEvent.VK_ESCAPE];
	}

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent e) {
	}

	public static void reset() {
		for (int i = 0; i < keys.length; i++) {
			keys[i] = false;
		}
	}

}
