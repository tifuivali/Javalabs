/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audio.library.manager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.List;

/**
 *
 * @author tifui
 */
public class RemoveFavComand implements Comand{
    
    String[] args;
    String favDirectory;
    List<File> prefred_Files;
    
    
    public RemoveFavComand(List<File> preferdList,String favDir,String[] args)
    {
       this.args=args;
       this.prefred_Files=preferdList;
       this.favDirectory=favDir;
       
    }
    
    
        public  void parseRemove_fav(String...args) throws ComandArgumentException
    {
        if(args.length>1)
        {
            throw new ComandArgumentException("Not valid argumets!");
        }
        delete_Comand();
        
    }
    
      public void delete_Comand()
        {
           prefred_Files.clear();
           try(ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream(new File(favDirectory,"fav.ser")))) 
            { 
              out.writeObject(prefred_Files);
            } 
            catch (IOException ex)
            {
              System.out.println(ex.toString());
            }
        }

    @Override
    public void execute() throws IOException, IllegalArgumentException, ComandArgumentException {
        parseRemove_fav(args);
    }
    
}
