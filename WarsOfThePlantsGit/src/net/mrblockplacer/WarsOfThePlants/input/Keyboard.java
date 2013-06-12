package net.mrblockplacer.WarsOfThePlants.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	// private boolean[] keys = new boolean[120];
	private boolean[] keys = new boolean[525];
	public boolean up, down, left, right, exit, zoomOut, zoomIn;

	public void update() {
		zoomOut = keys[KeyEvent.VK_DOWN];
		zoomIn = keys[KeyEvent.VK_UP];
		up = keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_S];
		right = keys[KeyEvent.VK_D];
		left = keys[KeyEvent.VK_A];
		exit = keys[KeyEvent.VK_ESCAPE];
		// up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		// down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
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

}
