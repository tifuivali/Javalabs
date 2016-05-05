/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Labirinth3;


public class LabyrinthObserverExploration implements LabyrinthObserver {
    

    private Labyrinth labyrinth;
    
    public LabyrinthObserverExploration(Labyrinth lab)
    {
        this.labyrinth=lab;
    }
    
    
    void writeCurentLab(Cell cell)
    {
        System.out.println("Curent position:");
        int r=cell.getRow();
        int c=cell.getColummn();
        for(int j=0;j<labyrinth.getCollumnCount();j++)
        {
             System.out.print("--");
        }
        System.out.print("-");
        System.out.println();
        for(int i=0;i<labyrinth.getRowConunt();i++)
        {
           if(labyrinth.getStartCell()==null)
                System.out.println("NULLLLL");
            System.out.print("|");
            for(int j=0;j<labyrinth.getCollumnCount();j++)
            {
               if(i==labyrinth.getFinsishCell().getRow()&&j==labyrinth.getFinsishCell().getColummn())
               {
                   System.out.print("F|");
               }
               else    
               if(i==labyrinth.getStartCell().getRow()&&j==labyrinth.getStartCell().getColummn())
               {
                   System.out.print("S|");
               }
               else
               if(i==r&&j==c)
                {
                    System.out.print("X|");
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
    public void processCell(Cell cell) 
    {
       // System.out.println("Row:"+cell.getRow()+" Collumn:"+cell.getColummn());
        if(cell.getType()==CellType.freeCell)
        {
            //System.out.println("Ok! Free Cell!");
            writeCurentLab(cell);
        }
        if(cell.getType()==CellType.wall)
        {
            System.out.println("Wall!");
        }
        if(cell.getType()==CellType.start)
        {
            System.out.println("Ok! Start Pozition!");
        }
        
    }

    @Override
    public void precessSolution() 
    {
        System.out.println("You win! Arived to destination!");   
    }
    
}
