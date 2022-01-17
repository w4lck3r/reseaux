package exercice2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class Client extends Thread {

	Server server;
	Socket client;
	BufferedReader input;
	List<String> leaveString = List.of("quit","leave",":q","stop");

	public Client(Socket socket, Server server) {
		this.client = socket;
		this.server = server;
	}

	public void run() {
		this.server.clients.add(this.client);
		System.out.println("Client : [address : " + client.getInetAddress() + "], [port : " + client.getPort() + "], [localport : " + client.getLocalPort() + "] connected.");
		try {
			this.listen();
		} catch (IOException e) {
			e.printStackTrace();
			try {
				client.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

	public void listen() throws IOException {
		input = new BufferedReader(new InputStreamReader(client.getInputStream()));
		String message;

		while ( (message = input.readLine()) != null && !this.leaveString.contains(message.toLowerCase())) {
			this.diffuseMessage(message);
		}
		input.close();
		server.clients.remove(client);
		System.out.println("Client " + client.toString() + " disconnected.");
		client.close();
	}
	
	public void diffuseMessage(String message) {
		for (Socket socket : server.clients) {
			if (!socket.equals(this.client)) {
				try {
					PrintWriter out = new PrintWriter(socket.getOutputStream());
					out.println(this.client.getInetAddress() + " : " + message);
					out.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}