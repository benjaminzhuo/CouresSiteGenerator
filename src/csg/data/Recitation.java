/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

/**
 *
 * @author benjaminzhuo
 */
public class Recitation {
    
    protected String section;
    protected String instructor;
    protected String dayTime;
    protected String firstTA;
    protected String secondTA;
    protected String location;
    
    public Recitation(String initSection, String initInstructor, String initDayTime, String initLocation, String initTA, String initSecondTA){
        
        section = initSection;
        instructor = initInstructor;
        dayTime = initDayTime;
        location = initLocation;
        firstTA = initTA;
        secondTA = initSecondTA;
    }
    
    public void setLocation(String a){
        location = a;
    }
    public void setSection(String a){
        section = a;
    }
    
    public void setInstructor(String a){
        instructor = a;
    }
    
    public void setDayTime(String a){
        dayTime = a;
    }
    
    public void setFirstTA(String a){
        firstTA = a;
    }
    
    public void setSecondTA(String a){
        secondTA = a;
    }
    
    public String getSection(){
        return section;
    }
    
    public String getInstructor(){
        return instructor;
    }
    
    public String getDayTime(){
        return dayTime;
    }
    
    public String getLocation(){
        return location;
    }
    
    public String getFirstTA(){
        return firstTA;
    }
    
    public String getSecondTA(){
        return secondTA;
    }
    
    
    
}
