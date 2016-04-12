/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audio.library.manager;

import java.io.File;
import java.nio.file.Path;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author tifui
 */
public class FindCommnad implements Comand{
    
    Path path_cutentDirectory;
    String[] args;

    
    
    public FindCommnad(Path curentDir,String...args)
    {
       path_cutentDirectory=curentDir;
       this.args=args;
       
    }
    
    @Override
    public void execute() throws ComandArgumentException
    {
        parseFindComand(args);
    }
    
    
    private boolean findFile(String key,File direPath) throws SecurityException
    {
        boolean found=false;
        String p=".*?"+key.toLowerCase()+".*";
        Pattern patern=Pattern.compile(p);
        File[] folderFiles=direPath.listFiles();
        for(File file:folderFiles)
        {
            if(file.isDirectory())
            {
                File newFile=new File(direPath,file.getName());
                found=findFile(key, newFile);
            }
            else
            {
                Matcher match=patern.matcher(file.getName().toLowerCase());
                if(match.matches())
                {
                    File newFile=new File(direPath,file.getName());
                    System.out.println("Found:\n"+newFile);
                    return true;
                }
               
                    
            }
        }
        return found ;
           
    }
    
    private void parseFindComand(String...args) throws ComandArgumentException
    {
        if(args[1].equals("-k")||args[1].equals("-key"))
         {
           StringBuilder strBuild=new StringBuilder();             
           for(int i=2;i<args.length;i++)
           {
               strBuild.append(args[i]+" ");
           }
           String key=strBuild.toString().trim();
           try{
              if(!findFile(key,path_cutentDirectory.toFile()))
              {
                 System.out.println("Not found!");
              }
            }
            catch(SecurityException ex)
            {
                System.out.println("An error occured!");
            }
  
         }
        else throw new ComandArgumentException("Invalid Argumets");
    }
    
}
