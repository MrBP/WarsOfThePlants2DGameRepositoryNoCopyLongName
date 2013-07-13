package net.mrblockplacer.WarsOfThePlants.network;

import java.util.ArrayList;

import net.mrblockplacer.WarsOfThePlants.Game;
import net.mrblockplacer.WarsOfThePlants.entity.mob.Player;
import net.mrblockplacer.WarsOfThePlants.packets.SomeRequest;
import net.mrblockplacer.WarsOfThePlants.packets.SomeResponse;

import com.esotericsoftware.kryonet.Connection;

public class Handler {
	public static ArrayList<Player> al = new ArrayList<Player>();

	public static void handle(Connection connection, Object object) {
		if (object instanceof SomeResponse) {
			SomeResponse obj = (SomeResponse) object;
			// Game.network.tcpid = Integer.parseInt(obj.text.split(":")[0]);
			// Game.network.udpid = Integer.parseInt(obj.text.split(":")[1]);
			System.out.println(obj.text);
		}
		if (object instanceof SomeRequest) {
			SomeRequest obj = (SomeRequest) object;
			System.out.println(obj.text);
		}
		if (object instanceof PacketPlayer) {
			PacketPlayer pp = (PacketPlayer) object;
			// if (pp.id != Game.network.udpid) {
			// al.add(new Player(pp.x, pp.y, pp.id));
			// }
		}
	}

	public static void clearArray() {
		al.clear();
	}

}
