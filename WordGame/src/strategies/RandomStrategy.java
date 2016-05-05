/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategies;

import Components.Player;
import dictionar.Vocabulary;
import wordgame.Title;
import wordgame.WordGame;

/**
 *
 * @author tifuivali
 */
public class RandomStrategy implements Strategy{

    private Player player;
    private Vocabulary vocabulary;
    
    public RandomStrategy(Player player,Vocabulary vocabulary)
    {
        this.player=player;
        this.vocabulary=vocabulary;
    }
    @Override
    public String getWord(Title[] titles, WordGame game) {
        
     return null;
    }
    
}
