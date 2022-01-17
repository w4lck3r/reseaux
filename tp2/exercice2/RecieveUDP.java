package exercice2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class RecieveUDP {

    final static String INET_ADRESS = "224.0.0.1";
    final static int PORT = 7654;
    final static int BUFFER_SIZE = 512;
    
    public static void main() {
        
    	InetAddress group = null;
    	MulticastSocket socket = null;
    	
        try {
        	group = InetAddress.getByName(INET_ADRESS);
        }
        catch (UnknownHostException e) {
            e.getStackTrace();
        }
        
        try {
        	socket = new MulticastSocket(PORT);
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
        try {
			socket.joinGroup(group);
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        
        DatagramPacket msgPacket = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
        while(true)
		{
            try {
		    	socket.receive(msgPacket);
		    } catch (IOException e) {
		    	e.printStackTrace();
		    }

            System.out.println("packet reçu : " + msgPacket.getAddress()+"port : " + msgPacket.getPort() + "taille : " + msgPacket.getLength());
            System.out.println("message : " + new String(msgPacket.getData()));
        }
    }
}

