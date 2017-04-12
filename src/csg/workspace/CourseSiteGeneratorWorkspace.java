/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.workspace;


import djf.components.AppDataComponent;
import djf.components.AppWorkspaceComponent;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import csg.CourseSiteGeneratorApp;
import djf.components.AppDataComponent;
import djf.components.AppWorkspaceComponent;
import static djf.settings.AppPropertyType.SAVE_UNSAVED_WORK_MESSAGE;
import static djf.settings.AppPropertyType.SAVE_UNSAVED_WORK_TITLE;
import djf.ui.AppGUI;
import djf.ui.AppMessageDialogSingleton;
import djf.ui.AppYesNoCancelDialogSingleton;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.FlowPane;
import properties_manager.PropertiesManager;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ColorPicker;
/**
 *
 * @author benjaminzhuo
 */
public class CourseSiteGeneratorWorkspace extends AppWorkspaceComponent{

    CourseSiteGeneratorApp app;
    
    //ALL THE TABS AND THE TABPANE
    TabPane tabPane;
    Tab taTab;
    Tab recitationTab;
    Tab projectTab;
    Tab scheduleTab;
    Tab courseTab;
    
    //COURSE DETAILS PAGE
    GridPane courseInfoPane;
    GridPane siteTemplatePane;
    GridPane pageStylePane;
    VBox courseDetailsPane;
    Label courseInfoLabel;
    Label subjectLabel;
    Label numberLabel;
    ComboBox subjectComboBox;
    ComboBox numberComboBox;
    Label semesterLabel;
    Label yearLabel;
    ComboBox semesterComboBox;
    ComboBox yearComboBox;
    Label titleLabel;
    TextField titleTextField;
    Label instructorNameLabel;
    TextField instructorNameTextField;
    Label instructorHomeLabel;
    TextField instructorHomeTextField;
    Label exportDirectoryLabel;
    Label exportDirectoryLinkLabel;
    Button exportChangeButton;
    
    //Site Template Section
    Label siteTemplateLabel;
    Label siteTemplateInfoLabel;
    Label siteTemplateDirectory;
    Button selectTemplateDirectoryButton;
    Label sitePagesLabel;
    TableView sitePages;
    TableColumn useColumn;
    TableColumn navbarTitleColumn;
    TableColumn fileNameColumn;
    TableColumn scriptColumn;
    
    //Page Style Section
    Label pageStyleLabel;
    Label bannerSchoolImageLabel;
    Label leftFooterImageLabel;
    Label rightFooterImageLabel;
    Button bannerChange;
    Button leftFooterChange;
    Button rightFooterChange;
    Label stylesheetLabel;
    ComboBox stylesheetComboBox;
    Label noteLabel;
    
    ////////////////////////////////RECITATION///////////////////////////////////
    
    VBox recitationPagePane;
    GridPane recitationDataPane;
    Label recitationLabel;
    TableView recitations;
    TableColumn section;
    TableColumn instructor;
    TableColumn dayAndTime;
    TableColumn location;
    TableColumn firstTA;
    TableColumn secondTA;
    Label addEditLabel;
    Label sectionLabel;
    Label instructorLabel;
    Label dayAndTimeLabel;
    Label locationLabel;
    Label supervisingTALabel;
    Label secondSupervisingTALabel;
    TextField sectionTextField;
    TextField instructorTextField;
    TextField dayAndTimeTextField;
    TextField locationTextField;
    ComboBox firstTAComboBox;
    ComboBox secondTAComboBox;
    Button recitationAddUpdateButton;
    Button recitationClearButton;
    ////////////////////////////////Schedule///////////////////////////////////
    
    VBox schedulePagePane;
    Label scheduleLabel;
    Label calendarBoundariesLabel;
    Label startingMondayLabel;
    Label endingFridayLabel;
    Label scheduleItemsLabel;
    TableColumn typeColumn;
    TableColumn dateColumn;
    TableColumn titleColumn;
    TableColumn topicColumn;
    TableView scheduleItemsTable;
    Label scheduleAddEditLabel;
    Label typeLabel;
    Label dateLabel;
    Label timeLabel;
    Label scheduleTitleLabel;
    Label topicLabel;
    Label linkLabel;
    Label criteriaLabel;
    Button scheduleAddUpdateButton;
    Button scheduleClearButton;
    ComboBox typeComboBox;
    DatePicker scheduleDate;
    DatePicker mondayDate;
    DatePicker fridayDate;
    TextField scheduleTimeTextField;
    TextField scheduleTitleTextField;
    TextField scheduleTopicTextField;
    TextField scheduleLinkTextField;
    TextField scheduleCriteriaTextField;
    GridPane calendarBoundariesPane;
    GridPane scheduleItemsPane;
    ///////////////////projects/////////////////////////
    VBox projectsPane;
    Label projectsHeader;
   
    ///////////
    GridPane teamsPane;
    Label teamsHeaderLabel;
    TableView teamsTable;
    TableColumn teamsNameColumn;
    TableColumn teamsColorColumn;
    TableColumn teamsTextColorColumn;
    TableColumn teamsLinkColumn;
    Label teamsAddEditLabel;
    Label teamsNameLabel;
    Label teamsColorLabel;
    Label teamsTextColorLabel;
    Label teamsLinkLabel;
    Button teamsAddUpdateButton;
    Button teamsClearButton;
    TextField teamsNameTextField;
    TextField teamsLinkTextField;
    ColorPicker teamsColorColorPicker;
    ColorPicker teamsTextColorPicker;
    
   
    //
    GridPane studentsPane;
    Label studentsHeaderLabel;
    TableView studentsTable;
    TableColumn studentsFirstNameColumn;
    TableColumn studentsLastNameColumn;
    TableColumn studentsTeamColumn;
    TableColumn studentsRoleColumn;
    Label studentsAddEditLabel;
    Label studentsFirstNameLabel;
    TextField studentsFirstNameTextField;
    Label studentsLastNameLabel;
    TextField studentsLastNameTextField;
    Label studentsTeamLabel;
    ComboBox studentsTeamComboBox;
    Label studentsRoleLabel;
    TextField studentsRoleTextField;
    Button studentsAddUpdateButton;
    Button studentsClearButton;
    
    
    
    
    
    
    public CourseSiteGeneratorWorkspace(CourseSiteGeneratorApp initApp) {
        
        
        
        app = initApp;
        
        //USED TO GET LABELS AND SUCH
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        
        //INITIALIZE THE TABPANE AND THE TABS
        tabPane = new TabPane();
        taTab = new Tab();
        recitationTab = new Tab();
        projectTab = new Tab();
        scheduleTab = new Tab();
        courseTab = new Tab();
        taTab.setText("TA Data");
        courseTab.setText("Course Details");
        scheduleTab.setText("Schedule Data");
        projectTab.setText("Project Data");
        recitationTab.setText("Recitation Data");
        
        tabPane.getTabs().addAll(courseTab, taTab, recitationTab,scheduleTab, projectTab);
        AppGUI gui = app.getGUI();
        gui.getAppPane().setCenter(tabPane);    
        
        //Course Info section
        courseInfoPane = new GridPane();
        
        //First Line
        courseInfoLabel = new Label();
        courseInfoLabel.setText("Course Info");
        courseInfoPane.add(courseInfoLabel, 0, 0);
        
        //Second Line
        subjectLabel = new Label();
        subjectLabel.setText("Subject: ");
        subjectComboBox = new ComboBox();
        numberLabel = new Label();
        numberLabel.setText("Number: ");
        numberComboBox = new ComboBox();
        courseInfoPane.add(subjectLabel, 0, 1);
        courseInfoPane.add(subjectComboBox, 1,1);
        courseInfoPane.add(numberLabel, 2, 1);
        courseInfoPane.add(numberComboBox, 3, 1);
       
        //Third Line
        semesterLabel = new Label();
        semesterLabel.setText("Semester: ");
        semesterComboBox = new ComboBox();
        yearLabel = new Label();
        yearLabel.setText("Year: ");
        yearComboBox = new ComboBox();
        courseInfoPane.add(semesterLabel, 0, 2);
        courseInfoPane.add(semesterComboBox, 1, 2);
        courseInfoPane.add(yearLabel, 2, 2);
        courseInfoPane.add(yearComboBox, 3, 2);
        
        //Fourth Line
        titleLabel = new Label();
        titleLabel.setText("Title: ");
        titleTextField = new TextField();
        courseInfoPane.add(titleLabel, 0, 3);
        courseInfoPane.add(titleTextField, 1, 3);
        
        //Fifth Line
        instructorNameLabel = new Label();
        instructorNameLabel.setText("Instructor Name: ");
        instructorNameTextField = new TextField();
        courseInfoPane.add(instructorNameLabel, 0, 4);
        courseInfoPane.add(instructorNameTextField, 1, 4);
        
        //Sixth Line
        instructorHomeLabel = new Label();
        instructorHomeLabel.setText("Intructor Home:");
        instructorHomeTextField = new TextField();
        courseInfoPane.add(instructorHomeLabel, 0, 5);
        courseInfoPane.add(instructorHomeTextField, 1, 5);
       
        //SeventhLine
        exportDirectoryLabel = new Label();
        exportDirectoryLinkLabel = new Label();
        exportDirectoryLabel.setText("Export Directory: ");
        exportChangeButton = new Button();
        exportChangeButton.setText("Change");
        courseInfoPane.add(exportDirectoryLabel, 0, 6);
        courseInfoPane.add(exportDirectoryLinkLabel, 1, 6);
        courseInfoPane.add(exportChangeButton, 2, 6);
        
        //Site Template Section
        siteTemplatePane = new GridPane();
        siteTemplateLabel = new Label();
        siteTemplateLabel.setText("Site Template");
        siteTemplatePane.add(siteTemplateLabel, 0, 0);
        siteTemplateInfoLabel = new Label();
        siteTemplateInfoLabel.setText("The selected directory should containthe full site template, including the HTML files");
        siteTemplatePane.add(siteTemplateInfoLabel, 0, 1);
        siteTemplateDirectory = new Label();
        siteTemplateDirectory.setText(".templatescse219");
        siteTemplatePane.add(siteTemplateDirectory, 0, 2);
        selectTemplateDirectoryButton = new Button();
        selectTemplateDirectoryButton.setText("Select Template Directory");
        siteTemplatePane.add(selectTemplateDirectoryButton, 0, 3);
        sitePagesLabel = new Label();
        sitePagesLabel.setText("Site Pages: ");
        siteTemplatePane.add(sitePagesLabel, 0 , 4);
        sitePages = new TableView();
        sitePages.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        siteTemplatePane.add(sitePages, 0, 5);
        //Set up Table
        useColumn = new TableColumn("Use");
        navbarTitleColumn = new TableColumn("Navbar Title");
        fileNameColumn = new TableColumn("File Name");
        scriptColumn = new TableColumn("Script");
        sitePages.getColumns().add(useColumn);
        sitePages.getColumns().add(navbarTitleColumn);
        sitePages.getColumns().add(fileNameColumn);
        sitePages.getColumns().add(scriptColumn);
        
        
       
        //Page Style Section
        pageStylePane = new GridPane();
        pageStyleLabel = new Label();
        pageStyleLabel.setText("Page Style");
        pageStylePane.add(pageStyleLabel, 0, 0);
        bannerSchoolImageLabel = new Label();
        bannerSchoolImageLabel.setText("Banner School Image");
        pageStylePane.add(bannerSchoolImageLabel, 0, 1);
        bannerChange = new Button();
        bannerChange.setText("Change");
        pageStylePane.add(bannerChange, 2, 1);
        leftFooterImageLabel = new Label();
        leftFooterImageLabel.setText("Left Footer Image");
        leftFooterChange = new Button();
        leftFooterChange.setText("Change");
        pageStylePane.add(leftFooterChange, 2, 2);
        pageStylePane.add(leftFooterImageLabel, 0, 2);
        rightFooterImageLabel = new Label();
        rightFooterImageLabel.setText("Right Footer Image");
        rightFooterChange = new Button();
        rightFooterChange.setText("Change");
        pageStylePane.add(rightFooterChange, 2, 3);
        pageStylePane.add(rightFooterImageLabel, 0, 3);
        stylesheetLabel = new Label();
        stylesheetLabel.setText("Stylesheet");
        stylesheetComboBox = new ComboBox();
        pageStylePane.add(stylesheetLabel, 0, 4);
        pageStylePane.add(stylesheetComboBox, 1, 4);
        noteLabel = new Label();
        noteLabel.setText("New stylesheets may be placed "
                + "in work/css to be selectable");
        pageStylePane.add(noteLabel, 0 , 5);
        
        
        courseDetailsPane = new VBox();
        courseDetailsPane.getChildren().add(courseInfoPane);
        courseDetailsPane.getChildren().add(siteTemplatePane);
        courseDetailsPane.getChildren().add(pageStylePane);
       // courseTab.setContent(courseDetailsPane);
        
        
        recitationDataPane = new GridPane();
        recitationLabel = new Label();
        recitationLabel.setText("Recitation");
        recitationDataPane.add(recitationLabel, 0, 0);
        recitations = new TableView();
        recitationDataPane.add(recitations, 0, 1);
        addEditLabel = new Label();
        addEditLabel.setText("Add/Edit");
        recitationDataPane.add(addEditLabel, 0, 2);
        sectionLabel = new Label();
        sectionLabel.setText("Section: ");
        recitationDataPane.add(sectionLabel, 0 , 3);
        sectionTextField = new TextField();
        recitationDataPane.add(sectionTextField, 1, 3);
        instructorLabel = new Label();
        instructorLabel.setText("Instructor: ");
        recitationDataPane.add(instructorLabel, 0 , 4);
        instructorTextField = new TextField();
        recitationDataPane.add(instructorTextField, 1, 4);
        dayAndTimeLabel = new Label();
        dayAndTimeLabel.setText("Day/Time");
        dayAndTimeTextField = new TextField();
        recitationDataPane.add(dayAndTimeTextField, 1, 5);
        recitationDataPane.add(dayAndTimeLabel, 0 , 5);
        locationLabel = new Label();
        locationLabel.setText("Location:");
        locationTextField = new TextField();
        recitationDataPane.add(locationTextField, 1, 6);
        recitationDataPane.add(locationLabel, 0 , 6);
        supervisingTALabel = new Label();
        supervisingTALabel.setText("Supervising TA");
        secondSupervisingTALabel = new Label();
        secondSupervisingTALabel.setText("Supervising TA");
        recitationDataPane.add(supervisingTALabel, 0 , 7);
        recitationDataPane.add(secondSupervisingTALabel, 0 , 8);
        firstTAComboBox = new ComboBox();
        secondTAComboBox = new ComboBox();
        recitationDataPane.add(firstTAComboBox, 1, 7);
        recitationDataPane.add(secondTAComboBox, 1, 8); 
        
        recitationAddUpdateButton = new Button();
        recitationAddUpdateButton.setText("Add/Update");
        recitationClearButton = new Button();
        recitationClearButton.setText("Clear");
        recitationDataPane.add(recitationAddUpdateButton, 0, 9);
        recitationDataPane.add(recitationClearButton, 1, 9);
    
        
      
       recitationTab.setContent(recitationDataPane);
       courseTab.setContent(courseDetailsPane);
       
       //////////////////////////////////SCHEDULE/////////////////////////////////////
       schedulePagePane = new VBox();
       calendarBoundariesPane = new GridPane();
       scheduleItemsPane = new GridPane();
       
       calendarBoundariesLabel = new Label();
       calendarBoundariesLabel.setText("Calendar Boundaries");
       startingMondayLabel = new Label();
       startingMondayLabel.setText("Starting Monday: ");
       endingFridayLabel = new Label();
       endingFridayLabel.setText("Ending Friday: ");
       mondayDate = new DatePicker();
       fridayDate = new DatePicker();
       calendarBoundariesPane.add(calendarBoundariesLabel,0,0);
       calendarBoundariesPane.add(startingMondayLabel,0,1);
       calendarBoundariesPane.add(mondayDate,1,1);
       calendarBoundariesPane.add(endingFridayLabel,2, 1);
       calendarBoundariesPane.add(fridayDate, 3, 1);
       
       //Schedule Items section
       scheduleItemsLabel = new Label();
       scheduleItemsLabel.setText("Schedule Items");
       //SET UP THE TABLE
       scheduleItemsTable = new TableView();
       typeColumn = new TableColumn("Type");
       dateColumn = new TableColumn("Date");
       titleColumn = new TableColumn("Title");
       topicColumn = new TableColumn("Topic");
       scheduleItemsTable.getColumns().add(typeColumn);
       scheduleItemsTable.getColumns().add(dateColumn);
       scheduleItemsTable.getColumns().add(titleColumn);
       scheduleItemsTable.getColumns().add(topicColumn);
       scheduleItemsPane.add(scheduleItemsLabel, 0, 0);
       scheduleItemsPane.add(scheduleItemsTable, 0, 1);
       
       scheduleAddEditLabel = new Label();
       scheduleAddEditLabel.setText("ADD/EDIT");
       typeLabel = new Label();
       typeLabel.setText("Type: ");
       dateLabel = new Label();
       typeComboBox = new ComboBox();
       scheduleItemsPane.add(scheduleAddEditLabel, 0, 2);
       dateLabel.setText("Date: ");
       timeLabel = new Label();
       timeLabel.setText("Time: ");
       titleLabel = new Label();
       titleLabel.setText("Title: ");
       topicLabel = new Label();
       topicLabel.setText("Topic: ");
       linkLabel = new Label();
       linkLabel.setText("Link: ");
       criteriaLabel = new Label();
       criteriaLabel.setText("Criteria: ");
       scheduleDate = new DatePicker();
       scheduleTimeTextField = new TextField();
       scheduleTitleTextField = new TextField();
       scheduleTopicTextField = new TextField();
       scheduleLinkTextField = new TextField();
       scheduleCriteriaTextField = new TextField();
       
       
       
       
       scheduleItemsPane.add(typeLabel,0,3);
       scheduleItemsPane.add(dateLabel,0,4);
       scheduleItemsPane.add(timeLabel,0,5);
       scheduleItemsPane.add(titleLabel,0,6);
       scheduleItemsPane.add(topicLabel,0,7);
       scheduleItemsPane.add(linkLabel,0,8);
       scheduleItemsPane.add(criteriaLabel,0,9);
       scheduleItemsPane.add(typeComboBox, 1,3);
       scheduleItemsPane.add(scheduleDate, 1,4);
       scheduleItemsPane.add(scheduleTimeTextField, 1,5);
       scheduleItemsPane.add(scheduleTitleTextField, 1,6);
       scheduleItemsPane.add(scheduleTopicTextField, 1,7);
       scheduleItemsPane.add(scheduleLinkTextField, 1,8);
       scheduleItemsPane.add(scheduleCriteriaTextField, 1,9);
       
       schedulePagePane.getChildren().add(calendarBoundariesPane);
       schedulePagePane.getChildren().add(scheduleItemsPane);
       scheduleTab.setContent(schedulePagePane);
       
       //////////////////////////////PROJECTs//////////////////
       
       //Initialize all the panes
       projectsPane = new VBox();
       teamsPane = new GridPane();
       studentsPane = new GridPane();
       
       projectsHeader = new Label();
       projectsHeader.setText("Projects");
       projectsPane.getChildren().add(projectsHeader);
       
       teamsHeaderLabel = new Label();
       teamsHeaderLabel.setText("Teams");
       teamsTable = new TableView();
       teamsNameColumn = new TableColumn();
       teamsNameColumn.setText("Name");
       teamsColorColumn = new TableColumn();
       teamsColorColumn.setText("Color (hex#)");
       teamsTextColorColumn = new TableColumn();
       teamsTextColorColumn.setText("Text Color (hex#)");
       teamsLinkColumn = new TableColumn();
       teamsLinkColumn.setText("Link");
       teamsTable.getColumns().addAll(teamsNameColumn,
               teamsColorColumn, 
               teamsTextColorColumn,
               teamsLinkColumn);
       teamsPane.add(teamsHeaderLabel,0,0);
       teamsPane.add(teamsTable,0,1);
       teamsAddEditLabel = new Label();
       teamsAddEditLabel.setText("Add/Edit");
       teamsNameLabel = new Label();
       teamsNameLabel.setText("Name: ");
       teamsColorLabel = new Label();
       teamsColorLabel.setText("Color: ");
       teamsColorColorPicker = new ColorPicker();
       teamsTextColorPicker = new ColorPicker();
       teamsTextColorLabel = new Label();
       teamsTextColorLabel.setText("Text Color: ");
       teamsLinkLabel = new Label();
       teamsLinkLabel.setText("Link");
       teamsLinkTextField = new TextField();
       teamsAddUpdateButton = new Button();
       teamsAddUpdateButton.setText("Add/Update");
       teamsClearButton = new Button();
       teamsClearButton.setText("Clear");
       teamsPane.add(teamsAddEditLabel, 0,2);
       teamsPane.add(teamsNameLabel,0,3);
       teamsNameTextField = new TextField();
       teamsPane.add(teamsNameTextField,1,3);
       teamsPane.add(teamsColorLabel,0,4);
       teamsPane.add(teamsColorColorPicker,1,4);
       teamsPane.add(teamsTextColorLabel,2,4);
       teamsPane.add(teamsTextColorPicker,3,4);
       teamsPane.add(teamsLinkLabel,0,5);
       teamsPane.add(teamsLinkTextField,1,5);
       teamsPane.add(teamsAddUpdateButton, 0,6);
       teamsPane.add(teamsClearButton,1,6);
       //////Students pane
       
       studentsHeaderLabel = new Label();
       studentsHeaderLabel.setText("Students");
       studentsTable = new TableView();
       studentsFirstNameColumn = new TableColumn();
       studentsFirstNameColumn.setText("First Name");
       studentsLastNameColumn = new TableColumn();
       studentsLastNameColumn.setText("Last Name");
       studentsTeamColumn = new TableColumn();
       studentsTeamColumn.setText("Team");
       studentsRoleColumn = new TableColumn();
       studentsRoleColumn.setText("Role");
       studentsTable.getColumns().addAll(
            studentsFirstNameColumn,
            studentsLastNameColumn,
            studentsTeamColumn,
            studentsRoleColumn);
       studentsPane.add(studentsHeaderLabel,0,0);
       studentsPane.add(studentsTable,0,1);
       studentsAddEditLabel = new Label();
       studentsAddEditLabel.setText("Add/Edit");
       studentsFirstNameLabel = new Label();
       studentsFirstNameLabel.setText("First Name:");
       studentsLastNameLabel = new Label();
       studentsLastNameLabel.setText("Last Name: ");
       studentsTeamLabel= new Label();
       studentsTeamLabel.setText("Team: ");
       studentsRoleLabel = new Label();
       studentsRoleLabel.setText("Role: ");
       studentsFirstNameTextField = new TextField();
       studentsLastNameTextField = new TextField();
       studentsTeamComboBox = new ComboBox();
       studentsRoleTextField = new TextField();
       studentsAddUpdateButton = new Button();
       studentsAddUpdateButton.setText("Add/Update");
       studentsClearButton = new Button();
       studentsClearButton.setText("Clear");
       studentsPane.add(studentsAddEditLabel,0,2);
       studentsPane.add(studentsFirstNameLabel,0,3);
       studentsPane.add(studentsFirstNameTextField,1,3);
       studentsPane.add(studentsLastNameLabel,0,4);
       studentsPane.add(studentsLastNameTextField,1,4);
       studentsPane.add(studentsTeamLabel,0,5);
       studentsPane.add(studentsTeamComboBox,1,5);
       studentsPane.add(studentsRoleLabel,0,6);
       studentsPane.add(studentsRoleTextField,1,6);
       studentsPane.add(studentsAddUpdateButton,0,7);
       studentsPane.add(studentsClearButton,1,7);
       
       projectsPane.getChildren().add(teamsPane);
       projectsPane.getChildren().add(studentsPane);
       projectTab.setContent(projectsPane);
       
       
       
       
       
       
    }

    @Override
    public void resetWorkspace() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void reloadWorkspace(AppDataComponent dataComponent) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
