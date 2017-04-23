/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import csg.data.CourseSiteGeneratorData;
import javafx.scene.control.TableView;
import csg.workspace.jTPS_Transaction;
import csg.data.TeachingAssistant;

/**
 *
 * @author khurr
 */
public class UpdateTA_EmailOnly_Transaction implements jTPS_Transaction{
    
    private String orgName;
    
    private String orgEmail;
    private String newEmail;
    private CourseSiteGeneratorData taData;
    private CourseSiteGeneratorWorkspace workspace;
   public  UpdateTA_EmailOnly_Transaction(String oldName, String oldEmail, String email, CourseSiteGeneratorData data, CourseSiteGeneratorWorkspace workspace )
    {
        orgName=oldName;
        orgEmail=oldEmail;
        newEmail=email;
        taData=data; 
        this.workspace=workspace;
    }
    

    @Override
    public void doTransaction() {  //Control Y 
        TableView taTable = workspace.getTATable();
        TeachingAssistant ta=taData.getTA(orgName); 
        ta.setEmail(newEmail);
        workspace.emailTextField.setText(newEmail);
        taTable.refresh();
        
        //data.getTA(orgName).setEmail(email);
        //tasetEmail(email);
                
        
    }

    @Override
    public void undoTransaction() {  // COntrol Z 
         TableView taTable = workspace.getTATable();
        TeachingAssistant ta=taData.getTA(orgName);
        ta.setEmail(orgEmail);
        workspace.emailTextField.setText(orgEmail);
        taTable.refresh();
        
        
    }
    
}
