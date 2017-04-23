/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;


import csg.workspace.jTPS_Transaction;
import csg.CourseSiteGeneratorApp;
import csg.data.CourseSiteGeneratorData;
import csg.data.TAData;
import csg.data.TeachingAssistant;


/**
 *
 * @author khurr
 */
public class UpdateTA_Transaction implements jTPS_Transaction {

    private String oldName;
    private String newName;
    private String oldEmail;
    private String newEmail;
    private CourseSiteGeneratorData taData;
    private TeachingAssistant ta;
    private CourseSiteGeneratorApp app; 
    private CourseSiteGeneratorWorkspace transWorkspace; 

    public UpdateTA_Transaction(String orgName, String name, String orgEmail, String email, CourseSiteGeneratorData data, CourseSiteGeneratorApp taApp, CourseSiteGeneratorWorkspace workspace) {
        oldName = orgName;
        newName = name;
        oldEmail = orgEmail;
        newEmail = email;
        taData = data;
        ta = data.getTA(orgName);
        app=taApp; 
        transWorkspace=workspace; 
    }

    @Override
    public void doTransaction() {  //Control Y 
        System.out.println("updateTA doTransaction ");
        taData.getTA(oldName).setName(newName);
        CourseSiteGeneratorController controller = new CourseSiteGeneratorController(app);
        controller.handleUpdateTaGrid(oldName, newName);
        ta.setName(newName);                        // MOVED TO TRANSACTION CASE 
        ta.setEmail(newEmail);
        transWorkspace.nameTextField.setText(newName);
        transWorkspace.emailTextField.setText(newEmail);
       // transWorkspace.taTable.refresh();

    }

    @Override
    public void undoTransaction() {  //Control Z 
        System.out.println("updateTA undoTransaction ");
        taData.getTA(newName).setName(oldName);
        CourseSiteGeneratorController controller = new CourseSiteGeneratorController(app);
        controller.handleUpdateTaGrid(newName, oldName);
        ta.setName(oldName);        // MOVED TO TRANSACTION CASE 
        ta.setEmail(oldEmail);
        transWorkspace.nameTextField.setText(oldName);
        transWorkspace.emailTextField.setText(oldEmail);
        //transWorkspace.taTable.refresh();

    }

}
