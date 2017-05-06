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

/**
 *
 * @author 
 */
public class DeleteSchedule_Transaction implements jTPS_Transaction {

    private Schedule sched;
    private CourseSiteGeneratorData data;
    
    

    public DeleteSchedule_Transaction(Schedule initSched, CourseSiteGeneratorData initData) {
        sched = initSched;
        data = initData;
    }

    @Override
    public void doTransaction() {  //control Y 
        data.removeSchedule(sched.getDate());
    }

    @Override
    public void undoTransaction() {
        data.addScheduleItem(sched.getType(),sched.getDate(),sched.getTime(),sched.getTitle(),sched.getTopic(),sched.getLink(),sched.getCriteria());
       

    }
}
