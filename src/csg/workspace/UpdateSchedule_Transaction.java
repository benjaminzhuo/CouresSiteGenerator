/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import csg.data.CourseSiteGeneratorData;
import csg.data.Recitation;
import csg.data.Schedule;
import java.util.Collections;
import java.util.HashMap;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import csg.data.TeachingAssistant;
import javafx.scene.control.TableView;

/**
 *
 * @author khurr
 */
public class UpdateSchedule_Transaction implements jTPS_Transaction {

    private String removeDate;
    private Schedule oldSchedule;
    private String type;
    private String date;
    private String time;
    private String title;
    private String topic;
    private String link;
    private String criteria;
    private CourseSiteGeneratorData data;

    public UpdateSchedule_Transaction(Schedule oldSchedule,String removeDate,String type,String date,String time,String title,String topic,String link,String criteria,CourseSiteGeneratorData data) {
        this.removeDate = removeDate;
        this.type = type;
        this.oldSchedule = oldSchedule;
        this.date = date;
        this.time = time;
        this.title = title;
        this.topic = topic;
        this.link = link;
        this.criteria = criteria;
        this.data = data;
        
       
    }

    @Override
    public void doTransaction() {  //Control Y 
        data.removeSchedule(removeDate);
        data.addScheduleItem(type,date,time,title,topic,link,criteria);
        
      
    }

    @Override
    public void undoTransaction() {  //Control Z 
        System.out.println("undoRecitation Transaction");
        ObservableList<Schedule> schedList = data.getScheduleItems();
        for (Schedule sched : schedList) {
            if (date.equals(sched.getDate())) {
                schedList.remove(sched);
                return;
            }

        }
        data.addScheduleObject(oldSchedule);
        // data.removeTA(taName);

    }

}
