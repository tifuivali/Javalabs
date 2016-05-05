package Labirinth3;

import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author student
 */
public class Labirtnth3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
      Scanner scan=new Scanner(System.in);
      LabyrinthMatrixImpl lab=LabyrinthMatrixImpl.ReadFromConsole();
      LabyrinthViewMatrixImpl viewLab=new LabyrinthViewMatrixImpl(lab);
      viewLab.write();
      System.out.println("Select solver:");
      System.out.println("A.Automatic");
      System.out.println("M.Manual");
      String r=scan.next();
      if(r.toUpperCase().equals("M"))
      {
      LabyrinthSolver solver=new LabyrinthSolverInteractive(lab);
      solver.solve();
      }
      else 
      if(r.toUpperCase().equals("A"))
      {
          LabyrinthSolver solver=new LabyrinthSolverAutomated(lab);
          solver.solve();
      }
      
   
      
     
    }
    
}
