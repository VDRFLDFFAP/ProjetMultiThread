package fr.epsi.tools;

import com.mysql.jdbc.Connection;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by fr2i on 10/12/14.
 */
public class ConnexionBD {

    protected String user = "root";
    protected String mdp = "root";
    protected String url = "jdbc:mysql://localhost:3306/ServeurFtp";
    protected String dbClass = "com.mysql.jdbc.Driver";
    protected Connection connexion = null;




    public ConnexionBD(){

    }


    public void connexion(){

        try {
            Class.forName(dbClass);
            connexion = DriverManager.getConnection(url, user, mdp);


        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally
        {
            if(connexion != null){
                try {
                    connexion.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }


    }





}
