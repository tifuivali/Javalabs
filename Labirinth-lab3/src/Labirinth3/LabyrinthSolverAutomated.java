/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Labirinth3;

import java.util.ArrayList;
import java.util.List;


public class LabyrinthSolverAutomated implements LabyrinthSolver {
    
    
    private Labyrinth labyrinth;
    private Labyrinth savedLab;
    Cell currentCell;
    LabyrinthObserverSolutions solutionsObserver;
    List<ExploreDirection> allDirections;
    LabyrinthObserver exploreObserver;
    List<Cell> visited;
    public LabyrinthSolverAutomated(Labyrinth labyrinth)
    {
        this.labyrinth=labyrinth;
        savedLab=labyrinth.getClone();
        currentCell=labyrinth.getStartCell();
        solutionsObserver=new LabyrinthObserverSolutions(savedLab);
        allDirections=new ArrayList<>();
        visited=new ArrayList<>();
        exploreObserver=new LabyrinthObserverExploration(savedLab);
    }

    @Override
    public Labyrinth getLabirynth() 
    {
        return this.labyrinth;
    }

    @Override
    public void setLabirynth(Labyrinth labirynth) 
    {
        this.labyrinth=labirynth;
    }


    @Override
    public Cell getCurrentCell() 
    {
        return this.currentCell;
    }
   
    void walkToDirection(Cell curent)
    {
      
       if(curent.getType()==CellType.wall)
           return;
       exploreObserver.processCell(curent);
       solutionsObserver.processCell(curent);
       if(curent.getType()==CellType.finish)
       {
           exploreObserver.precessSolution();
           solutionsObserver.precessSolution();
           return;
       }
           
       int count=0;
       ExploreDirection singleDir=null;
       for(ExploreDirection dir:allDirections)
       {
           if(nextCellToExplore(dir, curent).getType()==CellType.freeCell||
                 nextCellToExplore(dir, curent).getType()==CellType.finish)
           { 
               singleDir=dir;
               count++;
           }
       }
       if(count==1)
       { 
           if(curent.getType()!=CellType.start)
           {
           curent.setType(CellType.visited);
           labyrinth.setCell(curent);
           }
           if(singleDir!=null)
           walkToDirection(nextCellToExplore(singleDir,curent));
           
       }
       else
       {
           for(ExploreDirection dir:allDirections)
           {
               if(nextCellToExplore(dir, curent).getType()==CellType.freeCell||
                 nextCellToExplore(dir, curent).getType()==CellType.finish)
               {
                    if(curent.getType()!=CellType.start)
                    {
                       curent.setType(CellType.visited);
                      // solutionsObserver.removeVisitedCell(currentCell);
                       labyrinth.setCell(curent);
                    }
                   walkToDirection(nextCellToExplore(dir,curent));
               }
           }
       }
        
    }
   
    @Override
    public void solve()
    {
        allDirections=new ArrayList<>();
        allDirections.add(ExploreDirection.top);
        allDirections.add(ExploreDirection.bottom);
        allDirections.add(ExploreDirection.left);
        allDirections.add(ExploreDirection.right);
       
        walkToDirection(currentCell);
        

        
    }
    
    


    @Override
    public Cell nextCellToExplore(ExploreDirection direction) 
    {
        int r=currentCell.getRow();
        int c=currentCell.getColummn();
        switch(direction)
        {
            case left:
            {
              
                    c=c-1;
                    break;
                
            }
            
            case right:
            {
                c=c+1;
                break;
            }
            case top:
            {
                r=r-1;
                break;
            }
            case bottom:
            {
               r=r+1;
               break;
            }
            
        }
        
        
        if(labyrinth.isFreeAt(r, c))
        { 
            exploreObserver.processCell(currentCell);
            solutionsObserver.processCell(currentCell);
            return labyrinth.getCellAt(r, c);
        }
        if(r==labyrinth.getFinsishCell().getRow()&&c==labyrinth.getFinsishCell().getColummn())
        {
            solutionsObserver.processCell(labyrinth.getFinsishCell());
            exploreObserver.precessSolution();
            solutionsObserver.precessSolution();
            return labyrinth.getCellAt(r, c);
        }
        if(labyrinth.isWallAt(r, c))
        {
            exploreObserver.processCell(new Cell(r, c,CellType.wall));
            return labyrinth.getCellAt(r, c);
        }
        if(labyrinth.getCellAt(r, c).getType()==labyrinth.getStartCell().getType())
        {
            exploreObserver.processCell(new Cell(r, c,CellType.start));
        }
        return labyrinth.getCellAt(r, c);
    }
    
    
    @Override
    public Cell nextCellToExplore(ExploreDirection direction,Cell currentCell) 
    {
        int r=currentCell.getRow();
        int c=currentCell.getColummn();
        switch(direction)
        {
            case left:
            {
              
                    c=c-1;
                    break;
                
            }
            
            case right:
            {
                c=c+1;
                break;
            }
            case top:
            {
                r=r-1;
                break;
            }
            case bottom:
            {
               r=r+1;
               break;
            }
            
        }
        
     
        return labyrinth.getCellAt(r, c);
    }
    
}
