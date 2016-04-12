/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiuneangajati;

/**
 *
 * @author tifui
 */
public class Tester extends Angajat
{
 
    private String tipTestareCunoscuta;
    
    public Tester()
    {
        setFunctie("Tester");
    }
    
    public Tester(String nume,String prenume)
    {
        setFunctie("Tester");
        setNume(nume);
        setPrenume(prenume);
    }
    
    public Tester(String nume,String prenume,float salariu)
    {
        setFunctie("Tester");
        setNume(nume);
        setPrenume(prenume);
        setSalariu(salariu);
    }
    
    public void setTipTestare(String tip)
    {
        this.tipTestareCunoscuta=tip;
    }
    
    public String getTipTestareCunoscuta()
    {
        return tipTestareCunoscuta;
    }
    
    
}
