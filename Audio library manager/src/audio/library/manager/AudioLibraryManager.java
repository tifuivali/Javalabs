/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audio.library.manager;

import java.io.File;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.FilenameUtils;
import java.awt.Desktop;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import net.sf.dynamicreports.report.exception.DRException;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.audio.AudioParser;
import org.apache.tika.sax.BodyContentHandler;

/**
 *
 * @author tifui
 */
public class AudioLibraryManager implements Serializable{

 
    static String favDirectory;
    Path path_cutentDirectory;
    List<File> curent_listed_files;
    List<String> comands_history;
    List<File> prefred_Files;
    
    
    public AudioLibraryManager()
    {
       // curentDirectory=Paths.get("").toString();
        favDirectory=System.getProperty("user.dir");
        path_cutentDirectory=Paths.get(favDirectory);
        comands_history=new ArrayList<>();
        curent_listed_files=new ArrayList<>();
        prefred_Files=new ArrayList<>();
    }
    
            
    
  
    
    /**
     * Parse the input command.
     * @param comand
     * @throws ComandNotFound 
     * @throws audio.library.manager.ComandArgumentException 
     * @throws java.io.IOException 
     */
    private void parseCommand(String comand) throws ComandNotFound, ComandArgumentException, IOException,IllegalArgumentException
    {
        
        if(comand.equals(""))
        {
            return;
        }
        
        String[] args=comand.split(" ");
        String p="cd|list|exit|play|info|find|fav|report|view|remove_fav|help";
        Pattern patern=Pattern.compile(p);
        
        Matcher matcher=patern.matcher(args[0]);
        if(matcher.matches())
        {
           //comand cd
            if(args[0].equals("cd"))
            {
                ChangeDirectoryComand cmd=new ChangeDirectoryComand(path_cutentDirectory, args);
                cmd.execute();
                if(cmd.getPath_cutentDirectory()!=null)
                {
                    path_cutentDirectory=cmd.getPath_cutentDirectory();
                }
                
            }
            
            if(args[0].equals("list"))
            {
               Comand cmd=new ListComand(path_cutentDirectory, curent_listed_files, args);
               cmd.execute();
            }
            
            if(args[0].equals("play"))
            {
                Comand cmd=new PlayComand(path_cutentDirectory, curent_listed_files, args);
                cmd.execute();
            }
            
            if(args[0].equals("info"))
            {
                Comand cmd=new InfoComand(path_cutentDirectory, curent_listed_files, args);
                cmd.execute();
            }
            if(args[0].equals("find"))
            {
               Comand cmd=new FindCommnad(path_cutentDirectory, args);
               cmd.execute();
            }
            
            if(args[0].equals("fav"))
            {
                Comand cmd=new FavComand(path_cutentDirectory, curent_listed_files, prefred_Files, args);
                cmd.execute();
            }
            if(args[0].equals("report"))
            {
                Comand cmd=new ReportComand(path_cutentDirectory, curent_listed_files, prefred_Files, args);
                cmd.execute();
            }
             if(args[0].equals("remove_fav"))
            {
                Comand cmd=new RemoveFavComand(prefred_Files,favDirectory, args);
                cmd.execute();
            }
              if(args[0].equals("view"))
            {
                Comand cmd=new ViewComand(path_cutentDirectory, curent_listed_files, prefred_Files, args);
                cmd.execute();
            }
              if(args[0].equals("help"))
              {
                  Comand cmd=new HelpComand(args);
                  cmd.execute();
              }
          
            
               
           
            
        }
        else throw new ComandNotFound("Comand not found!");

    }
     
    public void shell() 
    {
        System.out.println("Audio Library Manager v1.0 ");
        String comand = "...";
        try(ObjectInputStream input=new ObjectInputStream(new FileInputStream(new File(favDirectory,"fav.ser"))))
        {
            try 
            {
                prefred_Files=(List<File>) input.readObject();
            } catch (ClassNotFoundException ex) {
                
                System.out.println("Favorite List load failed!");
            }
        }
        catch(IOException ex)
        {
            
        }
        Scanner scan=new Scanner(System.in);
        while(!comand.toLowerCase().equals("exit"))
        {
            System.out.print(path_cutentDirectory+"@:");
            comand=scan.nextLine();
            try {
                parseCommand(comand);
            } catch (ComandNotFound ex) {
                System.out.println(ex.getMessage());
            } catch (ComandArgumentException | IOException | IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
        
    }
    
     /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
         AudioLibraryManager audioLibrarymanager=new AudioLibraryManager();
         audioLibrarymanager.shell();
         
    }
    
}
