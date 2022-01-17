package exercice1;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private final static int PORT = 7654;
    private final static String MESSAGE = "Welcome to my server , sorry i dont have dns =/";

    public static void main(String[] args) {

        ServerSocket server = null;
        try {
            server = new ServerSocket(PORT);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        
        while (true) {
            Socket client = null;
            try {
                client = server.accept();
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            try {
            new PrintStream(client.getOutputStream()).println(MESSAGE);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            try {
            client.close();
            }catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}