/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiuneangajati;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tifui
 */
public class Angajat implements Serializable
{
    
    private String functie;
    private String nume;
    private String prenume;
    private String adresa;
    private String telefon;
    private float salariu;
    
    public Angajat(String functie,String nume,String prenume)
    {
        this.functie=functie;
        this.nume=nume;
        this.prenume=prenume;
    }
    
    public Angajat()
    {
     this.adresa="";
     this.functie="";
     this.nume="";
     this.prenume="";
     this.salariu=0;
     this.telefon="";
    }
    
    public String getFunctie()
    {
        return functie;
    }
    
    public String getNume()
    {
        return nume;
    }
    
    public String getPrenume()
    {
        return prenume;
    }
    
    public String getAdresa()
    {
        return adresa;
    }
    
    public String getTelefon()
    {
        return telefon;
                
    }
    
    public float getSalariu()
    {
        return salariu;
                
    }
    
    public String getNumePrenume()
    {
        return getNume()+" "+getPrenume();
    }
            
    public void setFunctie(String functie)
    {
        this.functie=functie;
    }
    
    public void setNume(String nume)
    {
        this.nume=nume;
    }
    
    public void setAdresa(String adresa)
    {
        this.adresa=adresa;
    }
    
    public void setSalariu(float salariu)
    {
        this.salariu=salariu;
    }
    
    public void setPrenume(String prenume)
    {
        this.prenume=prenume;
    }
    
    public static void saveObject(ObjectOutputStream stream)
    {
        try
        {
          stream.defaultWriteObject();
        }
        catch(IOException err)
        {
            System.out.println(err.toString());
        }
    }
    
    public  String[] getDetaliiAngajat()
    {
        String[] detalii=new String[6];
        detalii[0]=getNume();
        detalii[1]=getPrenume();
        detalii[2]=getFunctie();
        detalii[3]=getAdresa();
        detalii[4]=getTelefon();
        detalii[5]=String.valueOf(salariu);
        return detalii;
    }
    
    public static void restoreObject(ObjectInputStream stream) 
    {
        try
        {
            stream.defaultReadObject();
        }
        catch (IOException | ClassNotFoundException exc)
        {
            System.err.println(exc.toString());
        }
    }
    
             
   
}
