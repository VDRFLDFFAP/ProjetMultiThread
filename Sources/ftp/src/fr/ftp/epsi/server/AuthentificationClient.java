package fr.ftp.epsi.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AuthentificationClient implements Runnable {
	Socket soc;
	BufferedReader in=null;
	PrintWriter out=null;
	String login;
	String password;
	
	public AuthentificationClient(Socket soc){
		this.soc=soc;
		
	}
	
	public void run(){
		try {
			in= new BufferedReader(new InputStreamReader(soc.getInputStream()));
			out=new PrintWriter(soc.getOutputStream());
			boolean correct=false;
			
			while(!correct){
			out.println("Entrez votre login: ");
			out.flush();
			login=in.readLine();
			
			out.println("Entrez votre mot de passe: ");
			out.flush();
			password=in.readLine();
			
			correct=clientIsValid(login,password);
			if(correct){
				out.println("connecte");
				out.flush();
			}
			else{
				out.println("erreur");
				out.flush();
			}
			}
			//ici rajouter le client a un pool de thread avec ses commandes
			Thread t=new Thread(new EchangeFTP(soc,login));
			t.start();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	private boolean clientIsValid(String login2, String password2) {
		
		return true;
	}

}
