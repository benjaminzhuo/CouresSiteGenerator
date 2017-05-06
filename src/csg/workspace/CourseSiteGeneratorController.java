package csg.workspace;

import djf.ui.AppGUI;
import djf.ui.AppMessageDialogSingleton;
import djf.ui.AppYesNoCancelDialogSingleton;
import static csg.CourseSiteGeneratorProp.*;
import djf.ui.AppMessageDialogSingleton;
import djf.ui.AppYesNoCancelDialogSingleton;
import java.util.Collections;
import java.util.HashMap;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import csg.workspace.jTPS;
import csg.workspace.jTPS_Transaction;
import properties_manager.PropertiesManager;
import csg.CourseSiteGeneratorApp;
import csg.data.CourseSiteGeneratorData;
import csg.data.Recitation;
import csg.data.Schedule;
import csg.data.TeachingAssistant;
import csg.style.CourseSiteGeneratorStyle;
import static csg.style.CourseSiteGeneratorStyle.CLASS_HIGHLIGHTED_GRID_CELL;
import static csg.style.CourseSiteGeneratorStyle.CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN;
import static csg.style.CourseSiteGeneratorStyle.CLASS_OFFICE_HOURS_GRID_TA_CELL_PANE;
import csg.workspace.CourseSiteGeneratorWorkspace;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

/**
 * This class provides responses to all workspace interactions, meaning
 * interactions with the application controls not including the file toolbar.
 *
 * @author Richard McKenna
 * @version 1.0
 */
public class CourseSiteGeneratorController {

    // THE APP PROVIDES ACCESS TO OTHER COMPONENTS AS NEEDED
    CourseSiteGeneratorApp app;
    static jTPS jTPS = new jTPS();

    /**
     * Constructor, note that the app must already be constructed.
     */
    public CourseSiteGeneratorController(CourseSiteGeneratorApp initApp) {
        // KEEP THIS FOR LATER
        app = initApp;
    }

    /**
     * This helper method should be called every time an edit happens.
     */
    private void markWorkAsEdited() {
        // MARK WORK AS EDITED
        AppGUI gui = app.getGUI();
        gui.getFileController().markAsEdited(gui);
    }

    /**
     * This method responds to when the user requests to add a new TA via the
     * UI. Note that it must first do some validation to make sure a unique name
     * and email address has been provided.
     */
    public void handleAddTA() {
        // WE'LL NEED THE WORKSPACE TO RETRIEVE THE USER INPUT VALUES
        CourseSiteGeneratorWorkspace workspace = (CourseSiteGeneratorWorkspace) app.getWorkspaceComponent();
        TextField nameTextField = workspace.getNameTextField();
        TextField emailTextField = workspace.getEmailTextField();
        String name = nameTextField.getText();
        String email = emailTextField.getText();
        EmailValidator checkEmail = new EmailValidator();

        // WE'LL NEED TO ASK THE DATA SOME QUESTIONS TOO
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();

        // WE'LL NEED THIS IN CASE WE NEED TO DISPLAY ANY ERROR MESSAGES
        PropertiesManager props = PropertiesManager.getPropertiesManager();

        // DID THE USER NEGLECT TO PROVIDE A TA NAME?
        if (name.isEmpty()) {
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            dialog.show(props.getProperty(MISSING_TA_NAME_TITLE), props.getProperty(MISSING_TA_NAME_MESSAGE));
        } // DID THE USER NEGLECT TO PROVIDE A TA EMAIL?
        else if (email.isEmpty()) {
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            dialog.show(props.getProperty(MISSING_TA_EMAIL_TITLE), props.getProperty(MISSING_TA_EMAIL_MESSAGE));
        } // DOES A TA ALREADY HAVE THE SAME NAME OR EMAIL?
        else if (data.containsTA(name, email)) {
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            dialog.show(props.getProperty(TA_NAME_AND_EMAIL_NOT_UNIQUE_TITLE), props.getProperty(TA_NAME_AND_EMAIL_NOT_UNIQUE_MESSAGE));
        } // **********Check the TA Email Address for correct format 
        else if (!checkEmail.validate(email)) {
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            dialog.show(props.getProperty(INVALID_TA_EMAIL_TITLE), props.getProperty(INVALID_TA_EMAIL_MESSAGE));

        } // EVERYTHING IS FINE, ADD A NEW TA
        else {
            // ADD THE NEW TA TO THE DATA
            //data.addTA(name, email);
            jTPS_Transaction transaction1 = new AddTA_Transaction(name, email, data);

            jTPS.addTransaction(transaction1);
            //jTPS.doTransaction();
            // CLEAR THE TEXT FIELDS
            nameTextField.setText("");
            emailTextField.setText("");

            // AND SEND THE CARET BACK TO THE NAME TEXT FIELD FOR EASY DATA ENTRY
            nameTextField.requestFocus();
            // WE'VE CHANGED STUFF
            markWorkAsEdited();
        }
    }

    /**
     * This function provides a response for when the user presses a keyboard
     * key. Note that we're only responding to Delete, to remove a TA.
     *
     * @param code The keyboard code pressed.
     */
    public void handleKeyPress(KeyCode code) {
        // DID THE USER PRESS THE DELETE KEY?

        if (code == KeyCode.DELETE || code == KeyCode.BACK_SPACE) {
            // GET THE TABLE
            CourseSiteGeneratorWorkspace workspace = (CourseSiteGeneratorWorkspace) app.getWorkspaceComponent();
            TableView taTable = workspace.getTATable();

            // IS A TA SELECTED IN THE TABLE?
            Object selectedItem = taTable.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                TeachingAssistant ta = (TeachingAssistant) selectedItem;
                String taName = ta.getName();
                CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
                HashMap<String, StringProperty> officeHours = data.getOfficeHours();
                jTPS_Transaction transaction1 = new DeleteTA_Transaction(ta, data, officeHours);
                jTPS.addTransaction(transaction1);

                
                markWorkAsEdited();
            }
        }

    }
    public void handleRecitationKeyPress(KeyCode code) {
        // DID THE USER PRESS THE DELETE KEY?

        if (code == KeyCode.DELETE || code == KeyCode.BACK_SPACE) {
            // GET THE TABLE
            CourseSiteGeneratorWorkspace workspace = (CourseSiteGeneratorWorkspace) app.getWorkspaceComponent();
            TableView recitationTable = workspace.getRecitationTable();

            // IS A TA SELECTED IN THE TABLE?
           Object selectedItem = recitationTable.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                Recitation rec = (Recitation) selectedItem;
                CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
              //  HashMap<String, StringProperty> officeHours = data.getOfficeHours();
                jTPS_Transaction transaction1 = new DeleteRecitation_Transaction(rec, data);
                jTPS.addTransaction(transaction1);
                markWorkAsEdited();
            }
        }

    }
    public void handleScheduleKeyPress(KeyCode code) {
        // DID THE USER PRESS THE DELETE KEY?
        CourseSiteGeneratorWorkspace workspace = (CourseSiteGeneratorWorkspace) app.getWorkspaceComponent();
        if (code == KeyCode.DELETE || code == KeyCode.BACK_SPACE) {
            TableView scheduleTable = workspace.getScheduleTable();

            // IS A TA SELECTED IN THE TABLE?
            Object selectedItem = scheduleTable.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                Schedule sched = (Schedule) selectedItem;
                CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
              //  HashMap<String, StringProperty> officeHours = data.getOfficeHours();
                jTPS_Transaction transaction1 = new DeleteSchedule_Transaction(sched, data);
                jTPS.addTransaction(transaction1);
                markWorkAsEdited();
            }
        }

    }
    public void handleDeleteRecitation(){
            CourseSiteGeneratorWorkspace workspace = (CourseSiteGeneratorWorkspace) app.getWorkspaceComponent();
            TableView recitationTable = workspace.getRecitationTable();

            // IS A TA SELECTED IN THE TABLE?
            Object selectedItem = recitationTable.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                Recitation rec = (Recitation) selectedItem;
                CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
              //  HashMap<String, StringProperty> officeHours = data.getOfficeHours();
                jTPS_Transaction transaction1 = new DeleteRecitation_Transaction(rec, data);
                jTPS.addTransaction(transaction1);
                markWorkAsEdited();
            }
    }
     public void handleDeleteSchedule(){
            CourseSiteGeneratorWorkspace workspace = (CourseSiteGeneratorWorkspace) app.getWorkspaceComponent();
            TableView scheduleTable = workspace.getScheduleTable();

            // IS A TA SELECTED IN THE TABLE?
            Object selectedItem = scheduleTable.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                Schedule sched = (Schedule) selectedItem;
                CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
              //  HashMap<String, StringProperty> officeHours = data.getOfficeHours();
                jTPS_Transaction transaction1 = new DeleteSchedule_Transaction(sched, data);
                jTPS.addTransaction(transaction1);
                markWorkAsEdited();
            }
    }
    
    public void handleUndoTransaction() {
        jTPS.undoTransaction();
        markWorkAsEdited();
    }

    public void handleReDoTransaction() {
        System.out.println("Transaction crlt y");
        jTPS.doTransaction();
        markWorkAsEdited();
    }

    /**
     * This function provides a response for when the user clicks on the office
     * hours grid to add or remove a TA to a time slot.
     *
     * @param pane The pane that was toggled.
     */
    public void handleCellToggle(Pane pane) {
        // GET THE TABLE
        CourseSiteGeneratorWorkspace workspace = (CourseSiteGeneratorWorkspace) app.getWorkspaceComponent();
        TableView taTable = workspace.getTATable();

        // IS A TA SELECTED IN THE TABLE?
        Object selectedItem = taTable.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            // GET THE TA
            jTPS_Transaction transaction = new ToggleTa_Transaction(selectedItem, app, pane);
            jTPS.addTransaction(transaction);

            /*TeachingAssistant ta = (TeachingAssistant) selectedItem;
            String taName = ta.getName();
            TAData data = (TAData) app.getDataComponent();
            String cellKey = pane.getId();

            // AND TOGGLE THE OFFICE HOURS IN THE CLICKED CELL
            data.toggleTAOfficeHours(cellKey, taName);*/
            // WE'VE CHANGED STUFF
            markWorkAsEdited();
        }
    }

    public void handleTaClicked(Pane pane, Pane addBox) {
        // GET THE TABLE
        CourseSiteGeneratorWorkspace workspace = (CourseSiteGeneratorWorkspace) app.getWorkspaceComponent();
        TableView taTable = workspace.getTATable();

        // IS A TA SELECTED IN THE TABLE?
        Object selectedItem = taTable.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
           
            TeachingAssistant ta = (TeachingAssistant) selectedItem;
        
            addBox.getChildren().add(workspace.nameTextField);
            addBox.getChildren().add(workspace.emailTextField);
            addBox.getChildren().add(workspace.updateTaButton);
            addBox.getChildren().add(workspace.clearButton);
            // GET THE TA
            String taName = ta.getName();
            String taEmail = ta.getEmail();
            CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();

            // SET TextField To TA NAME 
            workspace.nameTextField.setText(taName);
            workspace.emailTextField.setText(taEmail);
           
        }
    }
    public void handleRecitationClicked() {
        // GET THE TABLE
        CourseSiteGeneratorWorkspace workspace = (CourseSiteGeneratorWorkspace) app.getWorkspaceComponent();
        TableView recitationTable = workspace.getRecitationTable();

        // IS A TA SELECTED IN THE TABLE?
        Object selectedItem = recitationTable.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
           
            Recitation rec = (Recitation) selectedItem;
        
           
            // GET THE TA
            String section = rec.getSection();
            String instructor = rec.getInstructor();
            String dayTime = rec.getDayTime();
            String location = rec.getLocation();
            String firstTAName = rec.getFirstTA();
            String secondTAName = rec.getSecondTA();
            CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();

            // SET TextField To TA NAME 
            workspace.sectionTextField.setText(section);
            workspace.instructorTextField.setText(instructor);
            workspace.dayAndTimeTextField.setText(dayTime);
            workspace.locationTextField.setText(location);
            workspace.firstTAComboBox.setValue(data.getTA(firstTAName));
            workspace.secondTAComboBox.setValue(data.getTA(secondTAName));
            workspace.updateable = true;
        }
    }
    public void handleScheduleClicked() {
        // GET THE TABLE
        CourseSiteGeneratorWorkspace workspace = (CourseSiteGeneratorWorkspace) app.getWorkspaceComponent();
        TableView scheduleTable = workspace.getScheduleTable();

        // IS A TA SELECTED IN THE TABLE?
        Object selectedItem = scheduleTable.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
           
            Schedule sched = (Schedule) selectedItem;
        
           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            
            CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
            LocalDate localDate = LocalDate.parse(sched.getDate(), formatter);
            // SET TextField To TA NAME 
            workspace.typeComboBox.setValue(sched.getType());
            workspace.scheduleDate.setValue(localDate);
            workspace.scheduleTimeTextField.setText(sched.getTime());
            workspace.scheduleTitleTextField.setText(sched.getTitle());
            workspace.scheduleTopicTextField.setText(sched.getTopic());
            workspace.scheduleLinkTextField.setText(sched.getLink());
            workspace.scheduleCriteriaTextField.setText(sched.getCriteria());
            
            
        }
    }

    public void handleUpdateTA() {
        CourseSiteGeneratorWorkspace workspace = (CourseSiteGeneratorWorkspace) app.getWorkspaceComponent();
        TableView taTable = workspace.getTATable();
        Object selectedItem = taTable.getSelectionModel().getSelectedItem();
        TeachingAssistant ta = (TeachingAssistant) selectedItem;
        String orgName = ta.getName();
        String orgEmail = ta.getEmail();

        TextField nameTextField = workspace.getNameTextField();
        TextField emailTextField = workspace.getEmailTextField();

        String name = nameTextField.getText();
        String email = emailTextField.getText();
        EmailValidator checkEmail = new EmailValidator();

        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();

        // WE'LL NEED THIS IN CASE WE NEED TO DISPLAY ANY ERROR MESSAGES
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        if (name.isEmpty()) {
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            dialog.show(props.getProperty(MISSING_TA_NAME_TITLE), props.getProperty(MISSING_TA_NAME_MESSAGE));
        } // DID THE USER NEGLECT TO PROVIDE A TA EMAIL?
        else if (email.isEmpty()) {
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            dialog.show(props.getProperty(MISSING_TA_EMAIL_TITLE), props.getProperty(MISSING_TA_EMAIL_MESSAGE));
        } // **********Check the TA Email Address for correct format 
        else if (!checkEmail.validate(email)) {
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            dialog.show(props.getProperty(INVALID_TA_EMAIL_TITLE), props.getProperty(INVALID_TA_EMAIL_MESSAGE));

        } // EVERYTHING IS FINE, ADD A NEW TA
        else {
            // ADD THE NEW TA TO THE DATA
            if (!orgName.equalsIgnoreCase(name)) { //case if only name is changed
                jTPS_Transaction transaction2 = new UpdateTA_Transaction(orgName, name, orgEmail, email, data, app, workspace);
                nameTextField.setText(name);
                emailTextField.setText(email);
                jTPS.addTransaction(transaction2);

                markWorkAsEdited();
            }
            if (!orgEmail.equalsIgnoreCase(email)) {   //case if only email is changed 
                jTPS_Transaction transaction3 = new UpdateTA_EmailOnly_Transaction(orgName, orgEmail, email, data, workspace);
                jTPS.addTransaction(transaction3);

                // data.getTA(orgName).setEmail(email);     //moved to transaction class 
                //ta.setEmail(email);
                markWorkAsEdited();

            }
            if (orgEmail.equalsIgnoreCase(email) && orgName.equalsIgnoreCase(name)) {                //case if nothing is chagned 
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(NO_UPDATE_TITLE), props.getProperty(NO_UPDATE_MESSAGE));

            }
            taTable.refresh();
            nameTextField.setText(name);
            emailTextField.setText(email);

            // AND SEND THE CARET BACK TO THE NAME TEXT FIELD FOR EASY DATA ENTRY
            nameTextField.requestFocus();
            // WE'VE CHANGED STUFF

        }
        // workspace.reloadOfficeHoursGrid(data);

    }

    public void handleTaTableRefresh() {
        CourseSiteGeneratorWorkspace workspace = (CourseSiteGeneratorWorkspace) app.getWorkspaceComponent();
        TableView taTable = workspace.getTATable();
        taTable.refresh();
    }

    public void handleUpdateTaGrid(String taName, String newName) {

        CourseSiteGeneratorWorkspace workspace = (CourseSiteGeneratorWorkspace) app.getWorkspaceComponent();
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        //data.removeTA(taName);

        // AND BE SURE TO REMOVE ALL THE TA'S OFFICE HOURS
        HashMap<String, Label> labels = workspace.getOfficeHoursGridTACellLabels();

        for (Label label : labels.values()) {   //iterates thourhg the hashmap to find all occurences of orgTA in the office hour grid
            if (label.getText().equals(taName)
                    || (label.getText().contains(taName + "\n"))
                    || (label.getText().contains("\n" + taName))) {
                data.renameTaCell(label.textProperty(), taName, newName);
            }
        }
        TableView taTable = workspace.getTATable();
        Collections.sort(data.getTeachingAssistants());  //sorts the teachingAssistants List 
        taTable.refresh();

        markWorkAsEdited();

    }

    void handleGridCellMouseExited(Pane pane) {
        String cellKey = pane.getId();
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        int column = Integer.parseInt(cellKey.substring(0, cellKey.indexOf("_")));
        int row = Integer.parseInt(cellKey.substring(cellKey.indexOf("_") + 1));
        CourseSiteGeneratorWorkspace workspace = (CourseSiteGeneratorWorkspace) app.getWorkspaceComponent();

        Pane mousedOverPane = workspace.getTACellPane(data.getCellKey(column, row));
        mousedOverPane.getStyleClass().clear();
        mousedOverPane.getStyleClass().add(CLASS_OFFICE_HOURS_GRID_TA_CELL_PANE);

        // THE MOUSED OVER COLUMN HEADER
        Pane headerPane = workspace.getOfficeHoursGridDayHeaderPanes().get(data.getCellKey(column, 0));
        headerPane.getStyleClass().remove(CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN);

        // THE MOUSED OVER ROW HEADERS
        headerPane = workspace.getOfficeHoursGridTimeCellPanes().get(data.getCellKey(0, row));
        headerPane.getStyleClass().remove(CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN);
        headerPane = workspace.getOfficeHoursGridTimeCellPanes().get(data.getCellKey(1, row));
        headerPane.getStyleClass().remove(CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN);

        // AND NOW UPDATE ALL THE CELLS IN THE SAME ROW TO THE LEFT
        for (int i = 2; i < column; i++) {
            cellKey = data.getCellKey(i, row);
            Pane cell = workspace.getTACellPane(cellKey);
            cell.getStyleClass().remove(CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN);
            cell.getStyleClass().add(CLASS_OFFICE_HOURS_GRID_TA_CELL_PANE);
        }

        // AND THE CELLS IN THE SAME COLUMN ABOVE
        for (int i = 1; i < row; i++) {
            cellKey = data.getCellKey(column, i);
            Pane cell = workspace.getTACellPane(cellKey);
            cell.getStyleClass().remove(CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN);
            cell.getStyleClass().add(CLASS_OFFICE_HOURS_GRID_TA_CELL_PANE);
        }
    }

    void handleGridCellMouseEntered(Pane pane) {
        String cellKey = pane.getId();
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        int column = Integer.parseInt(cellKey.substring(0, cellKey.indexOf("_")));
        int row = Integer.parseInt(cellKey.substring(cellKey.indexOf("_") + 1));
        CourseSiteGeneratorWorkspace workspace = (CourseSiteGeneratorWorkspace) app.getWorkspaceComponent();

        // THE MOUSED OVER PANE
        Pane mousedOverPane = workspace.getTACellPane(data.getCellKey(column, row));
        mousedOverPane.getStyleClass().clear();
        mousedOverPane.getStyleClass().add(CLASS_HIGHLIGHTED_GRID_CELL);

        // THE MOUSED OVER COLUMN HEADER
        Pane headerPane = workspace.getOfficeHoursGridDayHeaderPanes().get(data.getCellKey(column, 0));
        headerPane.getStyleClass().add(CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN);

        // THE MOUSED OVER ROW HEADERS
        headerPane = workspace.getOfficeHoursGridTimeCellPanes().get(data.getCellKey(0, row));
        headerPane.getStyleClass().add(CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN);
        headerPane = workspace.getOfficeHoursGridTimeCellPanes().get(data.getCellKey(1, row));
        headerPane.getStyleClass().add(CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN);

        // AND NOW UPDATE ALL THE CELLS IN THE SAME ROW TO THE LEFT
        for (int i = 2; i < column; i++) {
            cellKey = data.getCellKey(i, row);
            Pane cell = workspace.getTACellPane(cellKey);
            cell.getStyleClass().add(CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN);
        }

        // AND THE CELLS IN THE SAME COLUMN ABOVE
        for (int i = 1; i < row; i++) {
            cellKey = data.getCellKey(column, i);
            Pane cell = workspace.getTACellPane(cellKey);
            cell.getStyleClass().add(CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN);
        }
    }

    void handleChangeTime(String startTime, String endTime) {
        //TAWorkspace workspace = (TAWorkspace)app.getWorkspaceComponent();
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        AppYesNoCancelDialogSingleton yesNoDialog = AppYesNoCancelDialogSingleton.getSingleton();
        yesNoDialog.show(props.getProperty(UPDATE_TIME_TITLE), props.getProperty(UPDATE_TIME_MESSAGE));

        // AND NOW GET THE USER'S SELECTION
        String selection = yesNoDialog.getSelection();
        if (selection.equals(AppYesNoCancelDialogSingleton.YES)) {

            int start = convertToMilitaryTime(startTime);
            int end = convertToMilitaryTime(endTime);
            System.out.println(start);

            //TAWorkspace workspace = (TAWorkspace)app.getDataComponent();
            if (start == end || start == -1 || end == -1) {
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(INVALID_TIME_INPUT_TITLE), props.getProperty(INVALID_TIME_INPUT_MESSAGE));       //REMEMBER TO CHANGE TO PROPER ERROR MESSAGE                              

            } else if (start > end) {
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(INVALID_TIME_INPUT_TITLE), props.getProperty(INVALID_TIME_INPUT_MESSAGE));       //REMEMBER TO CHANGE TO PROPER ERROR MESSAGE                              

            } else if (end < start) {
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(INVALID_TIME_INPUT_TITLE), props.getProperty(INVALID_TIME_INPUT_MESSAGE));       //REMEMBER TO CHANGE TO PROPER ERROR MESSAGE                              

            } else {    //At this point the time varialbes are good to go. 
                CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();

                jTPS_Transaction transaction = new updateTime_Transaction(start, end, data);
                jTPS.addTransaction(transaction);

                //workspace.resetWorkspace(); 
                //workspace.reloadWorkspace(oldData);
                markWorkAsEdited();
                //workspace.reloadOfficeHoursGrid(data);
            }
        }

    }

    public int convertToMilitaryTime(String time) {
        int milTime = 0;
        if (time == null) {
            PropertiesManager props = PropertiesManager.getPropertiesManager();
            AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            dialog.show(props.getProperty(INVALID_TA_EMAIL_TITLE), props.getProperty(INVALID_TA_EMAIL_MESSAGE));       //REMEMBER TO CHANGE TO PROPER ERROR MESSAGE                              
        } else if (time.equalsIgnoreCase("12:00pm")) {
            milTime = 12;
        } else {
            int index = time.indexOf(":");
            String subStringTime = time.substring(0, index);
            milTime = Integer.parseInt(subStringTime);
            if (time.contains("p")) {
                milTime += 12;
            }
        }
        return milTime;
    }

    public void handleAddRecitation(boolean update) {
        // WE'LL NEED THE WORKSPACE TO RETRIEVE THE USER INPUT VALUES
        CourseSiteGeneratorWorkspace workspace = (CourseSiteGeneratorWorkspace) app.getWorkspaceComponent();
        TableView recitationTable = workspace.getRecitationTable();
      
            //CourseSiteGeneratorWorkspace workspace = (CourseSiteGeneratorWorkspace) app.getWorkspaceComponent();
            TextField sectionTextField = workspace.getRecitationSectionTextField();
            TextField instructorTextField = workspace.getRecitationInstructorTextField();
            TextField dayAndTimeTextField = workspace.getRecitationDayTimeTextField();
            TextField locationTextField = workspace.getRecitationLocationTextField();
            ComboBox firstTAComboBox = workspace.getRecitationFirstTABox();
            ComboBox secondTAComboBox = workspace.getRecitationSecondTABox();
            String section = sectionTextField.getText();
            String instructor = instructorTextField.getText();
            String dayAndTime = dayAndTimeTextField.getText();
            String location = locationTextField.getText();
            String firstTAName = ((TeachingAssistant)firstTAComboBox.getValue()).getName();
            String secondTAName = ((TeachingAssistant)secondTAComboBox.getValue()).getName();
        
       

        // WE'LL NEED TO ASK THE DATA SOME QUESTIONS TOO
            CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();

        // WE'LL NEED THIS IN CASE WE NEED TO DISPLAY ANY ERROR MESSAGES
            PropertiesManager props = PropertiesManager.getPropertiesManager();

       if(!update){
            System.out.println("new recitation");
            if (section.isEmpty()) {
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(MISSING_RECITATION_SECTION_TITLE), props.getProperty(MISSING_RECITATION_SECTION_MESSAGE));
            } 
            else if (instructor.isEmpty()) {
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(MISSING_RECITATION_INSTRUCTOR_TITLE), props.getProperty(MISSING_RECITATION_INSTRUCTOR_MESSAGE));
            } // DOES A TA ALREADY HAVE THE SAME NAME OR EMAIL?
            else if (dayAndTime.isEmpty()) {
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(MISSING_RECITATION_DAYTIME_TITLE), props.getProperty(MISSING_RECITATION_DAYTIME_MESSAGE));
            } // DOES
            else if (location.isEmpty()) {
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(MISSING_RECITATION_LOCATION_TITLE), props.getProperty(MISSING_RECITATION_LOCATION_MESSAGE));
            } // DOES
            else if (firstTAComboBox.getSelectionModel().isEmpty()) {
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(MISSING_RECITATION_FIRSTTA_TITLE), props.getProperty(MISSING_RECITATION_FIRSTTA_MESSAGE));
            }
            else if (secondTAComboBox.getSelectionModel().isEmpty()) {
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(MISSING_RECITATION_SECONDTA_TITLE), props.getProperty(MISSING_RECITATION_SECONDTA_MESSAGE));
            }
            else if (data.containsRecitation(section)) {
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(RECITATION_NOT_UNIQUE_TITLE), props.getProperty(RECITATION_NOT_UNIQUE_MESSAGE));
            } 
            else {
                
                jTPS_Transaction transaction1 = new AddRecitation_Transaction(section, instructor, dayAndTime, location, firstTAName, secondTAName, data);

                jTPS.addTransaction(transaction1);
                sectionTextField.setText("");
                instructorTextField.setText("");
                dayAndTimeTextField.setText("");
                locationTextField.setText("");
                sectionTextField.requestFocus();
                markWorkAsEdited();
            }
       }
        
       else if(update){
     System.out.println("update recitation");
            if (section.isEmpty()) {
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(MISSING_RECITATION_SECTION_TITLE), props.getProperty(MISSING_RECITATION_SECTION_MESSAGE));
            } 
            else if (instructor.isEmpty()) {
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(MISSING_RECITATION_INSTRUCTOR_TITLE), props.getProperty(MISSING_RECITATION_INSTRUCTOR_MESSAGE));
            } // DOES A TA ALREADY HAVE THE SAME NAME OR EMAIL?
            else if (dayAndTime.isEmpty()) {
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(MISSING_RECITATION_DAYTIME_TITLE), props.getProperty(MISSING_RECITATION_DAYTIME_MESSAGE));
            } // DOES
            else if (location.isEmpty()) {
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(MISSING_RECITATION_LOCATION_TITLE), props.getProperty(MISSING_RECITATION_LOCATION_MESSAGE));
            } // DOES
            else if (firstTAComboBox.getSelectionModel().isEmpty()) {
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(MISSING_RECITATION_FIRSTTA_TITLE), props.getProperty(MISSING_RECITATION_FIRSTTA_MESSAGE));
            }
            else if (secondTAComboBox.getSelectionModel().isEmpty()) {
                AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
                dialog.show(props.getProperty(MISSING_RECITATION_SECONDTA_TITLE), props.getProperty(MISSING_RECITATION_SECONDTA_MESSAGE));
            }
            //else if (data.containsRecitation(section)) {
             //   AppMessageDialogSingleton dialog = AppMessageDialogSingleton.getSingleton();
            //    dialog.show(props.getProperty(RECITATION_NOT_UNIQUE_TITLE), props.getProperty(RECITATION_NOT_UNIQUE_MESSAGE));
           // } 
            else {
                
                jTPS_Transaction transaction1 = new UpdateRecitation_Transaction(recitationTable, section, instructor, dayAndTime, location, firstTAName, secondTAName, data);

                jTPS.addTransaction(transaction1);
                System.out.println("updateable");
                //jTPS.doTransaction();
                // CLEAR THE TEXT FIELDS
                sectionTextField.setText("");
                instructorTextField.setText("");
                dayAndTimeTextField.setText("");
                locationTextField.setText("");

                // AND SEND THE CARET BACK TO THE NAME TEXT FIELD FOR EASY DATA ENTRY
                sectionTextField.requestFocus();
                // WE'VE CHANGED STUFF
                markWorkAsEdited();
            }
            
        }
    }
    public void handleAddTeam(boolean update){
        CourseSiteGeneratorWorkspace workspace = (CourseSiteGeneratorWorkspace) app.getWorkspaceComponent();
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        TextField nameTextField = workspace.getTeamNameTextField();
        ColorPicker teamColorPicker = workspace.getTeamColorPicker();
        ColorPicker teamTextColorPicker = workspace.getTeamTextColorPicker();
        TextField teamLinkTextField = workspace.getTeamLinkTextField();
        
        String name = nameTextField.getText();
        String color = teamColorPicker.getId();
        String textColor = teamTextColorPicker.getId();
        String link = teamLinkTextField.getText();
        
        
        if(!update){
            TableView table = workspace.getScheduleTable();
            jTPS_Transaction transaction1 = new AddTeam_Transaction(name, color, textColor, link, data);
            jTPS.addTransaction(transaction1);
            table.refresh();
        }
        else if(update){
           
        }
        
    }
    public void handleAddSchedule(boolean update) {
        
        CourseSiteGeneratorWorkspace workspace = (CourseSiteGeneratorWorkspace) app.getWorkspaceComponent();
        CourseSiteGeneratorData data = (CourseSiteGeneratorData) app.getDataComponent();
        ComboBox typeComboBox = workspace.getScheduleTypeComboBox();
        DatePicker datePicker = workspace.getScheduleDatePicker();
        TextField timeTextField = workspace.getScheduleTimeTextField();
        TextField titleTextField = workspace.getScheduleTitleTextField();
        TextField topicTextField = workspace.getScheduleTopicTextField();
        TextField linkTextField = workspace.getScheduleLinkTextField();
        TextField criteriaTextField = workspace.getScheduleCriteriaTextField();
        
        String type = (String)typeComboBox.getSelectionModel().getSelectedItem();
        String time = timeTextField.getText();
        String title = titleTextField.getText();
        String topic = topicTextField.getText();
        String link = linkTextField.getText();
        String criteria = criteriaTextField.getText();
        String date = workspace.getScheduleDatePicker().getValue().toString();
        if(!update){
            TableView table = workspace.getScheduleTable();
            jTPS_Transaction transaction1 = new AddSchedule_Transaction(type,date,time,title,topic,link,criteria, data);
            jTPS.addTransaction(transaction1);
            table.refresh();
        }
        else if(update){
            System.out.println("UPDATING SCHEDULE");
            TableView table = workspace.getScheduleTable();
            Schedule selectedSchedule = (Schedule)table.getSelectionModel().getSelectedItem();
            String removeDate = selectedSchedule.getDate();
            //data.removeSchedule(selectedSchedule.getDate());
           // data.addScheduleItem(type,date,time,title,topic,link,criteria);
            table.refresh();
            jTPS_Transaction transaction1 = new UpdateSchedule_Transaction(selectedSchedule, removeDate,type,date,time,title,topic,link,criteria, data);
            jTPS.addTransaction(transaction1);
        }
        timeTextField.setText("");
        titleTextField.setText("");
        topicTextField.setText("");
        linkTextField.setText("");
        criteriaTextField.setText("");
        typeComboBox.getSelectionModel().clearSelection();
        datePicker.setValue(null);
        markWorkAsEdited();
    }
        
}
