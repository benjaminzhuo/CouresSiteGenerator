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
import javafx.scene.control.TableView;

/**
 *
 * @author khurr
 */
public class UpdateRecitation_Transaction implements jTPS_Transaction {

    private Recitation oldRecitation;
    private String section;
    private String instructor;
    private String dayAndTime;
    private String location;
    private String firstTA;
    private String secondTA;
    private CourseSiteGeneratorData data;
    private TableView recitationTable;

    public UpdateRecitation_Transaction(TableView recitationTable, String initSection, String initInstructor, String initDayAndTime, String initLocation, String initFirstTA, String initSecondTA, CourseSiteGeneratorData taData) {
        this.recitationTable=recitationTable;
        section = initSection;
        instructor = initInstructor;
        dayAndTime = initDayAndTime;
        location = initLocation;
        firstTA = initFirstTA;
        secondTA = initSecondTA;
        data = taData;
        oldRecitation = (Recitation)recitationTable.getSelectionModel().getSelectedItem();
        System.out.println("old rec" + oldRecitation.getSection()+" " + oldRecitation.getDayTime());
    }

    @Override
    public void doTransaction() {  //Control Y 
        String removeSection = ((Recitation)recitationTable.getSelectionModel().getSelectedItem()).getSection();
        data.removeRecitation(removeSection);
        data.addRecitation(section, instructor, dayAndTime, location, firstTA, secondTA);
      
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
        data.addRecitation(oldRecitation.getSection(),oldRecitation.getInstructor(),oldRecitation.getDayTime(),oldRecitation.getLocation(),oldRecitation.getFirstTA(),oldRecitation.getSecondTA());
        // data.removeTA(taName);

    }

}
