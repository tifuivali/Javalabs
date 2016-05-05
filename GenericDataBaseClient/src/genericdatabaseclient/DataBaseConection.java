/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genericdatabaseclient;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tifuivali
 */
public class DataBaseConection {
    
    private Connection connection=null;
    private final DatabaseMetaData metadata=null;
    public static String strSchemaDB="VALI";
    public static final String DEFAULT_STR_SCHEMA="VALI";
    private DataBaseConection(Connection con)
    {
        this.connection=con;
    }
    
    /**
     * Create a data base connection.
     * @return Return a new instance of DataBaseConection
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public static DataBaseConection getInstance() throws ClassNotFoundException, SQLException
    {
        
        String url="jdbc:oracle:thin:@localhost:1521:xe";
        Class.forName("oracle.jdbc.driver.OracleDriver").getInterfaces();
        
        Connection conn=DriverManager.getConnection(url,"VALI","VALI");
        conn.setAutoCommit(false);
        DataBaseConection data=new DataBaseConection(conn);
        return  data;
        
    }
    /**
     * Execute an udate operation to data base.
     * @param query Sql quwry string.
     * @throws SQLException 
     */
    public void executeUpdate(String query) throws SQLException
    {
        Statement stmt =connection.createStatement();
        stmt.executeUpdate(query);
    }
    /**
     * Close the connection.
     * @throws SQLException 
     */
    public void closeConnection() throws SQLException 
    {
        connection.close();
    }

    /**
     * @return the connection
     */
    public Connection getConnection() {
        return connection;
    }
    
    /**
     * Get resutset of query.
     * @param query
     * @return ResultSet of query.
     * @throws SQLException 
     */
    public ResultSet getResults(String query) throws SQLException
    {
        Statement stmt=connection.createStatement();
        return  stmt.executeQuery(query);
    }
    /**
     * Return names of columns from the results.
     * @param results ResultSet of results.
     * @return String[] name columns of results.
     * @throws SQLException 
     */
    public String[] getColumnsName(ResultSet results) throws SQLException
    {
        ResultSetMetaData rsmd=results.getMetaData();
        int n=rsmd.getColumnCount();
        String[] names=new String[n];
        for(int i=1;i<=n;i++)
        {
            names[i-1]=rsmd.getColumnName(i);
        }
        return names;
    }
    
    /**
     * Get evectively data of query.Rows of query results.
     * @param rs The result set if results.
     * @param colNames The names of columns.
     * @return Rows data of query.
     * @throws SQLException 
     */
    public String[][] getData(ResultSet rs,String[] colNames) throws SQLException
    {
        List<String[]> list=new ArrayList<>();
        while(rs.next())
        {
            String[] row=new String[colNames.length];
            for(int i=0;i<colNames.length;i++)
            {
                row[i]=rs.getString(colNames[i]);
            }
            list.add(row);
        }
        String[][] data=new String[list.size()][colNames.length];
        for(int i=0;i<list.size();i++)
        {
            data[i]=list.get(i);
        }
        return data;
    }
    /**
     * Get Data Base tables name.Return names of tables from current data base connection.
     * @return String[] names of tables.
     * @throws java.sql.SQLException
     */
    public String[] getDbaTablesNames() throws SQLException
    {
        String[] TABLE_TYPE={"TABLE"};
        List<String> tblNames=new ArrayList<>();
        String[] names ;
        DatabaseMetaData dbmd=connection.getMetaData();
        ResultSet rs=dbmd.getTables(connection.getCatalog(),strSchemaDB,null, TABLE_TYPE);
        while(rs.next())
        {
            tblNames.add(rs.getString("TABLE_NAME"));
        }
        names=new String[tblNames.size()];
        return tblNames.toArray(names);
    }
    
     /**
     * Get Data Base views name.Return names of views from current data base connection.
     * @return String[] names of views.
     * @throws java.sql.SQLException
     */
    public String[] getDbaViewsNames() throws SQLException
    {
        String[] VIEW_TYPE={"VIEW"};
        List<String> tblNames=new ArrayList<>();
        String[] names;
        DatabaseMetaData dbmd=connection.getMetaData();
        ResultSet rs=dbmd.getTables(connection.getCatalog(),strSchemaDB,null,VIEW_TYPE);
        while(rs.next())
        {
            tblNames.add(rs.getString("TABLE_NAME"));
        }
        names=new String[tblNames.size()];
        return tblNames.toArray(names);
    }
    /**
     * Get Data Base trigger names.Return names of triggers from current data base connection.
     * @return String[] names of triggers.
     * @throws java.sql.SQLException
     */
    public String[] getDbaTriggerNames() throws SQLException
    {
        String[] VIEW_TYPE={"TRIGGER"};
        List<String> tblNames=new ArrayList<>();
        String[] names;
        DatabaseMetaData dbmd=connection.getMetaData();
        ResultSet rs=dbmd.getTables(connection.getCatalog(),strSchemaDB,null,VIEW_TYPE);
        while(rs.next())
        {
            tblNames.add(rs.getString("TABLE_NAME"));
        }
        names=new String[tblNames.size()];
        return tblNames.toArray(names);
    }
    public String[] getProcedureNames() throws SQLException
    {
        String[] names;
        List<String> list=new ArrayList<>();
        DatabaseMetaData dbmd=connection.getMetaData();
        ResultSet rs=dbmd.getProcedures(connection.getCatalog(),strSchemaDB, null);
        while(rs.next())
        {
            list.add(rs.getString("PROCEDURE_NAME"));
        }
        names=new String[list.size()];
        return  list.toArray(names);
        
    }
    /**
     * Return two list , first list with names of tables fk and second list with names of tables pk.
     * @return String[][] two first list of fk table names , second list of pk table names.
     * @throws SQLException 
     */
    public String[][] getAllTableNamesForeignKeys() throws SQLException
    {
          String[][] names;
        List<String> listpk=new ArrayList<>();
        List<String> listfk=new ArrayList<>();
        DatabaseMetaData dbmd=connection.getMetaData();
        ResultSet rs;
        rs = dbmd.getExportedKeys(connection.getCatalog(),strSchemaDB, null);
        while(rs.next())
        {
            listfk.add(rs.getString("FKTABLE_NAME"));
            listpk.add(rs.getString("PKTABLE_NAME"));
        }
        names=new String[2][listfk.size()];
        for(int i=0;i<names[0].length;i++)
        {
            names[0][i]=listfk.get(i);
            names[1][i]=listpk.get(i);
        }
        return names;
    }
    /**
     * Get table names that contains foreign keys associate with table parameter.
     * @param pkTableName Table with pk key
     * @return String[] table names.
     * @throws SQLException 
     */
    public String[] getFkTableNames(String pkTableName) throws SQLException
    {
        String[] names;
        List<String> listfk=new ArrayList<>();
        DatabaseMetaData dbmd=connection.getMetaData();
        ResultSet rs=dbmd.getExportedKeys(connection.getCatalog(),strSchemaDB, pkTableName);
        while(rs.next())
        {
            listfk.add(rs.getString("FKTABLE_NAME"));
        }
        names=new String[listfk.size()];
        return listfk.toArray(names);
    }
    



    /**
     * @return the metadata
     */
    public DatabaseMetaData getMetadata() {
        return metadata;
    }
    
}
