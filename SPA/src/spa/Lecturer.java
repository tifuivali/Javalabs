/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spa;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author tifui
 */
public class Lecturer extends Person
{
   
    /**
     * @param nume 
     * @param prenume 
     */
    private int capacity;
    private int reachedCapacity;
    private List<Project> proejects_offered;
    private List<Student> students_prefered;
    private List<Student> students_assigned;
    public Lecturer(String nume,String prenume)    
    {
        this.setNume(nume);
        this.setPrenume(prenume);
        this.proejects_offered=new ArrayList<>();
        this.students_prefered=new ArrayList<>();
        this.students_assigned=new ArrayList<>();
        
    }
    /**
     * 
     * @param nume
     * @param prenume
     * @param email
     * @param telefon 
     */
    public Lecturer(String nume,String prenume,String email,String telefon)
    {
        this.setEmail(email);
        this.setNume(nume);
        this.setPrenume(prenume);
        this.setTelefon(email);
        this.proejects_offered=new ArrayList<>();
        this.students_prefered=new ArrayList<>();
         this.students_assigned=new ArrayList<>();
       
    }
    /**
     * Check if lecturer is full.
     * @return 
     */
    public boolean isFull()
    {
        return reachedCapacity==capacity;
    }
    
    public void addStudentAssigned(Student stud)
    {
        this.students_assigned.add(stud);
    }
    
    public void removeStudentAsiigned(Student student)
    {
        if(this.students_assigned.remove(student))
        {
            decrementReachedCapacity(1);
        }
    }
    
    /**
     * Create new lecturer.
     * @param nume 
     */
    public Lecturer(String nume)
    {
        this.setNume(nume);
        this.proejects_offered=new ArrayList<>();
        this.students_prefered=new ArrayList<>();
         this.students_assigned=new ArrayList<>();
    }
    /**
     * Add a prefered student.
     * @param student 
     */
    public void addPreferdStudent(Student student)
    {
        this.students_prefered.add(student);
    }
    /**
     * add prefered students.
     * @param students 
     */
    public void addPreferedStudents(Student...students)
    {
        for(Student stud:students)
        {
            students_prefered.add(stud);
        }
    }
    
    public List<Student> getStudents_Prefered()
    {
        return this.students_prefered;
    }
    
    public Student getWorstStudent(List<Student> students)
    {
        int max=0;
        for(Student stud:students)
        {
            int m=this.students_prefered.indexOf(stud);
            if(m>max)
                max=m;
                    
        }
        return  this.students_prefered.get(max);
    }
    public List<Student> getStudentsAsigned()
    {
        return this.students_assigned;
    }
    public void removePreferedStudent(Student student)
    {
        this.students_prefered.remove(student);
    }
        
    
    /**
     * Add new project.
     * @param proj 
     */
    public void addProject(Project proj)
    {
        proejects_offered.add(proj);
    }
    /**
     * add projects
     * @param projs 
     */
    public void addProjects(Project...projs)
    {
        for(Project proj:projs)
        {
            proejects_offered.add(proj);
        }
    }
    /**
     * Set the maximum capacity.
     * @param capacity 
     */
    public void setCapacity(int capacity)
    {
        this.capacity=capacity;
    }
    /**
     * Get the total capactity.
     * @return the total Capacity
     */
    public int getCapacity()
    {
        return this.capacity;
                
    }
    
    public boolean isOverSubscribed()
    {
        return reachedCapacity>capacity;
    }
    
    /**
     * check if Lecturer have not reached capacity
     * @return 
     */

    @Override
    boolean isFree() 
    {
        if(reachedCapacity<capacity)
           return true;
        return false;
    }
    void setIsFree()
    {
        reachedCapacity=0;
    }
    /**
     * @return the reachedCapacity
     */
    public int getReachedCapacity() 
    {
        return reachedCapacity;
    }
    /**
     * Increment reached capacity with specific value.
     * @param value 
     */
    public void IncrementReachedCapacity(int value)
    {
        this.reachedCapacity+=value;
    }
    /**
     * Decrement reached capacity with value.
     * @param value 
     */
    public void decrementReachedCapacity(int value)
    {
        if(reachedCapacity>0)
            reachedCapacity-=value;
    }
    /**
     * Get worst student asigned to this lecturer.
     * @return 
     */
    public Student getWortsStudent()
    {
        int mx=0;
       for(Student student:students_assigned)
       {
           int poz=students_prefered.indexOf(student);
           if(poz>mx)
               mx=poz;
       }
       
       return students_prefered.get(mx);
                
                
    }
    
    public List<Student> getListAsignedStudentsWithProj(Project proj)
    {
        List<Student>  studList=new ArrayList<>();
        for(Student stud:this.students_assigned)
        {
            if(stud.getProject()!=null)
            if(stud.getProject().equals(proj))
            {
                studList.add(stud);
            }
        }
        return studList;
    }
    /**
     * Get the oferedr project List.
     * @return List offeredProjects.
     */
    public List<Project> getProjectOfered()
    {
        return this.proejects_offered;
    }
    /**
     * @param reachedCapacity the reachedCapacity to set
     */
    public void setReachedCapacity(int reachedCapacity) {
        this.reachedCapacity = reachedCapacity;
    }
    /**
     * Check if lecturer equals with another lecturer.
     * @param other other Lecturer
     * @return 
     */
    @Override
    public boolean equals(Object other)
    {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Lecturer))return false;
        Lecturer otherLecturer=(Lecturer)other;
        if(this.getNume().equals(otherLecturer.getNume()))
              if(this.getPrenume().equals(otherLecturer.getPrenume()))
                  return true;
        return false;
    }
    /**
     * Increment the riched capacity with value.
     * @param value 
     */
    public void incrementReachedCapacity(int value)
    {
        this.reachedCapacity+=value;
    }
    /**
     * Return lecturer name : students prefered
     * @return string of lecturer
     */
    
    @Override
    public String toString()
    {
        StringBuilder strBuild=new StringBuilder();
        strBuild.append(getNume()+": ");
        for(Student stud :students_prefered)
        {
            strBuild.append(stud.getNume()+" ");
        }
        return strBuild.toString();
    }

    public String getOferedProjectsString()
    {
        StringBuilder strOfer=new StringBuilder();
        strOfer.append(getNume()+" offers ");
        for(Project p :proejects_offered)
        {
            strOfer.append(p.getName()+" ");
        }
        return strOfer.toString();
    }
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + this.capacity;
        hash = 31 * hash + this.reachedCapacity;
        hash = 31 * hash + Objects.hashCode(this.proejects_offered);
        return hash;
    }
    
    
    
}
