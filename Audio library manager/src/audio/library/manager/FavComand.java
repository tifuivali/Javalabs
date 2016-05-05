/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audio.library.manager;

import static audio.library.manager.AudioLibraryManager.favDirectory;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author tifui
 */
public class FavComand implements Comand{
    
    Path path_cutentDirectory;
    String[] args;
    List<File> curent_listed_files;
    List<File> prefred_Files;
    
    
    public FavComand(Path curentDir,List<File> recentlyListedFiels,List<File> preferdList,String[] args)
    {
       path_cutentDirectory=curentDir;
       this.args=args;
       this.curent_listed_files=recentlyListedFiels;
       this.prefred_Files=preferdList;
       
    }
    
    private void parseFav_Comand(String ... args) throws ComandArgumentException,IOException,IllegalArgumentException
    {
        
        if(args.length<2)
            throw new ComandArgumentException("Number of arguments too late.");
                
            if(args[1].equals("-i")||args[1].equals("-index"))       
            {
               int index=0; 
               try
               {
                index=Integer.parseInt(args[2]);
                favComand(curent_listed_files.get(index-1));
               }
               catch(NumberFormatException|IndexOutOfBoundsException ex)
               {
                   throw new ComandArgumentException("Not valid index!");
               }
               
               return;
            }
            
            if(args[1].equals("-f")||args[1].equals("-file"))
            {
                StringBuilder strBuild=new StringBuilder();
                
                for(int i=2;i<args.length;i++)
                {
                    strBuild.append(args[i]+" ");
                }
                String str_path=strBuild.toString();
               if(Paths.get(str_path).getParent()==null)
               {
                   File file1=new File(path_cutentDirectory.toString());
                   Path filePlay=new File(file1,str_path.trim()).toPath();
                   favComand(filePlay.toFile());
               }
               else
               {
                  Path path_new_directory=Paths.get(str_path);
                  if(Files.exists(path_new_directory))
                  {
                     favComand(path_new_directory.toFile());
                  }
                  else System.out.println("File not found!");
               }
               return;
            }
            
            throw new ComandArgumentException("Arguments error!");
        
    }
    
     private void favComand(File file)
    {
        prefred_Files.add(file);
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
     public void execute() throws ComandArgumentException, IOException
     {
         parseFav_Comand(args);
     }
    
}
