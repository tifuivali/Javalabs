/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Labirinth3;

/**
 *
 * @author tifui
 */
public interface LabyrinthSolver {
    
    Labyrinth getLabirynth();
    void setLabirynth(Labyrinth labirynth);
    Cell nextCellToExplore(ExploreDirection direction);
    Cell nextCellToExplore(ExploreDirection dir,Cell curent);
    Cell getCurrentCell();
    void solve();
    
    
}
