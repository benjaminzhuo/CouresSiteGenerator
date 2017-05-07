/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import csg.CourseSiteGeneratorApp;
import csg.data.CourseSiteGeneratorData;
import csg.data.Recitation;
import csg.data.Schedule;
import csg.data.Student;
import java.util.Collections;
import java.util.HashMap;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import csg.data.TeachingAssistant;
import csg.data.Team;
import javafx.scene.control.TableView;

/**
 *
 * @author khurr
 */


public class UpdateStudent_Transaction implements jTPS_Transaction {

    private CourseSiteGeneratorApp app;
    private CourseSiteGeneratorWorkspace workspace;
    private String firstName;
    private String lastName;
    private String team;
    private String role;
    private String newFirstName;
    private String newLastName;
    private String newTeam;
    private String newRole;
    private CourseSiteGeneratorData data;
    
    public UpdateStudent_Transaction(CourseSiteGeneratorApp initApp) {
        app = initApp;
        workspace = (CourseSiteGeneratorWorkspace)app.getWorkspaceComponent();
        data = (CourseSiteGeneratorData) app.getDataComponent();
        newFirstName = workspace.getStudentFirstNameTextField().getText();
        newLastName = workspace.getStudentLastNameTextField().getText();
        newTeam = workspace.getStudentTeamComboBox().getSelectionModel().getSelectedItem().toString();
        newRole = workspace.getStudentRoleTextField().getText();
        
        TableView studentTable = workspace.getStudentsTable();
        Object selectedItem = studentTable.getSelectionModel().getSelectedItem();
        Student student = (Student)selectedItem;
        
        firstName = student.getFirstName();
        lastName = student.getLastName();
        team = student.getTeam();
        role = student.getRole();
                
    }

    @Override
    public void doTransaction() {  //Control Y 
        data.removeStudent(firstName);
        data.addStudent(newFirstName, newLastName, newTeam, newRole);
       
    }

    @Override
    public void undoTransaction() {  //Control Z 
        data.removeTeam(newFirstName);
        data.addStudent(firstName, lastName, team, role);
    }

}
