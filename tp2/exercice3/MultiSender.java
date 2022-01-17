package exo3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MultiSender extends Thread {

	protected MulticastSocket socket = null;
	protected InetAddress group = null;
	protected String message;
	protected int port;
	
    public MultiSender(String inet_addr, int port, String message) throws IOException{
    	this.group = InetAddress.getByName(inet_addr);
		this.socket = new MulticastSocket(port);
		this.message = message;
		this.port = port;
    }
    
	public  void run(){
		System.out.println("Packet en cours");
		while(true){
			try {
				DatagramPacket pack = new DatagramPacket(message.getBytes(), message.getBytes().length, this.group, this.port);
				socket.send(pack);
				System.out.println("Envoie du packet : "+ new String(pack.getData()));
				Thread.sleep(1000);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}