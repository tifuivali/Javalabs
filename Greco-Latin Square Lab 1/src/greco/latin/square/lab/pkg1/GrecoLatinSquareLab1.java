/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greco.latin.square.lab.pkg1;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import javafx.beans.binding.StringBinding;

/**
 *
 * @author tifui
 */
public class GrecoLatinSquareLab1 {

    
    private char[] latinChars;
    private char[] grecoChars;
    private char[][][] generatedSquare;
    private int n;
    char[][][] generateSet;
    char[] vecAllSolSet;
    char matrixL[][];
    List<char[][]>list_matrixL;
    List<char[]>list_allSol;
    List<char[] >list_allSolGrec;
    List<char[][]>listMatrixG;
    char[] vecAllSetG;
    char[][] matrix_perm;
    
    
    public void writeSSet()
    {
        for(int i=0;i<latinChars.length;i++)
        {
            System.out.print(latinChars[i]+" ");
        }
        System.out.println();
    }
    
    public void writeTSet()
    {
        for(int i=0;i<grecoChars.length;i++)
        {
            System.out.print(grecoChars[i]+" ");
            
        }
        System.out.println();
    }
    
    public void writeN()
    {
        System.out.println(n);
    }
    
    public void setSChars(char[] chars)
    {
        latinChars=chars;
    }
    
    public void setTChars(char[] chars)
    {
        grecoChars=chars;
    }
    
    public void setN(int n)
    {
        this.n=n;
        generateSet=new char[n][n][2];
        vecAllSolSet=new char[n];
        matrixL=new char[n][n];
        list_matrixL=new ArrayList<>();
        list_allSol=new ArrayList<>();
        vecAllSetG=new char[n];
        listMatrixG=new ArrayList<>();
        list_allSolGrec=new ArrayList<>();
        matrix_perm=new  char[n*n][2];
               
      
    }
 
    private void WriteMatrix(char[][] matrix)
    {
        for(int i=0;i<n;i++)
        {
            System.out.println(matrix[i]);
        }
        System.out.println();
    }
    
    private boolean checkRowInMatrix(char[] line)
    {
        for(int i=0;i<matrixL.length;i++)
        {
            for(int j=0;j<line.length;j++)
            {
                if(matrixL[i][j]==line[j])
                {
                    return false;
                }
            }
        }
        return true;
    }
    private boolean checkRowInMatrixG(int maxLine ,char[] line)
    {
        for(int i=0;i<maxLine;i++)
        {
            for(int j=0;j<line.length;j++)
            {
                if(matrixL[i][j]==line[j])
                {
                    return false;
                }
            }
        }
        return true;
    }
       
       

    
    
  
 
    
    public char[][][] getExampleSquare()
    {
        char alpha='\u03B1';
        char beta='\u03B2';
        char gama='\u03B3';
        char[][][] square=new char[3][3][2];
        square[0][0][0]='A';
        square[0][0][1]=alpha;
        square[0][1][0]='B';
        square[0][1][1]=gama;
        square[0][2][0]='C';
        square[0][2][1]=beta;
        square[1][0][0]='B';
        square[1][0][1]=beta;
        square[1][1][0]='C';
        square[1][1][1]=alpha;
        square[1][2][0]='A';
        square[1][2][1]=gama;
        square[2][0][0]='C';
        square[2][0][1]=gama;
        square[2][1][0]='A';
        square[2][1][1]=beta;
        square[2][2][0]='B';
        square[2][2][1]=alpha;
        
        return square;
  
    }
    

    
    public char[][][] combineToPairs(char[][] latin ,char[][] greco)
    {
        char[][][] result=new char[n][n][2];
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                result[i][j][0]=latin[i][j];
                result[i][j][1]=greco[i][j];
            }
        }
        return result;
    }
    
   int factorial(int n)
   {
       int fact=1;
       for(int i=1;i<=n;i++)
           fact*=i;
       return fact;
   }
    
   
    
    public void Write(char[][][] square)
    {
        
        for(int i=0;i<square.length;i++)
        {
            for(int j=0;j<square[i].length;j++)
            {
                for(int z=0;z<square[i][j].length;z++)
                {
                    System.out.print(square[i][j][z]);
                }
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public void generateFisrtLatinChars()
    {
        char[] latinChars=new char[n];
        for(int i=0;i<n;i++)
        {
            latinChars[i]=(char) ('A'+i);
        }
        this.latinChars=latinChars;
    }
    
    public void generateFisrtGrecoChars()
    {
        char[] grecoChars=new char[n];
        for(int i=0;i<n;i++)
        {
            grecoChars[i]=(char) ('\u03B1'+i);
        }
        this.grecoChars=grecoChars;
        
    }
    
    public void generateN()
    {
        Random rand=new Random();
        int num=rand.nextInt(20);
        n=num;
        generateSet=new char[n][n][2];
        vecAllSolSet=new char[n];
        matrixL=new char[n][n];
        list_matrixL=new ArrayList<>();
        list_allSol=new ArrayList<>();
        vecAllSetG=new char[n];
        listMatrixG=new ArrayList<>();
        list_allSolGrec=new ArrayList<>();
        matrix_perm=new  char[n*n][2];
    }
    
    public void removeElementsOfSquare()
    {
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {for(int z=0;z<2;z++)
              {
                generatedSquare[i][j][z]='0';
                
               }
            
            }
        }
    }
    
   
    
    public static void main(String[] args) throws InterruptedException 
    {
        
      GrecoLatinSquareLab1 square=new GrecoLatinSquareLab1();
      char[][][] exampleSquare=square.getExampleSquare();
      square.Write(exampleSquare);
      if(args.length<7)
      {
          
          square.generateN();
          square.generateFisrtLatinChars();
          square.generateFisrtGrecoChars();
          square.generatedSquare=new char[square.n][square.n][2];
      }
      else
      {
         int n=Integer.parseInt(args[0]);
         square.setN(n);
         StringBuilder strLatin=new StringBuilder();
         for(int i=1;i<args.length-n;i++)
         {
             strLatin.append(args[i]);
             
         }
         StringBuilder strGreco=new StringBuilder();
         for(int i=args.length-n;i<args.length;i++)
         {
             strGreco.append(args[i]);
         }
         
         square.setSChars(strLatin.toString().toCharArray());
         square.setTChars(strGreco.toString().toCharArray());
         square.generatedSquare=new char[square.n][square.n][2];
                
         
      }
        System.out.println("n=:");
      square.writeN();
        System.out.println("S set:");
      square.writeSSet();
        System.out.println("T set:");
      square.writeTSet();
      square.removeElementsOfSquare();
     square.generateSolutions();
    
       
    }
    

    
    @SuppressWarnings("empty-statement")
   void generateSolutions() throws InterruptedException
   {
       
       back(0); 
       backk(0);
       back2(0);
       backk2(0);
       int count=0;
       System.out.println("gata back");
       System.out.println("nr linii"+list_allSol.size());
       System.out.println("matrici latine:"+list_matrixL.size()+" matrici grecesti:"+listMatrixG.size());
    
    for(int i=0;i<list_matrixL.size();i++)
    {  
        
        for(int j=0;j<listMatrixG.size();j++)
        {
            
            if(testPairs(list_matrixL.get(i), listMatrixG.get(j)))
            {
                if(count==0)
                {
                    Write(combineToPairs(list_matrixL.get(i), listMatrixG.get(j)));
                }
               count++;
            }
        }
    }
      
       CheckElements check1=new CheckElements(0, list_matrixL.size()/2);
       check1.setMatrixG(listMatrixG);
       check1.setMatrixL(list_matrixL);
       CheckElements check2=new CheckElements(list_matrixL.size()/2, list_matrixL.size());
       check2.setMatrixG(listMatrixG);
       check2.setMatrixL(list_matrixL);
       Thread thread1=new Thread(check1);
       Thread thread2=new Thread(check2);
       thread1.start();
       thread2.start();;
       thread1.join();
       thread2.join();
       count+=check1.getCount();
       count+=check2.getCount();
       System.out.println("count="+count);
    
    
   }
  
   boolean testPairs(char[][] a, char[][] b)
   {
       /*
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
       */
       return true;
   }
    
 boolean valid(int k)
 {
     for(int y=0;y<k;y++)
     {
        if(vecAllSolSet[y]==vecAllSolSet[k])
            return false;
     }
     return true;
 }

    void back(int k)
    {
        for(int x=0;x<n;x++)
        {
          vecAllSolSet[k]=latinChars[x];
            if(valid(k))
            {
                if(k==n-1)
                {
                    
                  list_allSol.add(vecAllSolSet.clone());
                }
                else{
                    back(k+1);
                }
                
                
            }
        }
        
    }
    
    boolean validd(int k)
    {
       
        for(int i=0;i<k;i++)
        {
                
            for(int j=0;j<n;j++)
            {
                    if(i!=k)
                    if(matrixL[i][j]==matrixL[k][j])
                        return false;
            }
        }
        return true;
    }
    
       void backk(int k)
    {
        for(int x=0;x<list_allSol.size();x++)
        {
          matrixL[k]=list_allSol.get(x).clone();
            if(validd(k))
            {
                if(k==n-1)
                {
                    list_matrixL.add(matrixL.clone());
                }
                else{
                    backk(k+1);
                }
                
                
            }
        }
        
    }
    
       //functii opentru generearea grecestilor
       
        boolean valid2(int k)
 {
     for(int y=0;y<k;y++)
     {
        if(vecAllSetG[y]==vecAllSetG[k])
            return false;
     }
     return true;
 }

    void back2(int k)
    {
        for(int x=0;x<n;x++)
        {
          vecAllSetG[k]=grecoChars[x];
            if(valid2(k))
            {
                if(k==n-1)
                {
                    
                  list_allSolGrec.add(vecAllSetG.clone());
                }
                else{
                    back2(k+1);
                }
                
                
            }
        }
        
    }
    
    boolean validd2(int k)
    {
       
        for(int i=0;i<k;i++)
        {
                
            for(int j=0;j<n;j++)
            {
                    if(i!=k)
                    if(matrixL[i][j]==matrixL[k][j])
                        return false;
            }
        }
        return true;
    }
    
       void backk2(int k)
    {
        for(int x=0;x<list_allSolGrec.size();x++)
        {
          matrixL[k]=list_allSolGrec.get(x).clone();
            if(validd2(k))
            {
                if(k==n-1)
                {
                    
                    listMatrixG.add(matrixL.clone());
                }
                else{
                    backk2(k+1);
                }
                
                
            }
        }
        
    }
    
    
    
    
    
}
