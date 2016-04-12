/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audio.library.manager;

import java.io.IOException;

/**
 *
 * @author tifui
 */
public class HelpComand implements Comand{
    
   String[] args;
   public  HelpComand(String[] args)
   {
       this.args=args;
              
   }
    
   String message="Comands avaible: \n"+
                  "cd [directory path] -->change directory\n"+
                  "list [directory path] -->list songs from curent directory or specified directory\n"+
                  "play {-i|-index} {index} -->play song from an index\n"+
                  "info {-i|-index} {index} -->get info about song at index\n"+
                  "play {-f|-file} {file destination} -->play song \n"+
                  "info {-f|-file} {file destination} -->get info about song\n"+
                  "find {-k|-key} {keyword} -->find a file"+
                  "fav {-i|-index} {index} -->add song to favorite list from an index\n"+
                  "fav {-f|-file} {file destination} -->add song to favorite list \n"+
                  "report -->report favorite list to pdf\n"+
                  "remove_fav -->remove favorite list\n"+
                  "view {-r|-report} -->open reported favorite list ,pdf file\n "+
                  "view {-fl|favlist} -->print favorite list";
   private void parse(String[] args) throws ComandArgumentException
   {
       if(args.length>1)
       {
           throw  new ComandArgumentException("Too much argumnets!");
       }
   }

    @Override
    public void execute() throws IOException, IllegalArgumentException, ComandArgumentException {
        parse(args);
        System.out.println(message);
    }
   
   
                  
                  
    
}
