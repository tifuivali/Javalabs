/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Labirinth3;

import java.util.Scanner;


public class LabyrinthSolverInteractive implements LabyrinthSolver 
{
    private Labyrinth labyrinth;
    private Cell currentCell;
    LabyrinthObserver solutionsObserver;
    LabyrinthObserver exploreObserver;
    
    public LabyrinthSolverInteractive(Labyrinth labyrinth)
    {
        this.labyrinth=labyrinth;
        currentCell=labyrinth.getStartCell();
        solutionsObserver=new LabyrinthObserverSolutions(labyrinth);
        exploreObserver=new LabyrinthObserverExploration(labyrinth);
    }

    @Override
    public Labyrinth getLabirynth() 
    {
        return labyrinth;
    }

    @Override
    public void setLabirynth(Labyrinth labirynth) 
    {
        this.labyrinth=labirynth;
    }
    
    boolean isInRowInterval(int row)
    {
        if(row>=0&&row<labyrinth.getRowConunt())
            return true;
        else return false;
    }
     boolean isInColumnInterval(int col)
    {
        if(col>=0&&col<labyrinth.getRowConunt())
            return true;
        else return false;
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
            
            currentCell=labyrinth.getCellAt(r, c);
            exploreObserver.processCell(currentCell);
            solutionsObserver.processCell(currentCell);
        }
        if(r==labyrinth.getFinsishCell().getRow()&&c==labyrinth.getFinsishCell().getColummn())
        {
            solutionsObserver.processCell(labyrinth.getFinsishCell());
            exploreObserver.precessSolution();
            solutionsObserver.precessSolution();
        }
        if(labyrinth.isWallAt(r, c))
        {
            exploreObserver.processCell(new Cell(r, c,CellType.wall));
        }
        if(labyrinth.getCellAt(r, c).getType()==labyrinth.getStartCell().getType())
        {
            exploreObserver.processCell(new Cell(r, c,CellType.start));
        }
        return null;
               
                
    }

    @Override
    public Cell getCurrentCell() 
    {
        return currentCell;
    }
    
    public void solve()
    {
        Cell cell;
        String message="Solve the labirynth..(for exit write exit)";
        String dir;
        Scanner scan=new Scanner(System.in);
        System.out.println(message);
        while(true)
        {
           
            ExploreDirection direction = null;
            System.out.println("Introduceti directia:");
            dir=scan.next();
            char d=dir.toCharArray()[0];
            switch(d)
            {
                case 'w':
                {
                    direction=ExploreDirection.top;
                    break;
                }
                case 's':
                {
                    direction=ExploreDirection.bottom;
                    break;
                }
                case 'd':
                {
                    direction=ExploreDirection.right;
                    break;
                }
                case 'a':
                {
                    direction=ExploreDirection.left;
                    break;
                }
                
            }
            if(dir.equals("exit"))
            {
                return;
            }
            nextCellToExplore(direction);    
        }
        
    }

    @Override
    public Cell nextCellToExplore(ExploreDirection dir, Cell curent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 



}
