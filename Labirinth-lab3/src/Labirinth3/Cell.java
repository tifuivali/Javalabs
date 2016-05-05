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

public class Cell {
    
    private int row;
    private int colummn;
    private CellType type;
    
    @Override
    public boolean equals(Object obj)
    {
        if(obj==null)
            return false;
        if(!(obj instanceof Cell))
            return false;
        Cell cell=(Cell) obj;
        return this.colummn==cell.colummn&&this.row==cell.row&&this.type==cell.type;
        
    }
    
    public Cell(int row,int collumn,CellType type)
    {
        this.colummn=collumn;
        this.row=row;
        this.type=type;
    }
    
    public  Cell(int row,int col)
    {
        this.colummn=col;
        this.row=row;
    }
    /**
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * @param row the row to set
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * @return the colummn
     */
    public int getColummn() {
        return colummn;
    }

    /**
     * @param colummn the colummn to set
     */
    public void setColummn(int colummn) {
        this.colummn = colummn;
    }

    /**
     * @return the type
     */
    public CellType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(CellType type) {
        this.type = type;
    }
    
    
    
}
