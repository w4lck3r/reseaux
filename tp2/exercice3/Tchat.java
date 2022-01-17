package exercice3;


public class Tchat extends Thread {

	final static String INET_ADDR = "224.0.0.1";
	final static int PORT = 7654;
	final static int BUFFER_SIZE = 1024;

	public static void main(String[] args) {
		try {
			Thread serveur = new Multi_Reciever(INET_ADDR,BUFFER_SIZE);
			serveur.start();
			System.out.println("Serveur up");
			Thread multiSender = new MultiSender(INET_ADDR, PORT, "Yo i guess u can read that");
			multiSender.start();
			System.out.println("MultiSender start");
		} catch(Exception e ) {
			e.printStackTrace();
		}
	}
}
