package fr.ftp.epsi.server;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Emission implements Runnable {

	public static final String path="D:\\FTP";
	private PrintWriter out;
	private BufferedReader in = null;
	boolean connecte;
	private Socket soc;
	
	public Emission(Socket soc) {
		this.soc=soc;
		 connecte=true;
	}


	public void run(){
		try {
			
			out=new PrintWriter(soc.getOutputStream());
			getRepositery();
			in=new BufferedReader(new InputStreamReader(soc.getInputStream()));
			String file=in.readLine();
		    sendFile(file);
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


	

	private void sendFile(String fileWithoutRacine) throws IOException {
		FileInputStream fis=null;
		BufferedInputStream bis=null;
		OutputStream os=null;
		String file = path+"\\"+fileWithoutRacine;
		
		
		try {
			 System.out.println("send file "+file+"...");
	          File myFile = new File(file);
	          out=new PrintWriter(soc.getOutputStream());
	         
	          byte [] mybytearray  = new byte [(int)myFile.length()];
	          
	          fis = new FileInputStream(myFile);
	          bis = new BufferedInputStream(fis);
	          bis.read(mybytearray,0,mybytearray.length);
	         
	          
	          out.println(tailleFichier(file));
	          
	          out.flush();
	          os = soc.getOutputStream();
	          
	          System.out.println("Sending " + file + "(" + mybytearray.length + " bytes)");
	          os.write(mybytearray,0,mybytearray.length);
	          os.flush();
	          System.out.println("Done.");
	        }
	        finally {
	          if (bis != null) bis.close();
	          if (os != null) os.close();
	        }
		
	}


	private void getRepositery() throws IOException {
		String [] s = new File(path).list(); 
		List<String> listeFichiers = new ArrayList<String>(); 
		for (int i=0; i<s.length;i++) 
		{ 
		 System.out.println(s[i]); 
		 listeFichiers.add(s[i]); 
		} 
		for(String fichier: listeFichiers){
			out.println(fichier);
		}
		out.println("FIN");
		out.flush();
	}
	
	private String tailleFichier(String name){
		File file=new File(name);
		int taille;
		if(file.exists()){
			 taille=(int) file.length();
			 return String.valueOf(taille);
		}
		return "";
	}
	
	
}