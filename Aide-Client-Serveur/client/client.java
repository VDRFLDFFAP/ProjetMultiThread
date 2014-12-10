
import javax.swing.*;
import java.io.*;
import java.net.*;
import java.util.StringTokenizer;

public class client {
    public static void main(String[] args) throws IOException {


		 String host = "127.0.0.1";


        communication = new Socket(host, 8189);
        in = new BufferedReader(new InputStreamReader(communication
                .getInputStream()));
        out = new PrintWriter(communication.getOutputStream(), true);

        int nbelement = Integer.parseInt(in.readLine());
        System.out.println(nbelement +"\n\n\n");
      int i=0;
        while (i != nbelement-2/*je sais pas pourquoi mais ca ne marche que si je fait ca*/) {
            String str = in.readLine();
            System.out.println(str);
            i++;
            if(i== nbelement-2)break;//sureter supplementaire

        }
        String saisie = JOptionPane
                .showInputDialog("entree l'id du fichier qui vous interresse");
        out.println(saisie);
        for (int p = 0; p < 4; p++) {
            s = in.readLine();

        }

        StringTokenizer t = new StringTokenizer(s, "|");
        int id = Integer.parseInt(t.nextToken("|"));
        String name = t.nextToken("|");
        int taille = Integer.parseInt(t.nextToken("|"));
        System.out.println("\n\nid = " + id + "\nnom = " + name + "\ntaille = "
                + taille);

				        name = name.substring(name.lastIndexOf('\\') + 1);

			System.out
                .println("connection sur le port 8188 pour commencer le transfert");
        transmition = new Socket(host, 8188);
        transmettre = new PrintWriter(transmition.getOutputStream(), true);
        InputStream brut = transmition.getInputStream();
        InputStream entree = new BufferedInputStream(brut);
        System.out
                .println("\nenvoi du nom du fichier");
        transmettre.println(name);
        int tailleDuContenu = taille;
        byte[] donnees = new byte[taille];
        int octetsLus = 0;
        int deplacement = 0;
        int iteration = 1;
        while (deplacement < tailleDuContenu) {
            System.out.println("recuperation du fichier : " + iteration);
		iteration++;
            octetsLus = entree.read(donnees, deplacement, donnees.length
                    - deplacement);
            if (octetsLus == -1)
                break;
            deplacement += octetsLus;
            System.out.println(deplacement);
        }
        FileOutputStream fichiersortant = new FileOutputStream(name);
System.out.println("\nFileOutputStream fichiersortant = new FileOutputStream( " + name + ");");
        fichiersortant.write(donnees);
        //vidage du tampon
        fichiersortant.flush();
        //fermeture du flots de donnees
        fichiersortant.close();
        System.out.println("fin du transfert\nverification de l'existenbce du fichier :");
        File verif = new File(name);
        if(verif.exists())System.out.println("true");
        else
        System.out.println("false");


    }

    public static BufferedReader in;

    public static String s;
    public static PrintWriter transmettre;
    public static PrintWriter out;

    public static Socket transmition;

    public static Socket communication;
}