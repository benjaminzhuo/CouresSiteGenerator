/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import csg.data.CourseSiteGeneratorData;
import java.util.Collections;
import java.util.HashMap;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import csg.data.TeachingAssistant;

/**
 *
 * @author khurr
 */
public class AddTA_Transaction implements jTPS_Transaction {

    private String taEmail;
    private String taName;
    private CourseSiteGeneratorData data;

    public AddTA_Transaction(String name, String email, CourseSiteGeneratorData taData) {
        taEmail = email;
        taName = name;
        data = taData;
    }

    @Override
    public void doTransaction() {  //Control Y 
        data.addTA(taName, taEmail);
        //Collections.sort(data.getTeachingAssistants());
        System.out.println("doTransaction");
    }

    @Override
    public void undoTransaction() {  //Control Z 
        System.out.println("undo Transaction");
        ObservableList<TeachingAssistant> taList = data.getTeachingAssistants();
        for (TeachingAssistant ta : taList) {
            if (taName.equals(ta.getName())) {
                taList.remove(ta);
                return;
            }

        }
    }

}
