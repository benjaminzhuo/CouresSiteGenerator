/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import java.util.Date;

/**
 *
 * @author benjaminzhuo
 */
public class Schedule {
    
    protected String type;
    protected String date;
    protected String time;
    protected String title;
    protected String topic;
    protected String link;
    protected String criteria;
    
    
    
    
    public Schedule(String initType, String initDate, String initTime, String initTitle, String initTopic, String initLink, String initCriteria)
    {
        type = initType;
        date = initDate;
        time = initTime;
        title = initTitle;
        topic = initTopic;
        link = initLink;
        criteria = initCriteria;
    }
    
    public String getType(){
        return type;
    }
    public String getDate(){
        return date;
    }
    public String getTitle(){
        return title;
    }
    public String getTopic(){
        return topic;
    }
}
