/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audio.library.manager;

/**
 *
 * @author tifui
 */
public class ComandNotFound extends Exception{
    
    public ComandNotFound()
    {
        super();
    }
    
    public  ComandNotFound(String message)
    {
        super(message);
    }
    
}
