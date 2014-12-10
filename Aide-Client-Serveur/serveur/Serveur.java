
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {

    public static void main(String[] args) throws IOException {
        ServerSocket s = new ServerSocket(8189);
        System.out.println("serveur demare!!! en attente d'une connection...");
        int i = 1;
        for (;;) {
            Socket incoming = s.accept();
            System.out.println("connection acepter n°" + i);
            System.out.println("Spawning " + i);
            Thread t = new Communication(incoming, i);
            Thread t2 = new transmitionSocketserveur();
            t2.start();
            t.start();
            i++;
        }

    }
}