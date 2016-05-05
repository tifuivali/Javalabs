/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QueryOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author tifuivali
 */
public class QueryHistory implements Serializable{
    
    private  List<String> queries_list;
    private  int curentPosition=0;
    
    /**
     * Create new QueryHistory list
     * @param queries List of queries.
     */
    public QueryHistory(String...queries)
    {
        this.queries_list=new ArrayList<>();
        queries_list.addAll(Arrays.asList(queries));
        curentPosition=this.queries_list.size()-1; 
    }
    
    /**
     * Create an empty QueryHistory list.
     */
    public QueryHistory()
    {
        this.queries_list=new ArrayList<>();
        this.curentPosition=0;
    }
    /**
     * Add new query in history queries list.
     * @param query Query string. 
     */
    public void addQuery(String query)
    {
        this.queries_list.add(query);
        this.curentPosition=queries_list.size()-1;
    }

    /**
     * Return  list of queries. 
     * @return the queries_list
     */
    public List<String> getQueries_list() {
        return queries_list;
    }

    /**
     * Set list of queries.
     * @param queries_list the queries_list to set
     */
    public void setQueries_list(List<String> queries_list) {
        this.queries_list = queries_list;
        this.curentPosition=this.queries_list.size()-1;
    }
    /**
     *Return forward query of list from curent position and move to forward position.
     * @return String of forward query of list.
     * @throws QueryOptions.EmptyListException
     */
    public String getForwardQuery() throws EmptyListException
    {
        if(queries_list.size()<=0)
        {
            throw new EmptyListException();
        }
        if(curentPosition==0)
        {
            return queries_list.get(curentPosition);
        }
        curentPosition--;
        return  queries_list.get(curentPosition);
    }
     /**
     *Return next query of list from curent position and move to next position.
     * @return String next query of list.
     * @throws QueryOptions.EmptyListException
     */
    public String getNextQuery() throws EmptyListException
    {
        if(queries_list.size()<=0)
        {
            throw new EmptyListException();
        }
        if(curentPosition==queries_list.size()-1)
        {
            return "";
        }
        curentPosition++;
        return  queries_list.get(curentPosition);
    }
    /**
     * Serialize curent list to specified file.
     * @param fileName 
     */
    public void serialize(String fileName) 
    {
        File file=new File(fileName);
        file.delete();
        try {
            
            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(this);
        }  catch (IOException ex) {
           
        }
        
    }
    /**
     * Deserialize an QueryHistory list from file or null if a problem ocurred.
     * @param filename
     * @return 
     */
    public static QueryHistory deserialize(String filename)
    {
        QueryHistory qh=null;
        File file=new File(filename);
        try {
            ObjectInputStream ois=new ObjectInputStream(new FileInputStream(file));
           qh=(QueryHistory) ois.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            
        }
        return qh;
    }
}
