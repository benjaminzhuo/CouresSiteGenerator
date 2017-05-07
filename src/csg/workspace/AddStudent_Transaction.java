/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import csg.data.CourseSiteGeneratorData;
import csg.data.Recitation;
import csg.data.Student;
import java.util.Collections;
import java.util.HashMap;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import csg.data.TeachingAssistant;
import csg.data.Team;

/**
 *
 * @author khurr
 */
public class AddStudent_Transaction implements jTPS_Transaction {

    private String firstName;
    private String lastName;
    private String team;
    private String role;
    private CourseSiteGeneratorData data;

    public AddStudent_Transaction(String firstName, String lastName, String team, String role, CourseSiteGeneratorData data) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.team = team;
        this.role = role;
        this.data = data;
    }

    @Override
    public void doTransaction() {  //Control Y 
        data.addStudent(firstName, lastName, team, role);
    }

    @Override
    public void undoTransaction() {  //Control Z 
        
        ObservableList<Student> studentList = data.getStudents();
        for (Student student : studentList) {
            if (firstName.equals(student.getFirstName())) {
                studentList.remove(student);
                return;
            }

        }
        
    }

}
