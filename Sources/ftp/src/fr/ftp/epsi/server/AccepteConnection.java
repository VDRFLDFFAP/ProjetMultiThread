package fr.ftp.epsi.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class AccepteConnection implements Runnable {
	private ServerSocket serverSoc=null;
	private Socket soc=null;
	public AccepteConnection(ServerSocket serverSoc){
		this.serverSoc=serverSoc;
	}
	
	
	public void run(){
		System.out.println("En attente de client...");
		try {
			while(true){
			soc=serverSoc.accept();
			System.out.println("un client connecté");
			Thread auth=new Thread(new AuthentificationClient(soc));
			auth.start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


}
