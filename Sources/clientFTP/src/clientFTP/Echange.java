package clientFTP;

import java.io.*;
import java.net.*;
import java.util.Scanner;


public class Echange implements Runnable{

	private Socket socket;
	private PrintWriter out = null;
	private BufferedReader in = null;
	private String message=null;
	private String file;
	private Thread t3, t4;
	
	public Echange(Socket s){
		socket = s;
	}
	
	public void run() {
		try {
			out = new PrintWriter(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			message=in.readLine();
			while(!message.equals("FIN")){
			 try{
			   message=in.readLine();
			   System.out.println(message);
				  }
				  catch(IOException e){
					  e.printStackTrace();
				  }
			}
			
			
			Thread t4 = new Thread(new Emission(out));
			t4.start();
			Thread t3 = new Thread(new Reception(socket));
			t3.start();
		
		   
		    
		} catch (IOException e) {
			System.err.println("Le serveur distant s'est déconnecté !");
		}
	}
}

