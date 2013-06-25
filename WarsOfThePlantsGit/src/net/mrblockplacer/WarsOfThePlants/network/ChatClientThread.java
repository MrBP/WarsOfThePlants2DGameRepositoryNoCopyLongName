//package net.mrblockplacer.WarsOfThePlants.network;
//
//import java.io.DataInputStream;
//import java.io.IOException;
//import java.net.Socket;
//
//public class ChatClientThread extends Thread {
//	private Socket socket = null;
//	private MainNetwork client = null;
//	private DataInputStream streamIn = null;
//
//	public ChatClientThread(MainNetwork _client, Socket _socket) throws InterruptedException {
////		client = _client;
////		socket = _socket;
////		open();
////		start();
//	}
//
////	public void open() throws InterruptedException {
////		try {
////			streamIn = new DataInputStream(socket.getInputStream());
////		} catch (IOException ioe) {
////			System.out.println("Error getting input stream: " + ioe);
////			client.stop();
////		}
////	}
////
////	public void close() {
////		try {
////			if (streamIn != null)
////				streamIn.close();
////		} catch (IOException ioe) {
////			System.out.println("Error closing input stream: " + ioe);
////		}
////	}
////
////	public void run() {
////		while (true) {
////			try {
////				// client.handle(streamIn.readUTF());
////				// byte[] bs = new byte[1000];
////				// streamIn.read(bs);
////				byte[] bs = new byte[streamIn.available()+1000];
////				// ArrayList<Byte> bs = new ArrayList<Byte>();
////				// streamIn.read((byte[]) bs.toArray());
////				streamIn.read(bs);
////				client.handle(new String(bs));
////			} catch (IOException ioe) {
////				System.out.println("Listening error: " + ioe.getMessage());
////				try {
////					client.stop();
////				} catch (InterruptedException e) {
////					// TODO Auto-generated catch block
////					e.printStackTrace();
////				}
////			} catch (InterruptedException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}
////		}
////	}
//}
