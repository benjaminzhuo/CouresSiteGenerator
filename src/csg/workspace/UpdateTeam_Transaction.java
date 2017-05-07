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


public class UpdateTeam_Transaction implements jTPS_Transaction {

    private CourseSiteGeneratorApp app;
    private CourseSiteGeneratorWorkspace workspace;
    private String teamName;
    private String teamColor;
    private String teamTextColor;
    private String teamLink;
    private String newTeamName;
    private String newTeamColor;
    private String newTeamTextColor;
    private String newTeamLink;
    private CourseSiteGeneratorData data;
    
    public UpdateTeam_Transaction(CourseSiteGeneratorApp initApp) {
        app = initApp;
        workspace = (CourseSiteGeneratorWorkspace)app.getWorkspaceComponent();
        data = (CourseSiteGeneratorData) app.getDataComponent();
        newTeamName = workspace.getTeamNameTextField().getText();
        newTeamColor = workspace.getTeamColorPicker().getValue().toString();
        newTeamTextColor = workspace.getTeamTextColorPicker().getValue().toString();
        newTeamLink = workspace.getTeamLinkTextField().getText();
        
        TableView teamTable = workspace.getTeamsTable();
        Object selectedItem = teamTable.getSelectionModel().getSelectedItem();
        Team team = (Team)selectedItem;
        
        teamName = team.getName();
        teamColor = team.getColor();
        teamTextColor = team.getTextColor();
        teamLink = team.getLink();
                
    }

    @Override
    public void doTransaction() {  //Control Y 
        data.removeTeam(teamName);
        data.addTeam(newTeamName, newTeamColor, newTeamTextColor, newTeamLink);
       
    }

    @Override
    public void undoTransaction() {  //Control Z 
        data.removeTeam(newTeamName);
        data.addTeam(teamName, teamColor, teamTextColor, teamLink);
    }

}
