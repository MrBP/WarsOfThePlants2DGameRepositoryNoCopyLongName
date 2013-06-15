package net.mrblockplacer.WarsOfThePlants.network;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.mrblockplacer.WarsOfThePlants.Game;

public class MainNetwork implements Runnable {
	private Socket socket = null;
	private Thread thread = null;
	private DataInputStream console = null;
	private DataOutputStream streamOut = null;
	private ChatClientThread client = null;
	public static JFrame jf;
	public static JTextArea jta = new JTextArea();
	public static JTextField jtf = new JTextField();

	public MainNetwork() {
		jta.setEditable(false);
		jf = new JFrame();
		// jf.setUndecorated(true);

		jf.setSize(Game.width * 2, Game.height * 2);
		// jf.setLocationRelativeTo(Game.frame);
		// jf.setLocationRelativeTo(null);
		jta.setSize(jf.getWidth(), jf.getHeight() - 50);
		jf.add(jta);
		jf.add(jtf, BorderLayout.SOUTH);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setVisible(true);
		Game.frame.requestFocus();
		new MainNetwork("direct.mrblockplacer.net", 25565);
	}

	public MainNetwork(String serverName, int serverPort) {
		jta.append("Establishing connection. Please wait...\n");
		try {
			socket = new Socket(serverName, serverPort);
			jta.append("Connected: " + socket + "\n");
			start();
		} catch (UnknownHostException uhe) {
			jta.append("Host unknown: " + uhe.getMessage() + "\n");
		} catch (IOException ioe) {
			jta.append("Unexpected exception: " + ioe.getMessage() + "\n");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		jtf.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					streamOut.writeUTF(jtf.getText());
					jtf.setText("");
					streamOut.flush();
				} catch (IOException ioe) {
					jta.append("Sending error: " + ioe.getMessage() + "\n");
					try {
						stop();
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
			}

		});

	}

	public void handle(String msg) throws InterruptedException {
		if (msg.equals(".bye")) {
			// System.out.println("Good bye. Press RETURN to exit ...");
			jta.append("Goodbye...\n");
			stop();
		} else
			jta.append(msg + "\n");

	}

	public void start() throws IOException, InterruptedException {
		console = new DataInputStream(System.in);
		streamOut = new DataOutputStream(socket.getOutputStream());
		if (thread == null) {
			client = new ChatClientThread(this, socket);
			thread = new Thread(this);
			thread.start();
		}
	}

	public void stop() throws InterruptedException {
		if (thread != null) {
			thread.join();
			thread = null;
		}
		try {
			if (console != null)
				console.close();
			if (streamOut != null)
				streamOut.close();
			if (socket != null)
				socket.close();
		} catch (IOException ioe) {
			jta.append("Error closing...\n");
		}
		client.close();
		client.join();
	}

}
