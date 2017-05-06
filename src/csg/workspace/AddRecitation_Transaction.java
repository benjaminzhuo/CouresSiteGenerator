/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import csg.data.CourseSiteGeneratorData;
import csg.data.Recitation;
import java.util.Collections;
import java.util.HashMap;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import csg.data.TeachingAssistant;

/**
 *
 * @author khurr
 */
public class AddRecitation_Transaction implements jTPS_Transaction {

    private String section;
    private String instructor;
    private String dayAndTime;
    private String location;
    private String firstTA;
    private String secondTA;
    private CourseSiteGeneratorData data;

    public AddRecitation_Transaction(String initSection, String initInstructor, String initDayAndTime, String initLocation, String initFirstTA, String initSecondTA, CourseSiteGeneratorData taData) {
        section = initSection;
        instructor = initInstructor;
        dayAndTime = initDayAndTime;
        location = initLocation;
        firstTA = initFirstTA;
        secondTA = initSecondTA;
        data = taData;
    }

    @Override
    public void doTransaction() {  //Control Y 
        data.addRecitation(section, instructor, dayAndTime, location, firstTA, secondTA);
        //Collections.sort(data.getTeachingAssistants());
        System.out.println("doRecitationTransaction");
    }

    @Override
    public void undoTransaction() {  //Control Z 
        System.out.println("undoRecitation Transaction");
        ObservableList<Recitation> recList = data.getRecitations();
        for (Recitation rec : recList) {
            if (section.equals(rec.getSection())) {
                recList.remove(rec);
                return;
            }

        }
        // data.removeTA(taName);

    }

}
