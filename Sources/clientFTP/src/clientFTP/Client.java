package clientFTP;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	public static Socket socket=null;
	public static Thread t;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        
		try{
			System.out.println("tentative de connection");
			socket=new Socket("127.0.0.1",14000);
			
			t=new Thread(new Connexion(socket));
			t.start();
			
		}
		catch(UnknownHostException e){
			System.err.println("impossible de se connecter a l'hote "+socket.getLocalAddress());
		}
		catch(IOException e){
			System.err.println("erreur de port de connection "+socket.getLocalPort());
		}
	}
	

}
