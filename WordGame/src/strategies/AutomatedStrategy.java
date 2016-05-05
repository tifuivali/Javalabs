/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategies;

import Components.Player;
import dictionar.Vocabulary;
import java.util.Arrays;
import java.util.Random;
import wordgame.Title;
import wordgame.WordGame;

/**
 *
 * @author tifui
 */
public class AutomatedStrategy implements Strategy{

    private final Vocabulary vocabulary;
    private final Player player;
    private StringBuilder curent_Construction=new StringBuilder();
    /**
     * Creaza o noua strategie de alegere a cuvantului.
     * @param vocabulary dictionarul de cuvinte.
     * @param player Playerul care adopta strategia.
     */
    public AutomatedStrategy(Vocabulary vocabulary,Player player)
    {
        this.player=player;
        this.vocabulary=vocabulary;
    }
    
    @Override
    public String getWord(Title[] titles,WordGame game) {
     curent_Construction=new StringBuilder();
     int[] pozitii=new int[titles.length];
     Arrays.fill(pozitii, 0);
     Random random=new Random();
     
     int k=0;
     while(!check(curent_Construction.toString())&&!game.getStoped())
     {
         int r=random.nextInt(titles.length);
         while(!vocabulary.isPrefix(curent_Construction.toString()+titles[r])&&!game.getStoped())
         {
             
             player.getLabel_curent_construction().setText(curent_Construction.toString()+titles[r]);
             k++;
             r=random.nextInt(titles.length);
             if(k>100)
             {
                 curent_Construction.delete(curent_Construction.length()-1, curent_Construction.length());
                 k=0;
                 
                 curent_Construction.append(titles[new Random().nextInt(titles.length)]);
             }
         }
         player.getLabel_curent_construction().setText(curent_Construction.toString()+titles[r]);
         player.setCurentWord(curent_Construction.toString());
         curent_Construction.append(titles[r]);
         
     }
      
    
      
      return curent_Construction.toString();
       
    }
    
    
    private boolean check(String word)
    {
        return vocabulary.contains(word);
    }

 
    
    
}
