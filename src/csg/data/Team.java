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
public class Team {
    
    private final StringProperty name;
    private final StringProperty color;
    private final StringProperty textColor;
    private final StringProperty link;
    public Team(String initName, String initColor, String initTextColor, String initLink)
    {
        name = new SimpleStringProperty(initName);
        color = new SimpleStringProperty(initColor);
        textColor = new SimpleStringProperty(initTextColor);
        link = new SimpleStringProperty(initLink);
    }
    
    public String getName(){
        return name.get();
    }
    public String getColor(){
        return color.get();
    }
    public String getTextColor(){
        return textColor.get();
    }
    public String getLink(){
        return link.get();
    }
    public String toString(){
        return name.getValue();
    }
}
