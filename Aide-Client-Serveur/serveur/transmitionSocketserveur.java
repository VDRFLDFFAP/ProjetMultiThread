
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class transmitionSocketserveur extends Thread {

    private ServerSocket transfert;

    public void run() {

        ServerSocket transmition;
        try {
            transfert = new ServerSocket(8188);

            System.out
                    .println("serveur de transmition demare!!! en attente d'une connection...");
            int o = 1;
            for (;;) {
                Socket entrante = transfert.accept();
                System.out.println("connection de transmition accepter n°" + o);
                System.out.println("transmition n " + o);
                t = new transmition(entrante, o);
                t.setDaemon(true);
                t.start();
                o++;
            }
        } catch (IOException e) {

            e.printStackTrace();
        }

    }

    public transmition t;
}