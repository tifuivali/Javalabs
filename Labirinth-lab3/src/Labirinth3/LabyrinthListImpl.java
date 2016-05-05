package Labirinth3;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author student
 */
public class LabyrinthListImpl implements Labyrinth{

    
    List<Cell> listFreeCel;
    private Cell startCell;
    private Cell stopCell;
    int rowCount;
    int collumnCount;
    
    
    public void addFreeCell(Cell freecell)
    {
        listFreeCel=new ArrayList<>();
        this.listFreeCel.add(freecell);
    }
    public LabyrinthListImpl()
    {
        this.listFreeCel=new ArrayList<>();
    }
    public LabyrinthListImpl(int rows,int colls)
    {
        listFreeCel=new ArrayList<>();
        this.rowCount=rows;
        this.collumnCount=colls;
    }
    
    public LabyrinthListImpl(int rows,int colls,List<Cell> freeCels)
    {
        listFreeCel=new ArrayList<>();
        this.collumnCount=colls;
        this.rowCount=rows;
        this.listFreeCel=freeCels;
    }
    
    
    @Override
    public int getRowConunt() 
    {
       return rowCount;    
    }

    @Override
    public int getCollumnCount() 
    {
      return collumnCount;  
    }

    @Override
    public boolean isFreeAt(int row, int collumn) 
    {
        for(Cell cell :listFreeCel)
        {
            if(cell.getRow()==row&&cell.getColummn()==collumn)
                return true;
        }
        return false;
    }

    @Override
    public boolean isWallAt(int row, int collumn)
    {
        if(startCell.getColummn()==collumn&&startCell.getRow()==row)
            return false;
        if(stopCell.getRow()==row&&stopCell.getColummn()==collumn)
        {
            return false;
        }
            
        for(Cell cell :listFreeCel)
        {
            if(cell.getRow()==row&&cell.getColummn()==collumn)
                return false;
        }
         
        return true;
    }
    
    public static LabyrinthListImpl ReadFromConsole()
    {
        Scanner scan=new Scanner(System.in);
        System.out.println("Introdeuceti celula de start:");
        System.out.println("row:");
        int i,j;
        i=scan.nextInt();
        System.out.println("collumn:");
        j=scan.nextInt();
        LabyrinthListImpl lab=new LabyrinthListImpl();
        lab.startCell.setRow(i);
        lab.startCell.setColummn(j);
        lab.startCell.setType(CellType.start);
        System.out.println("Introdeuceti celula finish:");
        System.out.println("row:");
        i=scan.nextInt();
        System.out.println("collumn:");
        j=scan.nextInt();
        lab.stopCell.setRow(i);
        lab.stopCell.setColummn(j);
        lab.stopCell.setType(CellType.start);
        i=0;j=0;
        System.out.println("Introduceti celule libere (i>0&&j>0)");
        while(i>0&&j>0)
        {
            System.out.println("Introduceti celula libera:");
            System.out.println("row:");
            i=scan.nextInt();
            System.out.println("collumn:");
            j=scan.nextInt();
            Cell cell=new Cell(i,j, CellType.freeCell);
            lab.listFreeCel.add(cell);       
        }
        return lab;
    }

    @Override
    public Cell getStartCell() 
    {
        return startCell;
    }

    @Override
    public Cell getFinsishCell() 
    {
        return stopCell;
    }

    /**
     * @param startCell the startCell to set
     */
    public void setStartCell(Cell startCell) {
        this.startCell = startCell;
    }

    /**
     * @param stopCell the stopCell to set
     */
    public void setStopCell(Cell stopCell) {
        this.stopCell = stopCell;
    }

    @Override
    public Cell getCellAt(int row, int collumn) 
    {
       if(startCell.getRow()==row&&startCell.getColummn()==collumn)
           return startCell;
       if(stopCell.getRow()==row&&stopCell.getColummn()==collumn)
           return stopCell;
       for(Cell cell:listFreeCel)
       {
           if(cell.getRow()==row&&cell.getColummn()==collumn)
               return cell;
       }
       Cell cell=new Cell(row,collumn,CellType.wall);
       return cell;
    }

    @Override
    public void setCell(Cell cell) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Labyrinth getClone() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
