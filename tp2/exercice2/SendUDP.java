package exercice2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class SendUDP {

	
	final static String INET_ADRESS = "224.0.0.1";
    final static int PORT = 7654;
    public static void main(String[] args) {
		
		InetAddress group = null;
		MulticastSocket socket = null;
		try {
			group = InetAddress.getByName(INET_ADRESS);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		try {
			socket = new MulticastSocket(PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			socket.joinGroup(group);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String mess = args[0];
		byte[] byteMess = mess.getBytes();
				
		DatagramPacket packet = new DatagramPacket(byteMess, byteMess.length ,group,PORT);
		
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		socket.close();
	
	}
}
