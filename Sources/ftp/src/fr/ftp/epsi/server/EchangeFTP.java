package fr.ftp.epsi.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchangeFTP implements Runnable{
	
		private Socket socket = null;
		private BufferedReader in = null;
		private PrintWriter out = null;
		private String login;
		private Thread threadClient, threadServeur;
		
		
		public EchangeFTP(Socket s, String log){
			
			socket = s;
			login = log;
		}
		public void run() {
			
			try {
			/*in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream());
			
			Thread threadClient = new Thread(new Reception(in,login));
			threadClient.start();*/
			Thread threadServeur = new Thread(new Emission(socket));
			threadServeur.start();
			
			} catch (Exception e) {
				System.err.println(login +"est deconnecté ");
			}
	}
	}

