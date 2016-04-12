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
public class SefEchipa extends Angajat
{
  private List<Programator> programatoriSubordonati;
  private List<Tester> testeriSubordonati;
  
  public SefEchipa()
  {
      setFunctie("SefEchipa");
      programatoriSubordonati=new ArrayList<>();
      testeriSubordonati=new ArrayList<>();
      
  }
  
  public SefEchipa(String nume,String prenume)
  {
      setFunctie("SefEchipa");
      setNume(nume);
      setPrenume(prenume);
      programatoriSubordonati=new ArrayList<>();
      testeriSubordonati=new ArrayList<>();
  }
  
  
    public SefEchipa(String nume,String prenume,float salariu)
  {
      setFunctie("SefEchipa");
      setNume(nume);
      setPrenume(prenume);
      setSalariu(salariu);
      programatoriSubordonati=new ArrayList<>();
      testeriSubordonati=new ArrayList<>();
  }
    
   public void addProgramator(Programator programator)
   {
       programatoriSubordonati.add(programator);
   }
   
   public void addTester(Tester tester)
   {
       testeriSubordonati.add(tester);
   }
   
   public void addTesteri(Tester...testeri)
   {
       for(Tester tester:testeri)
       {
           addTester(tester);
       }
   }
   
   public void addProgramatori(Programator...programatori)
   {
       for(Programator programator:programatori)
       {
           addProgramator(programator);
       }
   }
   
   public List<Programator> getProgramatoriSubordonati()
   {
       return programatoriSubordonati;
   }
   
   public List<Tester> getTesteriSubordonati()
   {
       return testeriSubordonati;
   }
   
   
  
  
  
}
