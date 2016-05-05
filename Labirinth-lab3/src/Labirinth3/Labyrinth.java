package Labirinth3;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author student
 */
public interface Labyrinth {

    
    int getRowConunt();
    int getCollumnCount();
    boolean isFreeAt(int row,int collumn);
    boolean isWallAt(int row,int collumn);
    Cell getStartCell();
    Cell getFinsishCell();
    Cell getCellAt(int row,int collumn);
    void setCell(Cell cell);
    Labyrinth getClone();
    
    
}
