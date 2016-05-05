/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audio.library.manager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author tifui
 */
public class ListComand implements Comand{
    
    
    Path path_cutentDirectory;
    String[] args;
    List<File> curent_listed_files;
    
    
    public ListComand(Path curentDir,List<File> recentlyListedFiels,String[] args)
    {
       path_cutentDirectory=curentDir;
       this.args=args;
       this.curent_listed_files=recentlyListedFiels;
       
    }
    
     public void parseList_Comand(String...args)
    {
        Path directory_toList;
        directory_toList=path_cutentDirectory;
        if(args.length>1)
        {
            String path="";
            for(int i=1;i<args.length;i++)
                path+=args[i]+" ";
            Path path_new_directory=Paths.get(path);
            if(Files.exists(path_new_directory))
            {
                directory_toList=path_new_directory;
            }
            else System.out.println("Directory not found!");
        }
        
        
        File[] files=directory_toList.toFile().listFiles();
        boolean b_exist_audio_files=false;
        int i=1;
        curent_listed_files.clear();
        for(File file:files)
        {
           if(isAudioFile(file))
           {
              System.out.println(file.getName()+" -->"+i);
              curent_listed_files.add(file);
              b_exist_audio_files=true;
              i++;
           }
           
        }
         if(!b_exist_audio_files)
        {
            System.out.println("Music files not found on this directory!");
        }
        
    }
    
    private boolean isAudioFile(File file)
    {
        String extension=FilenameUtils.getExtension(file.getName());
        
        return extension.equals("mp3")||
                extension.equals("wav")||
                extension.equals("flac")||
                extension.equals("ac3");
                
    }

    @Override
    public void execute() throws IOException, IllegalArgumentException, ComandArgumentException {
        parseList_Comand(args);
    }
    
}
