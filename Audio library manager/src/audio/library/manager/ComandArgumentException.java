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
public class ComandArgumentException extends Exception
{
  public ComandArgumentException()
  {
      super();
  }
  
  public ComandArgumentException(String message)
  {
      super(message);
  }
}
