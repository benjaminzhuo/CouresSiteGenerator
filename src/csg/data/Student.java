/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

/**
 *
 * @author benjaminzhuo
 */
public class Student {
    
    protected String firstName;
    protected String lastName;
    protected String team;
    protected String role;
    public Student(String initFirstName, String initLastName, String initTeam, String initRole){
        firstName = initFirstName;
        lastName = initLastName;
        team = initTeam;
        role = initRole;
    }
    
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getTeam(){
        return team;
    }
    public String getRole(){
        return role;
    }
}


