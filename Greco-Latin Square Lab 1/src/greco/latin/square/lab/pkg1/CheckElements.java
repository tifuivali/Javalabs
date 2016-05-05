/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greco.latin.square.lab.pkg1;

import java.util.List;

/**
 *
 * @author tifui
 */
public class CheckElements implements Runnable
{
     int count=0;
     int start;
     int stop;  
     List<char[][]> list_matrixL;
     List<char[][]>listMatrixG;
     public CheckElements(int start,int stop)
     {
         this.start=start;
         this.stop=stop;
     }
     
     void setMatrixL(List<char[][]> list)
     {
         this.list_matrixL=list;
     }
     
     void setMatrixG(List<char[][]> list)
     {
         this.listMatrixG=list;
     }
     
     int getCount()
     {
         return this.count;
     }
     
   boolean testPairs(char[][] a, char[][] b)
   {
       
       for(int i=0;i<a.length;i++)
       {
           for(int j=0;j<a.length;j++)
           {
               for(int y=0;y<b.length;y++)
               {
                   for(int z=0;z<b.length;z++)
                   {
                     if(i!=y&&j!=z)
                     if(a[i][j]==a[y][z]&&b[i][j]==b[y][z])
                         return false;
                   }
               }
           }
       }
       
       return true;
   }
    
    @Override
    public void run() 
    {
               
        
       for(int i=start;i<stop;i++)
       {  
        
           for(int j=0;j<listMatrixG.size();j++)
            {
            
                 if(i!=j)
                 {
                 if(testPairs(list_matrixL.get(i), listMatrixG.get(j)))
                 {
                   count++;
                 }
                 }
            }
       }
    }
    
}
