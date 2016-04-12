/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiuneangajati;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tifui
 */
public class Programator extends Angajat
{
    
    List<String> limbajeCunoscute;
   
   public Programator()
   {
      this.setFunctie("Programator");
      limbajeCunoscute=new ArrayList<>();
   }
   
   public Programator(String nume,String prenume)
   {
       this.setFunctie("Programator");
       this.setNume(nume);
       this.setPrenume(prenume);
       limbajeCunoscute=new ArrayList<>();
   }
   
   public void addLimbajCunoscut(String limbaj)
   {
       limbajeCunoscute.add(limbaj);
   }
   
   public void addLimbajeCunoscute(String...limbaj)
   {
      for(String limb:limbaj)
      {
          addLimbajCunoscut(limb);
      }
   }
   
   public List<String> getLimbajeCunoscute()
   {
       return this.limbajeCunoscute;
   }
   
   
}
