/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spa;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tifui
 */
public class SPA {

    
    private List<Student> students;
    private List<Lecturer> lectors;
    private List<Project> projects;
    /**
     * @param args the command line arguments
     */
    
    public SPA()
    {
        students=new ArrayList<>();
        lectors=new ArrayList<>();
        projects=new ArrayList<>();
    }
    
    public static void main(String[] args) 
    {
        /*
           Create and show the form
        */
            java.awt.EventQueue.invokeLater(() -> {
                new VisualExample().setVisible(true);
            });
             
           SPA spa=new SPA();
           spa.test();
          
    }
    
    void makeStudentsFree()
    {
        for(Student stud:students)
            stud.setFree();
    }

    List<Project> commonProjects(Student student,Lecturer lecturer)
    {
        List<Project> commProj=new ArrayList<>();
        for(Project studProj:student.getPreferedProjects())
        {
            for(Project lectProject:lecturer.getProjectOfered())
            {
                if(studProj.equals(lectProject))
                    commProj.add(studProj);
            }
        }
        return commProj;
    }
    
    void makeLecturerFree()
    {
        for(Lecturer lect:lectors)
            lect.setIsFree();
    }
    /**
     * Get the lecturer who ofers the project parameter.
     * @param proj
     * @return the lecturer who ofers proj
     */
    Lecturer getLecturerWhoOfers(Project proj)
    {
        for(Lecturer lecturer :lectors)
        {
            for(Project project :lecturer.getProjectOfered())
            {
                if(proj.equals(project))
                {
                    return lecturer;
                }
            }
        }
        return null;
    }
    
    void test()
    {
        Project p1=new Project("P1");
        p1.setCapacity(2);
        Project p2=new Project("P2");
        p2.setCapacity(1);
        Project p3=new Project("P3");
        p3.setCapacity(1);
        Project p4=new Project("P4");
        p4.setCapacity(1);
        Project p5=new Project("P5");
        p5.setCapacity(1);
        Project p6=new Project("P6");
        p6.setCapacity(1);
        Project p7=new Project("P7");
        p7.setCapacity(1);
        Project p8=new Project("P8");
        p8.setCapacity(1);
        Lecturer l1=new Lecturer("L1");
        l1.setCapacity(3);
        l1.addProjects(p1,p2,p3);
        Lecturer l2=new Lecturer("L2");
        l2.setCapacity(2);
        l2.addProjects(p4,p5,p6);
        Lecturer l3=new Lecturer("L3");
        l3.setCapacity(2);
        l3.addProjects(p7,p8);
        Student s1=new Student("S1");
        s1.addProjects(p1,p7);
        Student s2=new Student("S2");
        s2.addProjects(p1,p2,p3,p4,p5,p6);
        Student s3=new Student("S3");
        s3.addProjects(p2,p1,p4);
        Student s4=new Student("S4");
        s4.addProjects(p2);
        Student s5=new Student("S5");
        s5.addProjects(p1,p2,p3,p4);
        Student s6=new Student("S6");
        s6.addProjects(p2,p3,p4,p5,p6);
        Student s7=new Student("S7");
        s7.addProjects(p5,p3,p8);
        l1.addPreferedStudents(s7,s4,s1,s3,s2,s5,s6);
        l2.addPreferedStudents(s3,s2,s6,s7,s5);
        l3.addPreferedStudents(s1,s7);
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        students.add(s5);
        students.add(s6);
        students.add(s7);
        lectors.add(l1);
        lectors.add(l2);
        lectors.add(l3);
        projects.add(p1);
        projects.add(p2);
        projects.add(p3);
        projects.add(p4);
        projects.add(p5);
        projects.add(p6);
        projects.add(p7);
        projects.add(p8);
        
        
        matchStudents();
        for(Student stud:students)
        {
            if(stud.getProject()!=null)
            System.out.println(stud.getNume()+" este la proiectul: "+stud.getProject().getName());
        }
        
    }
    
    boolean isAllStudendsFree()
    {
        for(Student student :students)
        {
          if(!student.isFree())
              return false;
        }
        return true;
    }
    
    void matchStudents()
    {
        makeStudentsFree();
        makeLecturerFree();
      
        for(Student stud :students)
        {
            if(stud.isFree() && !stud.getPreferedProjects().isEmpty())
            {
               int j=0;
               Project p_j=stud.getFirstProject();
               Lecturer l_k=getLecturerWhoOfers(p_j);
               stud.setProject_eroled(p_j);
               p_j.addStudent(stud);
               l_k.addStudentAssigned(stud);
               if(p_j.isOverSubscribed())
               {
                   Student worstStudent=l_k.getWorstStudent(p_j.getAsignedStudents());
                   
                   worstStudent.deleteEnroledProject();
                   p_j.removeStudent(worstStudent);
                   l_k.removeStudentAsiigned(worstStudent);
                   
                   
      
               }
               else
               {
                   if(l_k.isOverSubscribed())
                   {
                       Student sr=l_k.getWortsStudent();
                       if(sr.getProject()!=null)
                       {
                       Project pt=sr.getProject();
                       pt.removeStudent(sr);
                       sr.deleteEnroledProject();
                       l_k.removeStudentAsiigned(sr);
                       
                       }
                  }
               }
               if(p_j.isFull())
               {
                    Student worstStudent=l_k.getWorstStudent(p_j.getAsignedStudents());
                   
                    List<Student> listStudentAsign=l_k.getStudentsAsigned();
                    int index=listStudentAsign.indexOf(worstStudent);
                    for(int i=index+1;i<listStudentAsign.size();i++)
                    {
                        Student succesorStud=listStudentAsign.get(i);
                        
                            succesorStud.deletePreferedProject(p_j);
                            l_k.removePreferedStudent(succesorStud);
                        
                    }
               }
               
               if(l_k.isFull())
               {
                    Student sr=l_k.getWortsStudent();
                    
                    
                    for(Lecturer lect:lectors)
                    {
                       List<Student> listStudentAsign=lect.getStudentsAsigned();
                      int index=listStudentAsign.indexOf(sr);
                      for(int i=index+1;i<listStudentAsign.size();i++)
                      {
                          Student succesorStud=listStudentAsign.get(i);
                          List<Project> comonProjects=commonProjects(succesorStud,lect);
                          for(Project proj :comonProjects)
                            {
                           
                                   succesorStud.deletePreferedProject(proj);
                                   lect.removePreferedStudent(succesorStud);
                                
                            
                            }
                        
                       }
                    }
                    
               }
     
            }
        }
    }
    
    
    
    
 
    
}
