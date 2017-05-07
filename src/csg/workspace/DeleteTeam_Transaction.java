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
import csg.data.TeachingAssistant;
import csg.data.Team;

/**
 *
 * @author 
 */
public class DeleteTeam_Transaction implements jTPS_Transaction {

    private Team team;
    private CourseSiteGeneratorData data;
    
    

    public DeleteTeam_Transaction(Team initTeam, CourseSiteGeneratorData initData) {
        team = initTeam;
        data = initData;
    }

    @Override
    public void doTransaction() {  //control Y 
        data.removeTeam(team.getName());
    }

    @Override
    public void undoTransaction() {
        data.addTeam(team.getName(),team.getColor(),team.getTextColor(),team.getLink());
       

    }
}
