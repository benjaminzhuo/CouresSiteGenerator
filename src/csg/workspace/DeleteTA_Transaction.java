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
import csg.data.TeachingAssistant;

/**
 *
 * @author khurr
 */
public class DeleteTA_Transaction implements jTPS_Transaction {

    private TeachingAssistant ta;
    private CourseSiteGeneratorData data;
    private ArrayList<String> keyArray;
    private final HashMap<String, StringProperty> officeHours;

    public DeleteTA_Transaction(TeachingAssistant ta, CourseSiteGeneratorData data, HashMap<String, StringProperty> officeHours) {
        keyArray = new ArrayList<String>();
        this.data = data;
        this.officeHours = officeHours;
        this.ta = ta; 

    }

    @Override
    public void doTransaction() {  //control Y 
        keyArray.clear(); //clear anystored values 
        String taName = ta.getName();
        data.removeTA(taName);

        System.out.println("test");
        for (HashMap.Entry<String, StringProperty> entry : data.getOfficeHours().entrySet()) {
            String key = entry.getKey();
            StringProperty prop = data.getOfficeHours().get(key);
            if (prop.getValue().equals(taName)
                    || (prop.getValue().contains(taName + "\n"))
                    || (prop.getValue().contains("\n" + taName))) {
                System.out.println(prop.getValue());
                keyArray.add(key);
                data.removeTAFromCell(prop, taName);
            }
}
}

    @Override
    public void undoTransaction() {
        data.addTA(ta.getName(), ta.getEmail());

        for (String key : keyArray) {
            StringProperty prop = officeHours.get(key);
            String cellText = prop.getValue();
            prop.setValue(cellText + "\n" + ta.getName());
        }
       

    }
}
