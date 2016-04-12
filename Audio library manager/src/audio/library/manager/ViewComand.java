/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audio.library.manager;

import static audio.library.manager.AudioLibraryManager.favDirectory;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 *
 * @author tifui
 */
public class ViewComand  implements Comand{
    
    Path path_cutentDirectory;
    String[] args;
    List<File> curent_listed_files;
    List<File> prefred_Files;
    
    
    public ViewComand(Path curentDir,List<File> recentlyListedFiels,List<File> preferdList,String[] args)
    {
       path_cutentDirectory=curentDir;
       this.args=args;
       this.curent_listed_files=recentlyListedFiels;
       this.prefred_Files=preferdList;
       
    }
    
      private void comandViewReport() throws IOException
      {
          Desktop.getDesktop().open(new File(favDirectory,"report.pdf"));
      }
      
      void parse_ViewFavList(String...args) throws ComandArgumentException, IOException
      {
          if(args.length!=2)
          {
              throw  new ComandArgumentException("Not valid argumets!");
          }
         if(args[1].equals("-fl")||args[1].equals("-favlist"))
        {
            comandViewFavList();
        }
        else
        {
           throw new ComandArgumentException("Not valid argumets!");
        }
      }
      
       void comandViewFavList()
      {
          System.out.println("Favorite List:");
          for(File file:prefred_Files)
          {
              System.out.println(file.getName()+"   "+file);
          }
      }
       
     void parseViewReport(String...args) throws ComandArgumentException, IOException
    {
        if(args.length!=2)
        {
            throw new ComandArgumentException("Not valid argumets!");
        }
        if(args[1].equals("-r")||args[1].equals("-report"))
        {
            comandViewReport();
        }
        else
        {
           throw new ComandArgumentException("Not valid argumets!");
        }
        
        
    
    
}

    @Override
    public void execute() throws IOException, IllegalArgumentException, ComandArgumentException {
        try{
        parseViewReport(args);  
        }
        catch(ComandArgumentException|IOException ex)
        {
            System.out.println(ex.getMessage());
        }
        parse_ViewFavList(args);
    }
}
