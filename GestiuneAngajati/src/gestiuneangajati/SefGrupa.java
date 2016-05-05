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
public class SefGrupa extends Angajat
{
 
    private List<SefEchipa> sefiEchipaSubordinati;
    
    public SefGrupa()
    {
        setFunctie("SefGrupa");
        sefiEchipaSubordinati=new ArrayList<>();
    }
    
    public SefGrupa(String nume,String prenume)
    {
        setFunctie("SefGrupa");
        setNume(nume);
        setPrenume(prenume);
        sefiEchipaSubordinati=new ArrayList<>();
    }
    
    public SefGrupa(String nume,String prenume,float salariu)
    {
        setFunctie("SefGrupa");
        setNume(nume);
        setPrenume(prenume);
        setSalariu(salariu);
        sefiEchipaSubordinati=new ArrayList<>();
    }
    
    public void addSefEchipa(SefEchipa sefEchipa)
    {
        sefiEchipaSubordinati.add(sefEchipa);
    }
    
    public void addSefiEclipa(SefEchipa...sefiEchipa)
    {
        for(SefEchipa sefEchipa:sefiEchipa)
        {
            addSefEchipa(sefEchipa);
        }
    }
    
    public List<SefEchipa> getSefiEchipe()
    {
        return sefiEchipaSubordinati;
    }
    
  
    
    
}
