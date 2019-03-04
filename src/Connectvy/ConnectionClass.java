/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connectvy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Abdulaziz
 */
public class ConnectionClass {
    public Connection connection;
        Statement statement1;

    
    public  Connection getConnection(){
        String dbName="sql12252436";
        String userName="sql12252436";
        String password="lGryJjY5EN";

        String dbName1="MO_DB";
        String userName1="root";
        String password1="";

        String dbName2="azoz";
        String userName2="sa";
        String password2="1418";
        
        String dbName3="project";
        String userName3="root";
        String password3="";





        try {
            Class.forName("com.mysql.jdbc.Driver");

           // Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

           //connection= DriverManager.getConnection("jdbc:mysql://sql12.freemysqlhosting.net:3306/"+dbName,userName,password);

         connection= DriverManager.getConnection("jdbc:mysql://localhost/"+dbName1,userName1,password1);
            // its ok with port and with out port
          // connection= DriverManager.getConnection("jdbc:sqlserver://192.168.1.90:1433; DatabaseName=azoz","sa","1418");
//


        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }
    
    
public ResultSet execQuery(String query) {


        ResultSet result;
        try {
            //connectDB();  عشان لمى اشغل المين الثاني يتصل بالداتا عن هذي الميثود

            connectDB();
            result = statement1.executeQuery(query);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error:" + ex.getMessage(), "Error Occured", JOptionPane.ERROR_MESSAGE);
            System.out.println("Exeption at execAxtion " + ex.getLocalizedMessage());
            return null;

        }
        return result;

    }

  public void connectDB() {
        ConnectionClass connectionClass = new ConnectionClass();
        // we call conneClass  that we make it up
        Connection connection = connectionClass.getConnection();
        try {
            statement1 = connection.createStatement();
        } catch (SQLException ex) {
            System.out.println("Exeption at createStatement " + ex.getLocalizedMessage());
        }


    }


    

    
}
