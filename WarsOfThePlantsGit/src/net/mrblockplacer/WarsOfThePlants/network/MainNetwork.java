package net.mrblockplacer.WarsOfThePlants.network;

import java.io.IOException;

import net.mrblockplacer.WarsOfThePlants.Game;
import net.mrblockplacer.WarsOfThePlants.packets.SomeRequest;
import net.mrblockplacer.WarsOfThePlants.packets.SomeResponse;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

public class MainNetwork implements Runnable {
	public int udpid;
	public int tcpid;
	public Client client = new Client();
	SomeRequest request = new SomeRequest();

	// public static int id;
	// public static boolean testit = false;

	public MainNetwork() {
		try {
			new MainNetwork("direct.mrblockplacer.net", 25565);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public MainNetwork(String serverName, int serverPort) throws IOException {
		new Thread(this).start();
		// Game.networkUp = true;
		// System.err.println(testit);

	}

	public void sendText(String mess) {
		// System.out.println(client.equals(null));
		request.text = mess;
		client.sendTCP(request);
	}

	@Override
	public void run() {
		client.start();
		try {
			client.connect(1000, "localhost", 54555, 54777);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Kryo kryo = client.getKryo();
		kryo.register(SomeRequest.class);
		kryo.register(SomeResponse.class);
		kryo.register(PacketPlayer.class);
		client.addListener(new Listener() {
			public void received(Connection connection, Object object) {
				Handler.handle(connection, object);
			}
		});
		request.text = "Connecting to server....!";
		client.sendTCP(request);
		while (true) {
			PacketPlayer pp = new PacketPlayer();
			pp.id = udpid;
			pp.x = Game.player.x;
			pp.y = Game.player.y;
			client.sendUDP(pp);

		}
	}
}
