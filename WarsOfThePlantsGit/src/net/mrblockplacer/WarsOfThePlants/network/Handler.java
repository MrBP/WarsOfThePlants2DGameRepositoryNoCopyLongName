package net.mrblockplacer.WarsOfThePlants.network;

import net.mrblockplacer.WarsOfThePlants.packets.SomeRequest;
import net.mrblockplacer.WarsOfThePlants.packets.SomeResponse;

import com.esotericsoftware.kryonet.Connection;

public class Handler {

	public static void handle(Connection connection, Object object) {
		if (object instanceof SomeResponse) {
			SomeResponse obj = (SomeResponse) object;
			System.out.println(obj.text);
		} else if (object instanceof SomeRequest) {
			SomeRequest obj = (SomeRequest) object;
			System.out.println(obj.text);
		}
		// else if (object instanceof SimpleText) {
		// SimpleText obj = (SimpleText) object;
		// System.out.println(obj.textToSend);
		// }

	}

}
