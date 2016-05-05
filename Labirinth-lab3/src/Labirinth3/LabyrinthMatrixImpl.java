package Labirinth3;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Scanner;

/**
 *
 * @author student
 */
public class LabyrinthMatrixImpl implements Labyrinth{

    int[][] matrixLab;
    int rowCount;
    int columnCount;
    public LabyrinthMatrixImpl(int rowCount,int columsCount)
    {
        this.columnCount=columsCount;
        this.rowCount=rowCount;
        matrixLab=new int[rowCount][columsCount];
    }
    public LabyrinthMatrixImpl(int[][] labMatrix)
    {
        this.matrixLab=labMatrix;
        if(matrixLab.length>0)
        {
            this.columnCount=matrixLab[0].length;
        }
        else this.columnCount=0;
        this.rowCount=labMatrix.length;
    }
    
    @Override
    public int getRowConunt() 
    {
        return rowCount;
    }

    @Override
    public int getCollumnCount() 
    {
        return columnCount;
    }

    @Override
    public boolean isFreeAt(int row, int collumn) 
    {
        if(row>rowCount-1|row<0|collumn>columnCount-1|collumn<0)
            return false;
       return matrixLab[row][collumn]==0;
    }
    
    @Override
    public Cell getCellAt(int row,int colummn)
    {
        
        Cell cell=new Cell(row, colummn);
        if(row>rowCount-1|row<0|colummn>columnCount-1|colummn<0)
            return new Cell(-1, -1,CellType.wall);
        if(matrixLab[row][colummn]==0)
        {
            cell.setType(CellType.freeCell);
        }
        if(matrixLab[row][colummn]==1)
        {
            cell.setType(CellType.wall);
        }
        if(matrixLab[row][colummn]==2)
        {
            cell.setType(CellType.finish);
        }
        if(matrixLab[row][colummn]==-1)
        {
            cell.setType(CellType.start);
        }
        if(matrixLab[row][colummn]==-2)
           cell.setType(CellType.visited);
        return cell;
        
       
    }

    @Override
    public boolean isWallAt(int row, int collumn) 
    {
        if(row>rowCount-1|collumn>columnCount-1|row<0|collumn<0)
            return true;
        return matrixLab[row][collumn]==1;
    }

    @Override
    public Cell getStartCell() 
    {
       for(int i=0;i<getRowConunt();i++)
       {
           for(int j=0;j<getCollumnCount();j++)
           {
               if(matrixLab[i][j]==-1)
                   return new Cell(i,j, CellType.start);
           }
       }
       return null;
    }
    
    public void setCell(Cell cell)
    {
        int r=cell.getRow();
        int c=cell.getColummn();
        if(cell.getType()==CellType.finish)
        {
            matrixLab[r][c]=2;
        }
        if(cell.getType()==CellType.freeCell)
        {
            matrixLab[r][c]=0;
        }
        if(cell.getType()==CellType.start)
        {
            matrixLab[r][c]=-1;
        }
        if(cell.getType()==CellType.visited)
        {
            matrixLab[r][c]=-2;
        }
        if(cell.getType()==CellType.wall)
        {
           matrixLab[r][c]=1;
        }
    }

    @Override
    public Cell getFinsishCell() 
    {
        for(int i=0;i<getRowConunt();i++)
       {
           for(int j=0;j<getCollumnCount();j++)
           {
               if(matrixLab[i][j]==2)
                   return new Cell(i,j, CellType.start);
           }
       }
       return null;
        
    }
    
    public static LabyrinthMatrixImpl ReadFromConsole()
    {
       Scanner scan=new Scanner(System.in);
       System.out.println("Introduceti numarul de randuri:");
       int r=scan.nextInt();
       System.out.println("Introduceti numarul de coloane:");
       int c=scan.nextInt();
       LabyrinthMatrixImpl lab=new LabyrinthMatrixImpl(r, c);
       System.out.println("Inttroduceti matricea labirind:");
       for(int i=0;i<lab.getRowConunt();i++)
       {
           for(int j=0;j<lab.getCollumnCount();j++)
           {
              int x=-7;
               while(x<-1||x>2)
               {
                 System.out.println("Introduceti elementul "+i+" "+j+" :");
                  x=scan.nextInt();
                   
               }
               lab.matrixLab[i][j]=x; 
           }
       }
       return lab;
      
    }

    @Override
    public Labyrinth getClone() {
        Labyrinth lab=new LabyrinthMatrixImpl(matrixLab);
        return lab;
    }
}
