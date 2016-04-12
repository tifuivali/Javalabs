/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiuneangajati;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tifui
 */
public class Manager extends Angajat
{
   private List<SefGrupa> sefiGrupa;
   private List<Angajat> angajati;   
   
   public Manager()
   {
       setFunctie("Manager");
       sefiGrupa=new ArrayList<>();
   }
   
   public Manager(String nume,String prenume)
   {
       setFunctie("Manager");
       setNume(nume);
       setPrenume(prenume);
       sefiGrupa=new ArrayList<>();
   }
   
   public Manager(String nume,String prenume,float salariu)
   {
       setFunctie("Manager");
       setNume(nume);
       setPrenume(prenume);
       setSalariu(salariu);
       sefiGrupa=new ArrayList<>();
   }
   
   public void addSefEchipa(SefGrupa angajat)
   {
       sefiGrupa.add(angajat);
   }
   
   public void addAngajat(Angajat angajat)
   {
       angajati.add(angajat);
   }
   
   public void addSefiEchipe(SefGrupa...sefiEchipa)
   {
       for(SefGrupa sef:sefiEchipa)
       {
           addSefEchipa(sef);
       }
   }
   
   public List<SefGrupa> getSefiGrupe()
   {
       return  sefiGrupa;
   }
   
   public List<Angajat> getAngajati()
   {
       return angajati;
   }
}
