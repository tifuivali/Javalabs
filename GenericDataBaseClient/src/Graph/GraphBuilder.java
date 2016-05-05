package Graph;


import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
import genericdatabaseclient.DataBaseConection;
import java.sql.SQLException;
import java.util.Arrays;
import org.apache.solr.common.util.NamedList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tifuivali
 */
public class GraphBuilder {
    
    private mxGraphComponent graphComponent=null;
    private DataBaseConection dataBaseConection=null;
    private static double WIDH_VERTEX=80;
    private final static double  HEIGHT_VERTEX=30;
    private double lastX=20;
    private double lasty=20;
    NamedList<Object> namedList;
    public GraphBuilder(DataBaseConection dbc)
    {
        this.dataBaseConection=dbc;
        
    }
    
   public void build() throws SQLException
   {
        mxGraph mgraph=new mxGraph();
        namedList=new NamedList<>();
        Object defaultParent = mgraph.getDefaultParent();
        mgraph.getModel().beginUpdate();
        String[] namesAllTables=dataBaseConection.getDbaTablesNames();
        WIDH_VERTEX=getMaxLength(namesAllTables)*10;
        for(String nameTable:namesAllTables)
        {
            Object v=mgraph.insertVertex(defaultParent,null,nameTable, lastX, lasty, WIDH_VERTEX, HEIGHT_VERTEX);
            UpdateCoordonate();
            namedList.add(nameTable, v);
        }
        for(String nameTableString:namesAllTables)
        {
            Object vPk=namedList.get(nameTableString);
            String[] fkNames=dataBaseConection.getFkTableNames(nameTableString);
            for(String fkname:fkNames)
            {
                Object vFk=namedList.get(fkname);
                mgraph.insertEdge(defaultParent, null, "",vFk, vPk);
            }
        }
       
        mgraph.getModel().endUpdate();
        this.graphComponent = new mxGraphComponent(mgraph);     
   }
   
   private int getMaxLength(String[] l)
   {
       String[] list=l.clone();
       Arrays.sort(list, (String o1, String o2) -> {
           return Integer.compare(o1.length(), o2.length()); 
       });
       return list[list.length-1].length();
       
   }
   
    private void UpdateCoordonate()
    {
        if(lastX+50+WIDH_VERTEX<800)
        {
            lastX= (lastX+WIDH_VERTEX+50);
            return;
        }
        lasty=lasty+HEIGHT_VERTEX+80;
        lastX=20;
    }
   
    /**
     * @return the graphComponent
     */
    public mxGraphComponent getGraphComponent() {
        return graphComponent;
    }
   
   
    
}
