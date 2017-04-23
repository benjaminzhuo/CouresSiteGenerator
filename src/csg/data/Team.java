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
public class Team {
    
    protected String name;
    protected String color;
    protected String textColor;
    protected String link;
    public Team(String initName, String initColor, String initTextColor, String initLink)
    {
        name = initName;
        color = initColor;
        textColor = initTextColor;
        link = initLink;
    }
    
    public String getName(){
        return name;
    }
    public String getColor(){
        return color;
    }
    public String getTextColor(){
        return textColor;
    }
    public String getLink(){
        return link;
    }
}
