/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audio.library.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;

/**
 *
 * @author tifui
 */
public class InfoComand implements Comand{
     
    
    Path path_cutentDirectory;
    String[] args;
    List<File> curent_listed_files;
    
    
    public InfoComand(Path curentDir,List<File> recentlyListedFiels,String[] args)
    {
       path_cutentDirectory=curentDir;
       this.args=args;
       this.curent_listed_files=recentlyListedFiels;
       
    }
    
    private void parseInfo_comand(String...args) throws ComandArgumentException, IOException
    {
          if(args.length<2)
            throw new ComandArgumentException("Number of arguments too late.");
                
            if(args[1].equals("-i")||args[1].equals("-index"))       
            {
               int index=0; 
               String info="";
               try
               {
                index=Integer.parseInt(args[2]);
                WriteMetadataInformation(curent_listed_files.get(index-1));
               }
               catch(NumberFormatException|IndexOutOfBoundsException ex)
               {
                   throw new ComandArgumentException("Not valid index!");
               }
              catch (org.xml.sax.SAXException | TikaException ex) {
                  System.out.println("Error getting info!");
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
                   File filePlay=new File(file1,str_path.trim());
                    try {
                        WriteMetadataInformation(filePlay);
                    } catch (org.xml.sax.SAXException | TikaException ex) {
                        System.out.println("Error getting info!");
                    }
               }
               else
               {
                  Path path_new_directory=Paths.get(str_path);
                  if(Files.exists(path_new_directory))
                  {
                      try {
                          WriteMetadataInformation(path_new_directory.toFile());
                      } catch (org.xml.sax.SAXException | TikaException ex) {
                          System.out.println("Error getting info!");
                      }
                  }
                  else System.out.println("File not found!");
               }
               return;
            }
            
            throw new ComandArgumentException("Arguments error!");
    }
    
    private void WriteMetadataInformation(File file) throws FileNotFoundException, IOException, org.xml.sax.SAXException, TikaException
    {
      //Parser method parameters
      Parser parser =new AutoDetectParser();
      BodyContentHandler handler = new BodyContentHandler();
      Metadata metadata = new Metadata();
      FileInputStream inputstream = new FileInputStream(file);
      ParseContext context = new ParseContext();
   
      parser.parse(inputstream, handler, metadata, context);
      //System.out.println(handler.toString());

      //getting the list of all meta data elements 
      String[] metadataNames = metadata.names();

      for(String name : metadataNames) {		        
         System.out.println(name + ": " + metadata.get(name));
      }
    }

    @Override
    public void execute() throws IOException, IllegalArgumentException, ComandArgumentException {
        parseInfo_comand(args);
    }
    
}
