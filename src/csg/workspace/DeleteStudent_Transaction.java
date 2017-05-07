/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.beans.property.StringProperty;
import csg.workspace.jTPS_Transaction;
import csg.data.CourseSiteGeneratorData;
import csg.data.Recitation;
import csg.data.Schedule;
import csg.data.Student;
import csg.data.TeachingAssistant;

/**
 *
 * @author 
 */
public class DeleteStudent_Transaction implements jTPS_Transaction {

    private Student student;
    private CourseSiteGeneratorData data;
    
    

    public DeleteStudent_Transaction(Student student, CourseSiteGeneratorData initData) {
        this.student = student;
        data = initData;
    }

    @Override
    public void doTransaction() {  //control Y 
        data.removeStudent(student.getFirstName());
    }

    @Override
    public void undoTransaction() {
        data.addStudent(student.getFirstName(),student.getLastName(),student.getTeam(),student.getRole());
       

    }
}
