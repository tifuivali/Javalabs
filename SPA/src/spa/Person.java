/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spa;

import com.sun.org.apache.xpath.internal.operations.Equals;

/**
 * Person Class
 * @author tifui
 */
abstract class Person {
    
    
    String nume;
    private String prenume;
    private String email;
    private String telefon;
    
    /**
     * 
     * @return the string Name+Prenoum.
     */
    @Override
    public String toString()
    {
        return this.getNume()+" "+this.getPrenume();
    }

    /**
     * Get the name of person.
     * @return the name
     */
    public String getNume() {
        return nume;
    }

    /**
     * Set name.
     * @param nume the name to set
     */
    public void setNume(String nume) {
        this.nume = nume;
    }

    /**
     * Get prenoum.
     * @return the prenoum
     */
    public String getPrenume() {
        return prenume;
    }

    /**
     * Set prenoum.
     * @param prenume the prenume to set
     */
    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    /**
     * Get person email.S
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set person email.
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get person phone number.
     * @return the telefon
     */
    public String getTelefon() {
        return telefon;
    }

    /**
     * Set person phone number.
     * @param telefon the telefon to set
     */
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
    
    /**
     * abstract function isFree
     * 
     */
    
    abstract boolean isFree();

}
