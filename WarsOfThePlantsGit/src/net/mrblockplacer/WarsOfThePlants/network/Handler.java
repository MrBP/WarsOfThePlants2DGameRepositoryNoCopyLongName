package net.mrblockplacer.WarsOfThePlants.network;

import java.util.ArrayList;

import net.mrblockplacer.WarsOfThePlants.entity.movingobjects.Player;

public class Handler {
	public static ArrayList<Player> al = new ArrayList<Player>();

	// public static void handle(Connection connection, Object object) {
	// if (object instanceof SomeResponse) {
	// SomeResponse obj = (SomeResponse) object;
	// // MainClass.network.tcpid = Integer.parseInt(obj.text.split(":")[0]);
	// // MainClass.network.udpid = Integer.parseInt(obj.text.split(":")[1]);
	// System.out.println(obj.text);
	// }
	// if (object instanceof SomeRequest) {
	// SomeRequest obj = (SomeRequest) object;
	// System.out.println(obj.text);
	// }
	// if (object instanceof PacketPlayer) {
	// // PacketPlayer pp = (PacketPlayer) object;
	// // if (pp.id != MainClass.network.udpid) {
	// // al.add(new Player(pp.x, pp.y, pp.id));
	// // }
	// }
	// }

	public static void clearArray() {
		al.clear();
	}

}
