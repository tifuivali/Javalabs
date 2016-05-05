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
 *Project Class
 * @author tifui
 */
public class Project 
{
    private String name;
    private int capacity;
    private int reached_capacity;
    private List<Student> students_asigned;
    /**
     * Create new project.
     * @param name 
     */
    public Project(String name)
    {
       this.name=name;
       students_asigned=new ArrayList<>();
    }
    
   public List<Student> getAsignedStudents()
   {
       return this.students_asigned;
   }
    
  
    /**
     * Verify if project reached max capacity.
     * @return 
     */
    public boolean isFull()
    {
        return reached_capacity==capacity;
    }
    /**
     * Increment the riched capacity of project with value.
     * @param value 
     */
    public void incrementReachedCapacity(int value)
    {
        this.reached_capacity+=value;
    }
      /**
     * Decrement the riched capacity of project with value.
     * @param value 
     */
    public void decrementReachedCapacity(int value)
    {
        if(reached_capacity>0)
        this.reached_capacity-=value;
    }
    
    /**
     * add new student.
     * @param student 
     */
    public void addStudent(Student student)
    {
        this.students_asigned.add(student);
        incrementReachedCapacity(1);
    }
    
    /**
     * Get List of students who are asigned to the project.
     * @return students List.
     */
    public List<Student> getStudents()
    {
        return this.students_asigned;
    }
    
    public void removeStudent(Student student)
    {
       
        if(this.students_asigned.remove(student))
        {
            
            decrementReachedCapacity(1);
        }
       
    }
    
    /**
     * 
     * @return 
     */
    public boolean isOverSubscribed()
    {
        return this.reached_capacity>capacity;
    }
    

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * @param capacity the capacity to set
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    @Override
    public boolean equals(Object other)
    {
        if (other == null) return false;
        if (other == this) return true;
        if (!(other instanceof Project))return false;
        Project otherLecturer=(Project)other;
        return this.getName().equals(otherLecturer.getName())&&this.getCapacity()==otherLecturer.getCapacity();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.name);
        hash = 23 * hash + this.capacity;
        return hash;
    }

    /**
     * @return the reached_capacity
     */
    public int getReached_capacity() {
        return reached_capacity;
    }

    /**
     * @param reached_capacity the reached_capacity to set
     */
    public void setReached_capacity(int reached_capacity) {
        this.reached_capacity = reached_capacity;
    }
    
}
