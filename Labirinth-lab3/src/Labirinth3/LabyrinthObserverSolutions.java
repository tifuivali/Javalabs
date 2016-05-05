/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Labirinth3;

import com.sun.javafx.scene.traversal.Direction;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;


public class LabyrinthObserverSolutions implements LabyrinthObserver {
    
    List<Cell> cellsvisited;
    List<ExploreDirection> directions;
    List<List<ExploreDirection>> solutions;
    Labyrinth labyrinth;
    int curectIndex;
    int matrix[][];
    
    void removeVisitedCell(Cell visitedCell)
    {
        for(int i=0;i<cellsvisited.size();i++)
        {
            if(cellsvisited.get(i).getRow()==visitedCell.getRow()&&cellsvisited.get(i).getColummn()==visitedCell.getColummn())
                cellsvisited.remove(i);
        }
    }
    
    void removeRange(List<Cell> list,int start)
    {
        for(int i=start;i<list.size();i++)
        {
            list.remove(i);
        }
    }
    
    void removeRangeD(List<ExploreDirection> list,int start)
    {
        for(int i=start;i<list.size();i++)
        {
            list.remove(i);
        }
    }
     
    
    
    int exits(Cell cell)
    {
        for(int i=0;i<cellsvisited.size();i++)
        {
            if(cellsvisited.get(i).equals(cell))
                return i;
        }
        return -1;
    }
    
    
    
    public LabyrinthObserverSolutions(Labyrinth lab)
    {
        cellsvisited=new ArrayList<>();
        directions=new ArrayList<>();
        this.labyrinth=lab;
        solutions=new ArrayList<>();
        curectIndex=0;
    }
    boolean wasF=false;
    @Override
    public void processCell(Cell cell) 
    { 
      
       if((cell.getType()==CellType.freeCell||cell.getType()==CellType.start||cell.getType()==CellType.finish)&&!wasF)
       {
           int start=exits(cell);
           if(start>0)
           {
               cellsvisited.remove(cell);
           }
           else
           {
               cellsvisited.add(cell);
               
           }
       if(cell.getType()==CellType.finish)
       {
           wasF=true;
           
       }
       }
    }
    
    void writeSolution()
    {
        System.out.println("Best solution:");
        
        for(int j=0;j<labyrinth.getCollumnCount();j++)
        {
             System.out.print("--");
        }
        System.out.print("-");
        System.out.println();
        for(int i=0;i<labyrinth.getRowConunt();i++)
        {
           
            System.out.print("|");
            for(int j=0;j<labyrinth.getCollumnCount();j++)
            { boolean b=false;
               for(Cell cell:cellsvisited)
               {
                   if(cell.getColummn()==j&&cell.getRow()==i)
                   {
                       b=true;
                       System.out.print("X|");
                   }
               } 
               if(!b)
               if(i==labyrinth.getFinsishCell().getRow()&&j==labyrinth.getFinsishCell().getColummn())
               {
                   System.out.print("F|");
               }
               else
               if(i==labyrinth.getStartCell().getRow()&&j==labyrinth.getStartCell().getColummn())
               {
                   System.out.print("S|");
               }
               else System.out.print(" |");
              
            }
               
               
            System.out.println();
            for(int j=0;j<labyrinth.getCollumnCount();j++)
            {
                System.out.print("--");
            }
            System.out.print("-");
            System.out.println();
        }
    }

    @Override
    public void precessSolution() 
    {
        directions=new ArrayList<>();
        for(int i=1;i<cellsvisited.size();i++)
        {
            int r2=cellsvisited.get(i).getRow();
            int c2=cellsvisited.get(i).getColummn();
            int r1=cellsvisited.get(i-1).getRow();
            int c1=cellsvisited.get(i-1).getColummn();
            if(r2>r1)
                directions.add(ExploreDirection.bottom);
             if(r2<r1)
                directions.add(ExploreDirection.top);
             if(c2>c1)
                 directions.add(ExploreDirection.right);
             if(c2<c1)
                 directions.add(ExploreDirection.left);
            
           
        }
        solutions.add(directions);
        for(ExploreDirection dir:directions)
        {
            System.out.println(dir);
        }
        writeSolution();
    }
    
    void writeBestSol()
    {
        System.out.println("BEST SOl:");
        int mx=0;
        List<ExploreDirection> dirs;
        for(List<ExploreDirection> dir:solutions)
        {
            if(dir.size()>mx)
            {
                mx=dir.size();
                directions=dir;
            }
            
        }
        for(ExploreDirection d:directions)
        {
            System.out.println(d);
        }
        writeSolution();
        
    }
    
}
