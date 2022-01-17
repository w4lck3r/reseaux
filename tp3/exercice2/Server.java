package exercice2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server extends Thread {

	public final int port = 7654;
	private ServerSocket server;
	public ArrayList<Socket> clients;

	public void init() throws IOException {
		server = new ServerSocket(port);
		clients = new ArrayList<Socket>();
		this.accept();
	}

	public void accept() throws IOException {
		while (true) {
			Client client = new Client(server.accept(), this);
			client.start();
		}
	}

	public static void main(String[] args) {
		Server serveur = new Server();
		try {
			serveur.init();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}