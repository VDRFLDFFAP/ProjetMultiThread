package fr.epsi.thread;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created by fr2i on 10/12/14.
 *
 * Cette classe peut etre utilisée pour gérer des requetes clients entrantes
 * Gérer plusieurs clients
 *
 */
public class Handler extends Thread{

    Socket sock;

    Handler(Socket p_sock)
    {
        this.sock =p_sock;
    }

    public void run(){

        try {
            DataInputStream in = new DataInputStream(sock.getInputStream());
            PrintStream out = new PrintStream(sock.getOutputStream(), true);

            //Gestion des requetes 

            sock.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
