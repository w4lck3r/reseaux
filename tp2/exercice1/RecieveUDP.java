package exercice1;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class RecieveUDP {
    public static void main(String[] args) throws Exception{

        DatagramPacket pocket = null ;
        DatagramSocket socket = null ;
        
        int port = Integer.parseInt(args[0]);

        pocket = new DatagramPacket(new byte[512], 512);
        socket = new DatagramSocket(port);

        String message ;

        socket.receive(pocket);

        message  = new String(pocket.getData());
        System.out.println(message);
        
        socket.close();


    }
}