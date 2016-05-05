/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genericdatabaseclient;

import java.sql.Connection;
import java.sql.*;
import java.sql.SQLException;

/**
 *
 * @author tifuivali
 */
public class GenericDataBaseClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        try {
            DataBaseConection dbc=DataBaseConection.getInstance();
            Connection con=dbc.getConnection();
            Statement statement=con.createStatement();
            String sql="select * from studenti";
            ResultSet results=statement.executeQuery(sql);
            while(results.next())
            {
                String nr_matricol=results.getString("nr_matricol");
                String nume=results.getString("nume");
                System.out.println(nr_matricol+"  "+nume);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Conectare esuata! "+ex.getMessage());
        }
        
        
    }
    
}
