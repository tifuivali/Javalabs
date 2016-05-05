/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategies;

import Components.Player;
import dictionar.Vocabulary;
import java.util.stream.DoubleStream;
import javafx.scene.input.KeyCode;
import wordgame.Title;
import wordgame.WordGame;

/**
 *
 * @author tifuivali
 */
public class RandomlyRecursive implements Strategy{

    private Player player;
    private Vocabulary vocabulary;
    private String construction="";
    
    public  RandomlyRecursive(Vocabulary vocabulary,Player player)
    {
        this.vocabulary=vocabulary;
        this.player=player;
    }
    
    private boolean checkConstruction(String construction)
    {
        return vocabulary.contains(construction);
    }
    
    void back(String construction,WordGame game)
    {
          try {
                Thread.sleep(80);
            } catch (InterruptedException ex) {
                System.out.println("sleep");
            }
        if(game.getStoped())
            return;
        player.getLabel_curent_construction().setText(construction);
        if(checkConstruction(construction))
        { 
          this.construction=construction;
          return;
        }
        for (Title curentTitle : player.getCurentTitles()) {
            if (vocabulary.isPrefix(construction + curentTitle.toString())) {
                back(construction + curentTitle.toString(),game);
                if(checkConstruction(this.construction))
                    return;
            }
        }
        
    }
    @Override
    public String getWord(Title[] titles, WordGame game) {
      
        construction="";
       if(game.getStoped())
           return ".";
      while(!checkConstruction(construction)&&!game.getStoped())
      {
      for(Title title:player.getCurentTitles())
      {
          player.getLabel_curent_construction().setText(construction);
          if(checkConstruction(construction))
              return  construction;
          if(vocabulary.isPrefix(construction+title.toString()))
          {
             back(construction+title.toString(),game);
             if(checkConstruction(construction))
                 break;
          }
      }
      }
      return this.construction;
   
        
    }
    
}
