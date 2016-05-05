/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import genericdatabaseclient.DataBaseConection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tifuivali
 */
public class QueryTableModel extends DefaultTableModel{
    
    /**
     * Create an table model as results of query.
     * @param query String of sql query.
     * @param dbc DataBase connection.
     * @throws SQLException 
     */
    public QueryTableModel(String query,DataBaseConection dbc) throws SQLException
    {
        ResultSet rs=dbc.getResults(query);
        String[] colNames=dbc.getColumnsName(rs);
        String[][] data=dbc.getData(rs, colNames);
        this.setDataVector(data, colNames);
    }
    
    
    

}
