/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiuneangajati;

import com.sun.corba.se.impl.io.IIOPInputStream;
import com.sun.corba.se.impl.io.IIOPOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tifui
 */
public class GestiuneAngajati implements Serializable{

    /**
     * @param args the command line arguments
     */
    
    private List<Manager> manageri;
    public String CaleFiserSerializat;
    
    
    public GestiuneAngajati()
    {
       manageri=new ArrayList<>();
    }
    
    public void addManager(Manager manager)
    {
        manageri.add(manager);
    }
    
    public void setCalefisierSerializat(String cale)
    {
        CaleFiserSerializat=cale;
    }
    
    public void addAngajat(Angajat angajat)
    {
        manageri=new ArrayList<>();
    }
    
    public List<Manager> getManageri()
    {
        return manageri;
    }
    
    
    public void SalvareAngajati()
    {
        System.out.println("Se salveaza..");
      
      if(CaleFiserSerializat==null)  
       CaleFiserSerializat="//home//tifui//Descărcări//IP//save.ser";
     
          try {
              ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(CaleFiserSerializat));
              out.writeObject(this);
          } catch (IOException ex) {
              System.out.println(ex.toString());
          }
          
        
    }
    
    public static GestiuneAngajati GetAngajatiFromSerializedFile(String CaleFisierserializat)
    {
         System.out.println("Se restaureaza:..");
         
       
        try{
            ObjectInputStream in=new ObjectInputStream(new FileInputStream(CaleFisierserializat));
            return (GestiuneAngajati) in.readObject();
                    
           }
        catch (IOException | ClassNotFoundException ex)
        {
            System.out.println(ex.toString());
        } 
        return null;
    }
    
   
    
    public void afisareAngajat(Angajat angajat)
    {
        System.out.println("nume:"+angajat.getNume());
        System.out.println("prenume:"+angajat.getPrenume());
    
    }
    
   
    
   
 
    
    public static void main(String[] args) 
    {

    }


    
    
    
}
