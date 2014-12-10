
import java.io.*;
import java.net.*;

class transmition extends Thread implements Runnable {
    public transmition(Socket connect, int g) throws IOException {
        SocketTransmition = connect;
        g = connecte;
    }

    public void run() {

        try {
            System.out.println("\n demarrage du demon de transmition");
            in = new BufferedReader(new InputStreamReader(SocketTransmition
                    .getInputStream()));
            out = new PrintWriter(SocketTransmition.getOutputStream(), true);
            System.out.println("\n boucle de recuperation du nom du fichier");
            int boucle = 1;
         //recuperation du nom du fichier
         do{
                str = in.readLine();
                System.out.println(str);
                boucle++;
               // if(str==null)break;
            }while (boucle != 2) ;
            System.out.println("\nfin de la boucle");
            System.out.println("\nrecuperation de la taille du fichier");

            File toTransmit = new File(str);
            int taille = (int) toTransmit.length();
System.out.println("\n demarrage du demon de transmition");

            FileInputStream DataToSend = new FileInputStream(toTransmit);
            byte[] FileToSend = new byte[taille];
            int ReadOctet = 0;
            int MovedByte = 0;
            System.out
                    .println("\nfin de la creation du tableau et des variable de transfert");

            while (MovedByte < taille) {
                System.out.println("\n debut du transfert");

                System.out.println("\nvaleur de MovedByte : " + MovedByte
                        + "\n" + "valeur de ReadOctet : " + ReadOctet);
                ReadOctet = DataToSend.read(FileToSend, MovedByte,
                        FileToSend.length - MovedByte);
                if (ReadOctet == -1)
                    break;
                MovedByte += ReadOctet;
                System.out.println(MovedByte);
                System.out.println("\nfin du bloc");

                PrintStream DataSender = new PrintStream(SocketTransmition
                        .getOutputStream());
                DataSender.write(FileToSend);
                System.out.println("\nfin du transfert");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Socket SocketTransmition;

    public BufferedReader in;

    public PrintWriter out;

    public static String str;

    public int connecte;
}