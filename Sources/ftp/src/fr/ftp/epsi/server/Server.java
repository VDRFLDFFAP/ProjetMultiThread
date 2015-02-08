package fr.ftp.epsi.server;

import java.io.IOException;
import java.net.ServerSocket;


public class Server {

	public static void main(String[] args) {
		ServerSocket serverSocket;
		try {  
			serverSocket = new ServerSocket(14000,6);
			System.out.println("Demarrage du serveur...");
			Thread acceptation=new Thread(new AccepteConnection(serverSocket));
			acceptation.start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
			
	}

}
