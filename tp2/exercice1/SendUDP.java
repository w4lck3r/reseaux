package exercice1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SendUDP {

    public static void main(String[] args) throws Exception{

        //DatagramPacket pocket = null;
        //DatagramSocket socket = null;

        String message= args[2];
		byte[] bytesMessage= message.getBytes();
		
		InetAddress destinary = InetAddress.getByName(args[0]);
		int port = Integer.parseInt(args[1]);

        DatagramPacket packet = new DatagramPacket(bytesMessage, bytesMessage.length ,destinary,port);
        DatagramSocket socket =  new DatagramSocket();

        socket.send(packet);
        
		socket.close();

    }
    
}
