/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package strategies;

import wordgame.Title;
import wordgame.WordGame;

/**
 *
 * @author tifui
 */
public interface Strategy {
    
    
    public String getWord(Title[] titles,WordGame game);
   
    
}
