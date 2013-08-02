package net.mrblockplacer.WarsOfThePlants;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.JFrame;

import net.mrblockplacer.WarsOfThePlants.conf.MainConf;
import net.mrblockplacer.WarsOfThePlants.entity.mob.Player;
import net.mrblockplacer.WarsOfThePlants.graphics.Screen;
import net.mrblockplacer.WarsOfThePlants.graphics.Sprite;
import net.mrblockplacer.WarsOfThePlants.input.Keyboard;
import net.mrblockplacer.WarsOfThePlants.input.Mouse;
import net.mrblockplacer.WarsOfThePlants.level.Level;
import net.mrblockplacer.WarsOfThePlants.level.TileCoordinate;
import net.mrblockplacer.WarsOfThePlants.network.Handler;
import net.mrblockplacer.WarsOfThePlants.sound.Sound;

//import java.awt.Image;

public class MainClass extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	public static String APPDATA = System.getenv("APPDATA");

	/**
	 * The width variable for the gamess
	 */
	public static int width = 300;
	public static int height = (width / 10) * 9;
	// public static int height = (width / 16) * 9;
	public static int scale = 3;
	public static String title = "Rain";
	public static Long curTime = 0L;
	private Thread thread;
	public static JFrame frame;
	private Keyboard key;
	public static Level level;
	public static Player player;
	// public static Jabba jabba;
	private boolean running = false;
	int FIRST_LINE = height;
	int SECOND_LINE = height + 25;
	int THIRD_LINE = SECOND_LINE + 25;
	public static boolean canPlayerMove = false;
	public static boolean showInstructions;
	public static Screen screen;
	public static MainConf mc = new MainConf("conf.conf");
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	public static String username;
	public static String uniqueID;
	public static String localhost;
	public static boolean playingGame = false;
	public static boolean bossTime = false;
	public static MainClass instance;
	public static boolean doneDownloading = false;
	// public static Jabba jabbalist = new Jabba()
	// public static playerlist = new Player[50];
	public static ArrayList<Player> playerlist = new ArrayList<Player>();
	// public static Player[] playerlist = new Player[100];
	// public static MainNetwork network;
	public static boolean networkUp = false;

	public MainClass() {
		instance = this;
		if (mc.readFromKey("isFirstRun") == null) {
			System.out.println("HIHIIH");
			writeFirstKeys();
		}
		if (mc.readFromKey("showInstructions").trim().equalsIgnoreCase("yes")) {
			showInstructions = true;
		}
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);
		screen = new Screen(width, height);
		frame = new JFrame();
		key = new Keyboard();
		level = Level.spawn; // new SpawnLevel("/levels/spawn.png");
		// TileCoordinate playerSpawm = new TileCoordinate(3, 65);
		// TileCoordinate playerSpawm = new TileCoordinate(22, 65);
		// player = new Player(playerSpawm.getX(), playerSpawm.getX(), key);
		// int lastPosX = Integer.valueOf(mc.readFromKey("lastPosX"));
		// int lastPosY = Integer.valueOf(mc.readFromKey("lastPosY"));
		// player = new Player(1623, 1434, key);
		// player = new Player(1490, 1488, key);
		// player = new Player(lastPosX, lastPosY, key);
		player = new Player(30, 30, key, 1);
		// jabba = new Jabba(25, 80);
		// jabba.init(level);
		// for(Player p : playerlist){

		// }
		player.init(level);
		addKeyListener(key);
		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		// MainNetwork network2 = new MainNetwork();
		// network = network2;

	}

	public synchronized void start() {
		running = true;
		frame.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {

			}

			@Override
			public void windowIconified(WindowEvent e) {

			}

			@Override
			public void windowDeiconified(WindowEvent e) {

			}

			@Override
			public void windowDeactivated(WindowEvent e) {

			}

			@Override
			public void windowClosing(WindowEvent e) {
				stop();
			}

			@Override
			public void windowClosed(WindowEvent e) {

			}

			@Override
			public void windowActivated(WindowEvent e) {

			}
		});
		frame.addWindowFocusListener(new WindowFocusListener() {

			@Override
			public void windowLostFocus(WindowEvent arg0) {
				Keyboard.reset();
			}

			@Override
			public void windowGainedFocus(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}
		});
		thread = new Thread(this, "Display");
		thread.start();

		// Thread t = new Thread(new Runnable() {
		//
		// @Override
		// public void run() {
		// TODO Auto-generated method stub
		// }
		//
		// });
		// t.start();
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					downloadMusic("music.zip");
					// downloadMusic("music/MARS.wav");
					Sound.clear();
					doneDownloading = true;
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		});
		t2.start();
	}

	public synchronized void stop() {
		running = false;
		System.out.println("STOPPING");
		mc.writeToKey("lastPosX", String.valueOf(player.x));
		mc.writeToKey("lastPosY", String.valueOf(player.y));
		System.exit(0);
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("STOPPED");

	}

	public void run() {

		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		int i = 0;
		requestFocus();
		while (running) {
			if (playingGame && !bossTime) {
				i = 0;
				Sound.playMusic(Sound.MUSIC_BACKGROUND1);
			}
			if (playingGame && bossTime && i == 0) {
				i = 1;
				Sound.stopMusic();
				Sound.playBoss(Sound.MUSIC_BOSS1);
			}
			curTime++;
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;

			// one second || 1000 ns = 1 sec
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println(updates + " ups, " + frames + " fps");
				frame.setTitle(title + "  | " + updates + " ups, " + frames + " fps");
				frames = 0;
				updates = 0;
			}
		}
		stop();
	}

	private void writeFirstKeys() {
		mc.writeToKey("showInstructions", "yes");
		mc.writeToKey("isFirstRun", "no");
		mc.writeToKey("lastPosX", "125");
		mc.writeToKey("lastPosY", "125");
		mc.writeToKey("game-sounds", "true");
	}

	// int x = 0, y = 0;

	public void update() {
		if (playingGame) {
			// JOptionPane.showMessageDialog(null, "HIII");
			playerlist.addAll(Handler.al);
			Handler.clearArray();
			key.update();
			player.update();
			// jabba.update();

			// if (key.up) y--;
			// if (key.down) y++;
			// if (key.right) x++;
			// if (key.left) x--;
			// Iterator<Player> itr = playerlist.iterator();
			//
			// while (itr.hasNext()) {
			// Player p = itr.next();
			// // ferrybig end
			// if (p != null) {
			// p.update();
			// }
			//
			// }
			// synchronized (playerlist) {
			for (Player p : playerlist) {
				if (p != null) {
					p.update();
				}
			}
			// }
			level.update();

		}

	}

	Image heart = Toolkit.getDefaultToolkit().getImage(MainClass.class.getResource("/textures/heart.png"));
	Image loadingBackground = Toolkit.getDefaultToolkit().getImage(MainClass.class.getResource("/textures/loading.png"));
	int timeee = 0;

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		if (playingGame) {
			screen.clear();

			int xScroll = player.x - screen.width / 2;
			int yScroll = player.y - screen.height / 2;
			level.render(xScroll, yScroll, screen);
			// jabba.render(screen);
			player.render(screen);
			for (Player p : playerlist) {
				if (p != null) {
					p.render(screen);
				}
			}
			Sprite sprite = new Sprite(40, height, 0x808080);
			screen.renderSprite(width - 40, 0, sprite, false);
			for (int i = 0; i < pixels.length; i++) {
				pixels[i] = screen.pixels[i];
			}

			// g.setColor(Color.BLACK);
			// g.fillRect(0, 0, getWidth(), getHeight());
			g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
			g.setColor(Color.WHITE);
			g.setFont(new Font("Verdana", 0, 25));
			// System.out.println(player.x + "   " + player.y);
			// g.drawString("X: " + player.x + " Y: " + player.y, Mouse.getX(),
			// Mouse.getY());
			g.drawString("X: " + Mouse.getX() + " Y: " + Mouse.getY(), Mouse.getX(), Mouse.getY());
			try {
				renderText(g);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			renderHearts(g);
			// g.fillRect(x, y, xScroll, yScroll)
		} else {
			screen.clear();
			g.drawImage(loadingBackground, 0, 0, getWidth(), getHeight(), null);
			for (int i = 0; i < 500000; i++) {
				// System.out.println(i);
			}
			timeee++;
			if (timeee > 5000)
				playingGame = true;
		}
		g.dispose();
		bs.show();
	}

	private void renderHearts(Graphics g) {
		g.setColor(Color.black);
		// g.fillRect(width * scale - 240, height * scale - 50, 240, 60);
		g.fillRect(0, height * scale - 50, width * scale, 60);
		// g.drawRect(width*scale-50, height*scale-50, 60, 60);
		if (player.health >= 1)
			g.drawImage(heart, width * scale - 240, height * scale - 40, 40, 40, null);
		if (player.health >= 2)
			g.drawImage(heart, width * scale - 240 + 40, height * scale - 40, 40, 40, null);
		if (player.health >= 3)
			g.drawImage(heart, width * scale - 240 + 80, height * scale - 40, 40, 40, null);
		if (player.health >= 4)
			g.drawImage(heart, width * scale - 240 + 120, height * scale - 40, 40, 40, null);
		if (player.health >= 5)
			g.drawImage(heart, width * scale - 240 + 160, height * scale - 40, 40, 40, null);
		if (player.health >= 6)
			g.drawImage(heart, width * scale - 240 + 200, height * scale - 40, 40, 40, null);

	}

	public int getCenter(String s) {
		int i = (getWidth() / 2) - (s.length() * 6);
		// System.out.println(i);
		return i;
	}

	private void renderText(Graphics g) throws InterruptedException {
		String s;
		int j;
		g.setColor(Color.YELLOW);
		if (showInstructions) {
			if (curTime < 1000) {
				s = "Welcome to the game!";
				j = getCenter(s);
				g.drawString(s, j, FIRST_LINE);
			}
			if (curTime > 1000 && curTime < 2000) {
				s = "This game is based off of Realm of the Mad God, ";
				j = getCenter(s);
				g.drawString(s, j, FIRST_LINE);
			}
			if (curTime > 1000 && curTime < 2000) {
				s = "and is completly customizable!";
				j = getCenter(s);
				g.drawString(s, j, SECOND_LINE);
			}
			if (curTime > 2000 && curTime < 3000) {
				s = "You use the arrow keys to move, ";
				j = getCenter(s);
				g.drawString(s, j, FIRST_LINE);
			}
			if (curTime > 2000 && curTime < 3000) {
				s = "and the left mouse button to shoot!";
				j = getCenter(s);
				g.drawString(s, j, SECOND_LINE);
			}
			if (curTime > 3000) {
				canPlayerMove = true;
			}
			mc.writeToKey("showInstructions", "no");
		} else {
			canPlayerMove = true;
		}
		if (player.x >= 187 && player.x <= 291 && player.y >= 164 && player.y <= 290) {
			// if (Dialouge.fountain) {
			// s = "It's a fountain :D";
			// j = getCenter(s);
			// g.drawString(s, j, FIRST_LINE);
			// if (Dialouge.fountainCounter > 1000) {
			// Dialouge.fountain = false;
			// }
			// } else {
			// if (Dialouge.fountainCounter > 2000 && Dialouge.fountainCounter <
			// 2500) {
			// level = Level.spawn2;
			// Sound.playSound(Sound.SOUND_CHANGE_WORLD_1);
			// Thread.sleep(1000);
			// TileCoordinate newPos = new TileCoordinate(3, 3);
			// player.init(level);
			// player.setXY(newPos);
			// Dialouge.fountainCounter = 3000;
			// }
			// }
			// Dialouge.fountainCounter++;
		}
		if (player.y >= 1450 && player.y <= 1456 && player.x >= 1624 && player.x <= 1625) {
			level = Level.spawn2;
			Sound.playSound(Sound.SOUND_CHANGE_WORLD_1);
			Thread.sleep(1000);
			TileCoordinate newPos = new TileCoordinate(3, 3);
			player.init(level);
			player.setXY(newPos);

		}
	}

	public static String getIp() throws Exception {
		URL whatismyip = new URL("http://checkip.amazonaws.com");
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(whatismyip.openStream()));
			String ip = in.readLine();
			return ip;
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		// username = args[0];
		// uniqueID = args[1];

		try {
			localhost = getIp();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(localhost);
		AccessController.doPrivileged(new PrivilegedAction<Object>() {
			public Object run() {
				// privileged code goes here, for example:
				MainClass game = new MainClass();
				game = new MainClass();
				MainClass.frame.setResizable(false);
				MainClass.frame.setTitle(title);
				MainClass.frame.add(game);
				MainClass.frame.pack();
				MainClass.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				MainClass.frame.setLocationRelativeTo(null);
				MainClass.frame.setVisible(true);
				// Sound.playSound(Sound.LOADUP);
				Sound.playSound(Sound.SOUND_LOADUP);

				game.start();
				return null; // nothing to return
			}
		});

	}

	public void downloadMusic(String file) throws MalformedURLException, IOException {
		// dialog.progressBar.setEnabled(true);
		if (!new File(APPDATA + File.separator + "WarsOfThePlants" + File.separator + "music").isDirectory()) {
			new File(APPDATA + File.separator + "WarsOfThePlants" + File.separator + "music").mkdirs();
		}
		if (new File(APPDATA + File.separator + "WarsOfThePlants" + File.separator + "intemp.temp").exists()) {
			return;
		}
		BufferedInputStream in = new BufferedInputStream(new URL("http://ftp.mrblockplacer.net/" + file).openStream());
		FileOutputStream fos = new FileOutputStream(new File(APPDATA + File.separator + "WarsOfThePlants" + File.separator + file.replace("/", File.separator)));
		BufferedOutputStream bout = new BufferedOutputStream(fos);
		byte data[] = new byte[1024];
		int read;
		while ((read = in.read(data, 0, 1024)) >= 0) {
			System.out.println(read);
			bout.write(data, 0, read);
		}
		bout.close();
		in.close();
		extractMusic();

	}

	public boolean extractMusic() {

		FileInputStream input = null;
		ZipInputStream zipIn = null;
		try {
			input = new FileInputStream(APPDATA + File.separator + "WarsOfThePlants" + File.separator + "music.zip");
			zipIn = new ZipInputStream(input);
			ZipEntry currentEntry = zipIn.getNextEntry();
			while (currentEntry != null)
				if (currentEntry.getName().contains("META-INF")) {
					currentEntry = zipIn.getNextEntry();
				} else {
					System.out.println("Extracting " + currentEntry + "...");
					FileOutputStream outStream;

					outStream = new FileOutputStream(new File(APPDATA + File.separator + "WarsOfThePlants" + File.separator + "music", currentEntry.getName()));

					byte[] buffer = new byte[1024];
					int readLen;
					while ((readLen = zipIn.read(buffer, 0, buffer.length)) > 0) {
						outStream.write(buffer, 0, readLen);
					}
					outStream.close();
					currentEntry = zipIn.getNextEntry();
				}
		} catch (IOException e) {
			System.err.println(e.getMessage());
			return false;
		} finally {
			try {
				zipIn.close();
				input.close();
			} catch (IOException e) {
				System.err.println(e.getMessage());
			}
		}
		// nativesJar.delete();
		try {
			new File(APPDATA + File.separator + "WarsOfThePlants" + File.separator + "intemp.temp").createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	// public static synchronized void addPlayer(int x, int y, int id) {
	// // playerlist.new Player(x, y, id);
	// for (int i = 0; i < playerlist.length; i++) {
	// if (playerlist[i] == null) {
	// playerlist[i] = new Player(x, y, id);
	// }
	// }
	// }
}
