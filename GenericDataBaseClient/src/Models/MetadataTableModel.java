/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import genericdatabaseclient.DataBaseConection;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tifuivali
 */
public class MetadataTableModel extends DefaultTableModel{
    
    private DataBaseConection dba=null;
    
    /**
     * Create a new Metadata Table Model.
     * @param dataBaseConection 
     * @throws java.sql.SQLException 
     */
    public MetadataTableModel(DataBaseConection dataBaseConection) throws SQLException
    {
        this.dba=dataBaseConection;
        addTableNamesCollumn();
        addViewNamesCollumn();
        addTriggerNamesCollumn();
        addProceudreNamesCollumn();
        addPKFKTableNames(); 
    }
    private void addTableNamesCollumn() throws SQLException
    {
        String[] names;
        names=dba.getDbaTablesNames();
        this.addColumn("TABLES", names);
    }
    private void addViewNamesCollumn() throws SQLException
    {
        String[] names;
        names=dba.getDbaViewsNames();
        this.addColumn("Views", names);
    }
    private void addTriggerNamesCollumn() throws SQLException
    {
        String[] names;
        names=dba.getDbaTriggerNames();
        this.addColumn("Triggers", names);
    }
    private void addProceudreNamesCollumn() throws SQLException
    {
        String[] names;
        names=dba.getProcedureNames();
        this.addColumn("Procedures", names);
    }
    private void addPKFKTableNames() throws SQLException
    {
        String[][] names;
        names=dba.getAllTableNamesForeignKeys();
        this.addColumn("FKTableNames", names[0]);
        this.addColumn("PKTableNames", names[1]);
    }
}
