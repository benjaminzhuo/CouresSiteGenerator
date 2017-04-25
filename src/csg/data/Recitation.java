/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author benjaminzhuo
 */
public class Recitation {
    
   
    private final StringProperty section;
    private final StringProperty instructor;
    private final StringProperty dayTime;
    private final StringProperty firstTA;
    private final StringProperty secondTA;
    private final StringProperty location;
    
    public Recitation(String initSection, String initInstructor, String initDayTime, String initLocation, String initTA, String initSecondTA){
        
        section = new SimpleStringProperty(initSection);
        instructor = new SimpleStringProperty(initInstructor);
        dayTime = new SimpleStringProperty(initDayTime);
        location = new SimpleStringProperty(initLocation);
        firstTA = new SimpleStringProperty(initTA);
        secondTA = new SimpleStringProperty(initSecondTA);
    }
    
    public void setLocation(String a){
        location.set(a);
    }
    public void setSection(String a){
        section.set(a);
    }
    
    public void setInstructor(String a){
        instructor.set(a);
    }
    
    public void setDayTime(String a){
        dayTime.set(a);
    }
    
    public void setFirstTA(String a){
        firstTA.set(a);
    }
    
    public void setSecondTA(String a){
        secondTA.set(a);
    }
    
    public String getSection(){
        return section.get();
    }
    
    public String getInstructor(){
        return instructor.get();
    }
    
    public String getDayTime(){
        return dayTime.get();
    }
    
    public String getLocation(){
        return location.get();
    }
    
    public String getFirstTA(){
        return firstTA.get();
    }
    
    public String getSecondTA(){
        return secondTA.get();
    }
    
    
    
}
