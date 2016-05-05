/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audio.library.manager;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author tifui
 */
public class ChangeDirectoryComand implements Comand{
    
        
    private Path path_cutentDirectory;
    String[] args;

    
    
    public ChangeDirectoryComand(Path curentDir,String...args)
    {
       path_cutentDirectory=curentDir;
       this.args=args;
       
    }
    
   
    
    private void parseCd_Comand(String...args)
    {
       
           
               if(args.length>1)
               {
                 StringBuilder str_buBuilder=new StringBuilder();
                 for(int i=1;i<args.length;i++) 
                      str_buBuilder.append(args[i]+" ");
                 Path path_new_directory=Paths.get(str_buBuilder.toString().trim());
                 if(Files.exists(path_new_directory))
                 {
                     setPath_cutentDirectory(path_new_directory);
                 }
                 else System.out.println("Directory not found!");
               }
               else
               {
                   if(null!=getPath_cutentDirectory().getParent()) 
                   {
                       setPath_cutentDirectory(getPath_cutentDirectory().getParent());
                   } 
               }
              
               
    }

    @Override
    public void execute() throws ComandArgumentException {
        parseCd_Comand(args);
    }

    /**
     * @return the path_cutentDirectory
     */
    public Path getPath_cutentDirectory() {
        return path_cutentDirectory;
    }

    /**
     * @param path_cutentDirectory the path_cutentDirectory to set
     */
    public void setPath_cutentDirectory(Path path_cutentDirectory) {
        this.path_cutentDirectory = path_cutentDirectory;
    }
    
    
}
