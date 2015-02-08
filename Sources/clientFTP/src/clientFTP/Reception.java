package clientFTP;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Reception implements Runnable {
	private Socket sock=null;
    
	
    
    
    public Reception( Socket soc) throws UnknownHostException, IOException {
		this.sock=soc;

	}
	  
	  
	  
	  @Override
	public void run() {
	
		  try {
			Thread.sleep(2000); 
			
			telechargerFichier("downloaded");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		  catch (InterruptedException e) {
				e.printStackTrace();
			}	
	}


	public void telechargerFichier(String filename) throws UnknownHostException, IOException{
	    
		int tailleFichierRecu;
	    int bytesRead;
	    int current = 0;
	    FileOutputStream fos = null;
	    BufferedOutputStream bos = null;
	    
	    try {
	    	
	       tailleFichierRecu=getTaille();
	      System.out.println(tailleFichierRecu);
	      // receive file
	      byte [] mybytearray  = new byte [tailleFichierRecu];
	      InputStream is = sock.getInputStream();
	     // String filename=getNameFile();
	    
	      fos = new FileOutputStream("C:\\TEMP\\"+filename);
	      bos = new BufferedOutputStream(fos);
	      bytesRead = is.read(mybytearray,0,mybytearray.length);
	      current = bytesRead;

	     do {
	         bytesRead =is.read(mybytearray, current, (mybytearray.length-current));
	         if(bytesRead >= 0) current += bytesRead;
	      } while(current <tailleFichierRecu);

	      bos.write(mybytearray, 0 , current);
	      bos.flush();
	      System.out.println("Fichier " + filename
	          + " telechargé (" + current + " octets lus)");
	    }
	    finally {
	      if (fos != null) fos.close();
	      if (bos != null) bos.close();
	      if (sock != null) sock.close();
	    }
	  }

	private int getTaille() throws IOException{
		 
		BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
       int taille=Integer.parseInt(in.readLine());
	    return taille;
	}
	
	private String getNameFile() throws IOException{
		BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		return in.readLine();
		
	}

}
