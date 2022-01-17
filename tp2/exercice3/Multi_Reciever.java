package exercice3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Multi_Reciever extends Thread{

	private String INET_ADRESS;
	private int buffer_size;

	public Multi_Reciever(String inet_addr, int buffer_size) {
		this.INET_ADRESS = inet_addr;
		this.buffer_size = buffer_size;
	}

	@SuppressWarnings("resource")
	public  void run(){
		MulticastSocket socket = null;
		try {
			socket = new MulticastSocket();
			socket.joinGroup(InetAddress.getByName(this.INET_ADRESS));
		}catch(Exception e) {
			e.printStackTrace();
		}		
		DatagramPacket msgPacket = new DatagramPacket(new byte[this.buffer_size], this.buffer_size);
		while(true){
			try {
				System.out.println("Ecoute");
				socket.receive(msgPacket);
				System.out.println("J'ai reçu :");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}