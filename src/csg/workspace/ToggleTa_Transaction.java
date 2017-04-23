/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;

import javafx.scene.layout.Pane;
import csg.workspace.jTPS_Transaction;
import csg.CourseSiteGeneratorApp;
import csg.data.CourseSiteGeneratorData;
import csg.data.TAData;
import csg.data.TeachingAssistant;

/**
 *
 * @author khurr
 */
public class ToggleTa_Transaction implements jTPS_Transaction {

    private Object selectedItem;
    private CourseSiteGeneratorApp app;
    private Pane pane;

    /**
     *
     * @param selectedItem
     * @param workspace
     * @param app
     * @param pane
     */
    public ToggleTa_Transaction(Object selectedItem, CourseSiteGeneratorApp app, Pane pane) {
        this.selectedItem = selectedItem;
        this.app = app;
        this.pane = pane;
    }

    @Override
    public void doTransaction() {
        TeachingAssistant ta = (TeachingAssistant) selectedItem;
        String taName = ta.getName();
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        String cellKey = pane.getId();

        // AND TOGGLE THE OFFICE HOURS IN THE CLICKED CELL
        data.toggleTAOfficeHours(cellKey, taName);

    }

    @Override
    public void undoTransaction() {
        TeachingAssistant ta = (TeachingAssistant) selectedItem;
        String taName = ta.getName();
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        String cellKey = pane.getId();

        // AND TOGGLE THE OFFICE HOURS IN THE CLICKED CELL
        data.toggleTAOfficeHours(cellKey, taName);

    }

}
