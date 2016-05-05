/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Labirinth3;


public class LabyrinthViewMatrixImpl implements LabyrinthView {
    
    private Labyrinth labirynth;
    
    public LabyrinthViewMatrixImpl(Labyrinth labyrinthMatrixImpl)
    {
        this.labirynth=labyrinthMatrixImpl;
    }

    @Override
    public void setLabirynth(Labyrinth lab) 
    {
        this.labirynth=lab;
    }

    @Override
    public Labyrinth getLabirynth() 
    {
        return labirynth;  
    }
    
    @Override
    public String toString()
    {
        int rowCount=labirynth.getRowConunt();
        int collsCount=labirynth.getCollumnCount();
        StringBuilder strBuilder=new StringBuilder();
        for(int i=0;i<rowCount;i++)
        {
            strBuilder.append("|");
            for(int j=0;j<collsCount;j++)
            {
                Cell cell=labirynth.getCellAt(i, j);
                if(cell.getType()==CellType.start)
                {
                    strBuilder.append("S");
                   
                }
                if(cell.getType()==CellType.finish)
                {
                    strBuilder.append("F");
                   
                }
                if(labirynth.isWallAt(i,j))
                {
                  strBuilder.append("*");
                }
                if(labirynth.isFreeAt(i, j))
                {
                    strBuilder.append(" ");
                }
                strBuilder.append("|");
            }
            strBuilder.append("\n");        
        }
        return strBuilder.toString();
        
    }
            

    @Override
    public void write() 
    {
        System.out.print(this.toString());
    }
    
}
