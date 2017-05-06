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
import csg.data.Recitation;
import csg.data.TeachingAssistant;

/**
 *
 * @author 
 */
public class DeleteRecitation_Transaction implements jTPS_Transaction {

    private Recitation rec;
    private CourseSiteGeneratorData data;
    
    

    public DeleteRecitation_Transaction(Recitation initRec, CourseSiteGeneratorData initData) {
        rec = initRec;
        data = initData;
    }

    @Override
    public void doTransaction() {  //control Y 
        data.removeRecitation(rec.getSection());
    }

    @Override
    public void undoTransaction() {
        data.addRecitation(rec.getSection(), rec.getInstructor(), rec.getDayTime(), rec.getLocation(), rec.getFirstTA(), rec.getSecondTA());

    }
}
