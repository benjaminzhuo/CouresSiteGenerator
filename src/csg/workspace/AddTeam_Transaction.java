/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import csg.data.CourseSiteGeneratorData;
import csg.data.Recitation;
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
public class AddTeam_Transaction implements jTPS_Transaction {

    private String name;
    private String color;
    private String textColor;
    private String link;
    private CourseSiteGeneratorData data;

    public AddTeam_Transaction(String name, String color, String textColor, String link, CourseSiteGeneratorData data) {
        this.name = name;
        this.color = color;
        this.textColor = textColor;
        this.link = link;
        this.data = data;
    }

    @Override
    public void doTransaction() {  //Control Y 
        data.addTeam(name,color,textColor,link);
    }

    @Override
    public void undoTransaction() {  //Control Z 
        
        ObservableList<Team> teamList = data.getTeams();
        for (Team team : teamList) {
            if (name.equals(team.getName())) {
                teamList.remove(team);
                return;
            }

        }
        
    }

}
