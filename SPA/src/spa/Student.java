/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spa;

import com.sun.org.apache.xpath.internal.operations.Equals;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author tifui
 */
public class Student extends Person{
    
    private Project project_enroled;
    private List<Project> projects_prefered;
    /**
     * Create new student.
     * @param nume
     * @param prenume
     * @param email
     * @param telefon 
     */
    public Student(String nume,String prenume,String email,String telefon )
    {
        this.setNume(nume);
        this.setEmail(email);
        this.setPrenume(prenume);
        this.setTelefon(telefon);
        projects_prefered=new ArrayList<>();
    }
    /**
     * Add a prefred project.
     * @param lecturer 
     */
    /**
     * add a prefered projects
     * @param project 
     */
    public void addProject(Project project)
    {
        this.projects_prefered.add(project);
    }
    /**
     * add prefered projects.
     * @param projects 
     */
    public void addProjects(Project...projects)
    {
        for(Project proj :projects)
        {
            projects_prefered.add(proj);
        }
    }
    /**
     * Create new Student.
     * @param nume
     * @param prenume 
     */
    public Student(String nume,String prenume)
    {
        this.setNume(nume);
        this.setPrenume(prenume);
        projects_prefered=new ArrayList<>();
    }
    /**
     * Create new Student.
     * @param nume 
     */
    public Student(String nume)
    {
        this.setNume(nume);
        projects_prefered=new ArrayList<>();
    }
    /**
     * Get the project of student.
     * @return the project.
     */
    public void deleteEnroledProject()
    {
        project_enroled=null;
    }
    public Project getProject()
    {
        return project_enroled;
    }
    /**
     * Delete project from prefered project list.
     * @param project 
     */
    public void deletePreferedProject(Project project)
    {
       int i=this.projects_prefered.indexOf(project);
       if(i>=0)
       this.projects_prefered.remove(i);
       
    }
    
    /**
     * Set the project when student is enroled.
     * @param project 
     */
    public void setProject_eroled(Project project)
    {
        this.project_enroled=project;
    }
    /**
     * Get fist project of prefered projects list.
     * @return Project object.
     */
    public Project getFirstProject()
    {
        return this.projects_prefered.get(0);
    }
    
    /**
     * 
     * @return list of projects prefered.
     */
    public List<Project> getPreferedProjects()
    {
        return this.projects_prefered;
    }
        /**
     * Check if student is free.
     * @return 
     */
    @Override
    boolean isFree() 
    {
        return project_enroled==null;
    }
    /**
     * set student to be free
     */
    void setFree()
    {
        
            project_enroled=null;
        
    }
    /**
     * Check if curent student is equal with another student.
     * @param other other student
     * @return 
     */
    @Override
    public boolean equals(Object other){
    if (other == null) return false;
    if (other == this) return true;
    if (!(other instanceof Student))return false;
    Student otherStudent=(Student)other;
    if(this.getNume().equals(otherStudent.getNume()))
        if(this.getPrenume().equals(otherStudent.getPrenume()))
            return true;
    return false;
   
    }
    /**
     * Check if student is equal with another student.
     * @return 
     */
    @Override
    public String toString()
    {
        StringBuilder strBuild=new StringBuilder();
        strBuild.append(getNume()+": ");
        for(Project proj:projects_prefered)
        {
            strBuild.append(proj.getName()+" ");
        }
        return strBuild.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.project_enroled);
        return hash;
    }
  
    
}
