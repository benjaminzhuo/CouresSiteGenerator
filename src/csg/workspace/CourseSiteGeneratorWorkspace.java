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
import csg.CourseSiteGeneratorProp;
import csg.data.TAData;
import csg.data.TeachingAssistant;
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
    ////////////////////////////////TA DATA////////////////////////////
     CourseSiteGeneratorController controller;

    // NOTE THAT EVERY CONTROL IS PUT IN A BOX TO HELP WITH ALIGNMENT
    
    // FOR THE HEADER ON THE LEFT
    HBox tasHeaderBox;
    Label tasHeaderLabel;
    
    // FOR THE TA TABLE
    TableView<TeachingAssistant> taTable;
    TableColumn<TeachingAssistant, String> nameColumn;
    TableColumn<TeachingAssistant, String> emailColumn;

    // THE TA INPUT
    HBox addBox;
    TextField nameTextField;
    TextField emailTextField;
    Button addButton;
    Button clearButton;

    // THE HEADER ON THE RIGHT
    HBox officeHoursHeaderBox;
    Label officeHoursHeaderLabel;
    
    ObservableList<String> time_options;
    ComboBox comboBox1;
    ComboBox comboBox2;
    
    // THE OFFICE HOURS GRID
    GridPane officeHoursGridPane;
    HashMap<String, Pane> officeHoursGridTimeHeaderPanes;
    HashMap<String, Label> officeHoursGridTimeHeaderLabels;
    HashMap<String, Pane> officeHoursGridDayHeaderPanes;
    HashMap<String, Label> officeHoursGridDayHeaderLabels;
    HashMap<String, Pane> officeHoursGridTimeCellPanes;
    HashMap<String, Label> officeHoursGridTimeCellLabels;
    HashMap<String, Pane> officeHoursGridTACellPanes;
    HashMap<String, Label> officeHoursGridTACellLabels;
    
    
    
    
    
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
        taTab.setText(props.getProperty(CourseSiteGeneratorProp.TA_TAB_HEADER_TEXT.toString()));
        courseTab.setText(props.getProperty(CourseSiteGeneratorProp.COURSE_TAB_HEADER_TEXT.toString()));
        scheduleTab.setText(props.getProperty(CourseSiteGeneratorProp.SCHEDULE_TAB_HEADER_TEXT.toString()));
        projectTab.setText(props.getProperty(CourseSiteGeneratorProp.PROJECT_TAB_HEADER_TEXT.toString()));
        recitationTab.setText(props.getProperty(CourseSiteGeneratorProp.RECITATION_TAB_HEADER_TEXT.toString()));
        
        AppGUI gui = app.getGUI();
       
        tabPane.setTabMinWidth(gui.getAppPane().getWidth()/6);
        
        tabPane.getTabs().addAll(courseTab, taTab, recitationTab,scheduleTab, projectTab);
        
        gui.getAppPane().setCenter(tabPane);
        
        
        
        //Course Info section
        courseInfoPane = new GridPane();
        
        //First Line
        courseInfoLabel = new Label();
        courseInfoLabel.setText(props.getProperty(CourseSiteGeneratorProp.COURSEINFO_HEADER_TEXT.toString()));
        courseInfoPane.add(courseInfoLabel, 0, 0);
        
        //Second Line
        subjectLabel = new Label();
        subjectLabel.setText(props.getProperty(CourseSiteGeneratorProp.COURSEINFO_SUBJECT_TEXT.toString()));
        subjectComboBox = new ComboBox();
        numberLabel = new Label();
        numberLabel.setText(props.getProperty(CourseSiteGeneratorProp.COURSEINFO_NUMBER_TEXT.toString()));
        numberComboBox = new ComboBox();
        courseInfoPane.add(subjectLabel, 0, 1);
        courseInfoPane.add(subjectComboBox, 1,1);
        courseInfoPane.add(numberLabel, 2, 1);
        courseInfoPane.add(numberComboBox, 3, 1);
       
        //Third Line
        semesterLabel = new Label();
        semesterLabel.setText(props.getProperty(CourseSiteGeneratorProp.COURSEINFO_SEMESTER_TEXT.toString()));
        semesterComboBox = new ComboBox();
        yearLabel = new Label();
        yearLabel.setText(props.getProperty(CourseSiteGeneratorProp.COURSEINFO_YEAR_TEXT.toString()));
        yearComboBox = new ComboBox();
        courseInfoPane.add(semesterLabel, 0, 2);
        courseInfoPane.add(semesterComboBox, 1, 2);
        courseInfoPane.add(yearLabel, 2, 2);
        courseInfoPane.add(yearComboBox, 3, 2);
        
        //Fourth Line
        titleLabel = new Label();
        titleLabel.setText(props.getProperty(CourseSiteGeneratorProp.COURSEINFO_TITLE_TEXT.toString()));
        titleTextField = new TextField();
        courseInfoPane.add(titleLabel, 0, 3);
        courseInfoPane.add(titleTextField, 1, 3);
        
        //Fifth Line
        instructorNameLabel = new Label();
        instructorNameLabel.setText(props.getProperty(CourseSiteGeneratorProp.COURSEINFO_INSTRUCTORNAME_TEXT.toString()));
        instructorNameTextField = new TextField();
        courseInfoPane.add(instructorNameLabel, 0, 4);
        courseInfoPane.add(instructorNameTextField, 1, 4);
        
        //Sixth Line
        instructorHomeLabel = new Label();
        instructorHomeLabel.setText(props.getProperty(CourseSiteGeneratorProp.COURSEINFO_INSTRUCTORHOME_TEXT.toString()));
        instructorHomeTextField = new TextField();
        courseInfoPane.add(instructorHomeLabel, 0, 5);
        courseInfoPane.add(instructorHomeTextField, 1, 5);
       
        //SeventhLine
        exportDirectoryLabel = new Label();
        exportDirectoryLinkLabel = new Label();
        exportDirectoryLabel.setText(props.getProperty(CourseSiteGeneratorProp.COURSEINFO_EXPORTDIRECTORY_TEXT.toString()));
        exportChangeButton = new Button();
        exportChangeButton.setText(props.getProperty(CourseSiteGeneratorProp.COURSEINFO_CHANGEBUTTON_TEXT.toString()));
        courseInfoPane.add(exportDirectoryLabel, 0, 6);
        courseInfoPane.add(exportDirectoryLinkLabel, 1, 6);
        courseInfoPane.add(exportChangeButton, 2, 6);
        
        //Site Template Section
        siteTemplatePane = new GridPane();
        siteTemplateLabel = new Label();
        siteTemplateLabel.setText(props.getProperty(CourseSiteGeneratorProp.SITETEMPLATE_HEADER_TEXT.toString()));
        siteTemplatePane.add(siteTemplateLabel, 0, 0);
        siteTemplateInfoLabel = new Label();
        siteTemplateInfoLabel.setText(props.getProperty(CourseSiteGeneratorProp.SITETEMPLATE_INFOLABEL_TEXT.toString()));
        siteTemplatePane.add(siteTemplateInfoLabel, 0, 1);
        siteTemplateDirectory = new Label();
        siteTemplateDirectory.setText(props.getProperty(CourseSiteGeneratorProp.SITETEMPLATE_DIRECTORY_TEXT.toString()));
        siteTemplatePane.add(siteTemplateDirectory, 0, 2);
        selectTemplateDirectoryButton = new Button();
        selectTemplateDirectoryButton.setText(props.getProperty(CourseSiteGeneratorProp.SITETEMPLATE_DIRECTORYBUTTON_TEXT.toString()));
        siteTemplatePane.add(selectTemplateDirectoryButton, 0, 3);
        sitePagesLabel = new Label();
        sitePagesLabel.setText(props.getProperty(CourseSiteGeneratorProp.SITETEMPLATE_PAGES_TEXT.toString()));
        siteTemplatePane.add(sitePagesLabel, 0 , 4);
        sitePages = new TableView();
        sitePages.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        siteTemplatePane.add(sitePages, 0, 5);
        //Set up Table
        useColumn = new TableColumn(props.getProperty(CourseSiteGeneratorProp.SITETEMPLATE_USECOLUMN_TEXT.toString()));
        navbarTitleColumn = new TableColumn(props.getProperty(CourseSiteGeneratorProp.SITETEMPLATE_NAVBARCOLUMN_TEXT.toString()));
        fileNameColumn = new TableColumn(props.getProperty(CourseSiteGeneratorProp.SITETEMPLATE_FILENAMECOLUMN_TEXT.toString()));
        scriptColumn = new TableColumn(props.getProperty(CourseSiteGeneratorProp.SITETEMPLATE_SCRIPTCOLUMN_TEXT.toString()));
        sitePages.getColumns().add(useColumn);
        sitePages.getColumns().add(navbarTitleColumn);
        sitePages.getColumns().add(fileNameColumn);
        sitePages.getColumns().add(scriptColumn);
        
        
       
        //Page Style Section
        pageStylePane = new GridPane();
        pageStyleLabel = new Label();
        pageStyleLabel.setText(props.getProperty(CourseSiteGeneratorProp.PAGESTYLE_HEADERLABEL_TEXT.toString()));
        pageStylePane.add(pageStyleLabel, 0, 0);
        bannerSchoolImageLabel = new Label();
        bannerSchoolImageLabel.setText(props.getProperty(CourseSiteGeneratorProp.PAGESTYLE_BANNERLABEL_TEXT.toString()));
        pageStylePane.add(bannerSchoolImageLabel, 0, 1);
        bannerChange = new Button();
        bannerChange.setText(props.getProperty(CourseSiteGeneratorProp.PAGESTYLE_BANNERBUTTON_TEXT.toString()));
        pageStylePane.add(bannerChange, 2, 1);
        leftFooterImageLabel = new Label();
        leftFooterImageLabel.setText(props.getProperty(CourseSiteGeneratorProp.PAGESTYLE_LEFTFOOTERLABEL_TEXT.toString()));
        leftFooterChange = new Button();
        leftFooterChange.setText(props.getProperty(CourseSiteGeneratorProp.PAGESTYLE_LEFTBUTTON_TEXT.toString()));
        pageStylePane.add(leftFooterChange, 2, 2);
        pageStylePane.add(leftFooterImageLabel, 0, 2);
        rightFooterImageLabel = new Label();
        rightFooterImageLabel.setText(props.getProperty(CourseSiteGeneratorProp.PAGESTYLE_RIGHTFOOTERLABEL_TEXT.toString()));
        rightFooterChange = new Button();
        rightFooterChange.setText(props.getProperty(CourseSiteGeneratorProp.PAGESTYLE_RIGHTBUTTON_TEXT.toString()));
        pageStylePane.add(rightFooterChange, 2, 3);
        pageStylePane.add(rightFooterImageLabel, 0, 3);
        stylesheetLabel = new Label();
        stylesheetLabel.setText(props.getProperty(CourseSiteGeneratorProp.PAGESTYLE_STYLESHEETLABEL_TEXT.toString()));
        stylesheetComboBox = new ComboBox();
        pageStylePane.add(stylesheetLabel, 0, 4);
        pageStylePane.add(stylesheetComboBox, 1, 4);
        noteLabel = new Label();
        noteLabel.setText(props.getProperty(CourseSiteGeneratorProp.PAGESTYLE_NOTE_TEXT.toString()));
        pageStylePane.add(noteLabel, 0 , 5);
        
        
        courseDetailsPane = new VBox();
        courseDetailsPane.getChildren().add(courseInfoPane);
        courseDetailsPane.getChildren().add(siteTemplatePane);
        courseDetailsPane.getChildren().add(pageStylePane);
       // courseTab.setContent(courseDetailsPane);
        ////////////////////recitation//////////////////////
        
        recitationDataPane = new GridPane();
        recitationLabel = new Label();
        recitationLabel.setText(props.getProperty(CourseSiteGeneratorProp.RECITATION_LABEL_TEXT.toString()));
        recitationDataPane.add(recitationLabel, 0, 0);
        recitations = new TableView();
        recitationDataPane.add(recitations, 0, 1);
        section = new TableColumn();
        section.setText(props.getProperty(CourseSiteGeneratorProp.RECITATION_SECTIONCOLUMN_TEXT.toString()));
        instructor = new TableColumn();
        instructor.setText(props.getProperty(CourseSiteGeneratorProp.RECITATION_INSTRUCTORCOLUMN_TEXT.toString()));
        dayAndTime = new TableColumn();
        dayAndTime.setText(props.getProperty(CourseSiteGeneratorProp.RECITATION_DAYANDTIMECOLUMN_TEXT.toString()));
        location = new TableColumn();
        location.setText(props.getProperty(CourseSiteGeneratorProp.RECITATION_LOCATIONCOLUMN_TEXT.toString()));
        firstTA = new TableColumn();
        firstTA.setText(props.getProperty(CourseSiteGeneratorProp.RECITATION_FIRSTTACOLUMN_TEXT.toString()));
        secondTA = new TableColumn();
        secondTA.setText(props.getProperty(CourseSiteGeneratorProp.RECITATION_SECONDTACOLUMN_TEXT.toString()));
        recitations.getColumns().addAll(section, instructor, dayAndTime, location, firstTA, secondTA);
        addEditLabel = new Label();
        addEditLabel.setText(props.getProperty(CourseSiteGeneratorProp.RECITATION_ADDEDITLABEL_TEXT.toString()));
        recitationDataPane.add(addEditLabel, 0, 2);
        sectionLabel = new Label();
        sectionLabel.setText(props.getProperty(CourseSiteGeneratorProp.RECITATION_SECTIONLABEL_TEXT.toString()));
        recitationDataPane.add(sectionLabel, 0 , 3);
        sectionTextField = new TextField();
        recitationDataPane.add(sectionTextField, 1, 3);
        instructorLabel = new Label();
        instructorLabel.setText(props.getProperty(CourseSiteGeneratorProp.RECITATION_INSTRUCTORLABEL_TEXT.toString()));
        recitationDataPane.add(instructorLabel, 0 , 4);
        instructorTextField = new TextField();
        recitationDataPane.add(instructorTextField, 1, 4);
        dayAndTimeLabel = new Label();
        dayAndTimeLabel.setText(props.getProperty(CourseSiteGeneratorProp.RECITATION_DAYANDTIMELABEL_TEXT.toString()));
        dayAndTimeTextField = new TextField();
        recitationDataPane.add(dayAndTimeTextField, 1, 5);
        recitationDataPane.add(dayAndTimeLabel, 0 , 5);
        locationLabel = new Label();
        locationLabel.setText(props.getProperty(CourseSiteGeneratorProp.RECITATION_LOCATIONLABEL_TEXT.toString()));
        locationTextField = new TextField();
        recitationDataPane.add(locationTextField, 1, 6);
        recitationDataPane.add(locationLabel, 0 , 6);
        supervisingTALabel = new Label();
        supervisingTALabel.setText(props.getProperty(CourseSiteGeneratorProp.RECITATION_FIRSTTALABEL_TEXT.toString()));
        secondSupervisingTALabel = new Label();
        secondSupervisingTALabel.setText(props.getProperty(CourseSiteGeneratorProp.RECITATION_SECONDTALABEL_TEXT.toString()));
        recitationDataPane.add(supervisingTALabel, 0 , 7);
        recitationDataPane.add(secondSupervisingTALabel, 0 , 8);
        firstTAComboBox = new ComboBox();
        secondTAComboBox = new ComboBox();
        recitationDataPane.add(firstTAComboBox, 1, 7);
        recitationDataPane.add(secondTAComboBox, 1, 8); 
        
        recitationAddUpdateButton = new Button();
        recitationAddUpdateButton.setText(props.getProperty(CourseSiteGeneratorProp.RECITATION_ADDEDITBUTTON_TEXT.toString()));
        recitationClearButton = new Button();
        recitationClearButton.setText(props.getProperty(CourseSiteGeneratorProp.RECITATION_CLEARBUTTON_TEXT.toString()));
        recitationDataPane.add(recitationAddUpdateButton, 0, 9);
        recitationDataPane.add(recitationClearButton, 1, 9);
    
        
      
       recitationTab.setContent(recitationDataPane);
       courseTab.setContent(courseDetailsPane);
       
       //////////////////////////////////SCHEDULE/////////////////////////////////////
       schedulePagePane = new VBox();
       calendarBoundariesPane = new GridPane();
       scheduleItemsPane = new GridPane();
       
       calendarBoundariesLabel = new Label();
       calendarBoundariesLabel.setText(props.getProperty(CourseSiteGeneratorProp.CALENDAR_HEADER_TEXT.toString()));
       startingMondayLabel = new Label();
       startingMondayLabel.setText(props.getProperty(CourseSiteGeneratorProp.CALENDAR_MONDAYLABEL_TEXT.toString()));
       endingFridayLabel = new Label();
       endingFridayLabel.setText(props.getProperty(CourseSiteGeneratorProp.CALENDAR_FRIDAYLABEL_TEXT.toString()));
       mondayDate = new DatePicker();
       fridayDate = new DatePicker();
       calendarBoundariesPane.add(calendarBoundariesLabel,0,0);
       calendarBoundariesPane.add(startingMondayLabel,0,1);
       calendarBoundariesPane.add(mondayDate,1,1);
       calendarBoundariesPane.add(endingFridayLabel,2, 1);
       calendarBoundariesPane.add(fridayDate, 3, 1);
       
       //Schedule Items section
       scheduleItemsLabel = new Label();
       scheduleItemsLabel.setText(props.getProperty(CourseSiteGeneratorProp.SCHEDULEITEM_HEADER_TEXT.toString()));
       //SET UP THE TABLE
       scheduleItemsTable = new TableView();
       typeColumn = new TableColumn(props.getProperty(CourseSiteGeneratorProp.SCHEDULEITEM_TYPECOLUMN_TEXT.toString()));
       dateColumn = new TableColumn(props.getProperty(CourseSiteGeneratorProp.SCHEDULEITEM_DATECOLUMN_TEXT.toString()));
       titleColumn = new TableColumn(props.getProperty(CourseSiteGeneratorProp.SCHEDULEITEM_TITLECOLUMN_TEXT.toString()));
       topicColumn = new TableColumn(props.getProperty(CourseSiteGeneratorProp.SCHEDULEITEM_TOPICCOLUMN_TEXT.toString()));
       scheduleItemsTable.getColumns().add(typeColumn);
       scheduleItemsTable.getColumns().add(dateColumn);
       scheduleItemsTable.getColumns().add(titleColumn);
       scheduleItemsTable.getColumns().add(topicColumn);
       scheduleItemsPane.add(scheduleItemsLabel, 0, 0);
       scheduleItemsPane.add(scheduleItemsTable, 0, 1);
       
       scheduleAddEditLabel = new Label();
       scheduleAddEditLabel.setText(props.getProperty(CourseSiteGeneratorProp.SCHEDULEITEM_ADDEDITLABEL_TEXT.toString()));
       typeLabel = new Label();
       typeLabel.setText(props.getProperty(CourseSiteGeneratorProp.SCHEDULEITEM_TYPELABEL_TEXT.toString()));
       dateLabel = new Label();
       typeComboBox = new ComboBox();
       scheduleItemsPane.add(scheduleAddEditLabel, 0, 2);
       dateLabel.setText(props.getProperty(CourseSiteGeneratorProp.SCHEDULEITEM_DATELABEL_TEXT.toString()));
       timeLabel = new Label();
       timeLabel.setText(props.getProperty(CourseSiteGeneratorProp.SCHEDULEITEM_TIMELABEL_TEXT.toString()));
       titleLabel = new Label();
       titleLabel.setText(props.getProperty(CourseSiteGeneratorProp.SCHEDULEITEM_TITLELABEL_TEXT.toString()));
       topicLabel = new Label();
       topicLabel.setText(props.getProperty(CourseSiteGeneratorProp.SCHEDULEITEM_TOPICLABEL_TEXT.toString()));
       linkLabel = new Label();
       linkLabel.setText(props.getProperty(CourseSiteGeneratorProp.SCHEDULEITEM_LINKLABEL_TEXT.toString()));
       criteriaLabel = new Label();
       criteriaLabel.setText(props.getProperty(CourseSiteGeneratorProp.SCHEDULEITEM_CRITERIALABEL_TEXT.toString()));
       scheduleDate = new DatePicker();
       scheduleTimeTextField = new TextField();
       scheduleTitleTextField = new TextField();
       scheduleTopicTextField = new TextField();
       scheduleLinkTextField = new TextField();
       scheduleCriteriaTextField = new TextField();
       scheduleAddUpdateButton = new Button();
       scheduleAddUpdateButton.setText(props.getProperty(CourseSiteGeneratorProp.SCHEDULEITEM_ADDUPDATEBUTTON_TEXT.toString()));
       scheduleClearButton = new Button();
       scheduleClearButton.setText(props.getProperty(CourseSiteGeneratorProp.SCHEDULEITEM_CLEARBUTTON_TEXT.toString()));
       
       
       
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
       scheduleItemsPane.add(scheduleAddUpdateButton,0,10);
       scheduleItemsPane.add(scheduleClearButton,1,10);
       schedulePagePane.getChildren().add(calendarBoundariesPane);
       schedulePagePane.getChildren().add(scheduleItemsPane);
       scheduleTab.setContent(schedulePagePane);
       
       //////////////////////////////PROJECTs//////////////////
       
       //Initialize all the panes
       projectsPane = new VBox();
       teamsPane = new GridPane();
       studentsPane = new GridPane();
       
       projectsHeader = new Label();
       projectsHeader.setText(props.getProperty(CourseSiteGeneratorProp.PROJECTS_HEADER_TEXT.toString()));
       projectsPane.getChildren().add(projectsHeader);
       
       teamsHeaderLabel = new Label();
       teamsHeaderLabel.setText(props.getProperty(CourseSiteGeneratorProp.TEAMS_TEAMSLABEL_TEXT.toString()));
       teamsTable = new TableView();
       teamsNameColumn = new TableColumn();
       teamsNameColumn.setText(props.getProperty(CourseSiteGeneratorProp.TEAMS_NAMECOLUMN_TEXT.toString()));
       teamsColorColumn = new TableColumn();
       teamsColorColumn.setText(props.getProperty(CourseSiteGeneratorProp.TEAMS_COLORCOLUMN_TEXT.toString()));
       teamsTextColorColumn = new TableColumn();
       teamsTextColorColumn.setText(props.getProperty(CourseSiteGeneratorProp.TEAMS_TEXTCOLORCOLUMN_TEXT.toString()));
       teamsLinkColumn = new TableColumn();
       teamsLinkColumn.setText(props.getProperty(CourseSiteGeneratorProp.TEAMS_LINKCOLUMN_TEXT.toString()));
       teamsTable.getColumns().addAll(teamsNameColumn,
               teamsColorColumn, 
               teamsTextColorColumn,
               teamsLinkColumn);
       teamsPane.add(teamsHeaderLabel,0,0);
       teamsPane.add(teamsTable,0,1);
       teamsAddEditLabel = new Label();
       teamsAddEditLabel.setText(props.getProperty(CourseSiteGeneratorProp.TEAMS_ADDEDITLABEL_TEXT.toString()));
       teamsNameLabel = new Label();
       teamsNameLabel.setText(props.getProperty(CourseSiteGeneratorProp.TEAMS_NAMELABEL_TEXT.toString()));
       teamsColorLabel = new Label();
       teamsColorLabel.setText(props.getProperty(CourseSiteGeneratorProp.TEAMS_COLORLABEL_TEXT.toString()));
       teamsColorColorPicker = new ColorPicker();
       teamsTextColorPicker = new ColorPicker();
       teamsTextColorLabel = new Label();
       teamsTextColorLabel.setText(props.getProperty(CourseSiteGeneratorProp.TEAMS_TEXTCOLORLABEL_TEXT.toString()));
       teamsLinkLabel = new Label();
       teamsLinkLabel.setText(props.getProperty(CourseSiteGeneratorProp.TEAMS_LINKLABEL_TEXT.toString()));
       teamsLinkTextField = new TextField();
       teamsAddUpdateButton = new Button();
       teamsAddUpdateButton.setText(props.getProperty(CourseSiteGeneratorProp.TEAMS_ADDUPDATEBUTTON_TEXT.toString()));
       teamsClearButton = new Button();
       teamsClearButton.setText(props.getProperty(CourseSiteGeneratorProp.TEAMS_CLEARBUTTON_TEXT.toString()));
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
       studentsHeaderLabel.setText(props.getProperty(CourseSiteGeneratorProp.STUDENTS_HEADER_TEXT.toString()));
       studentsTable = new TableView();
       studentsFirstNameColumn = new TableColumn();
       studentsFirstNameColumn.setText(props.getProperty(CourseSiteGeneratorProp.STUDENTS_FIRSTNAMECOLUMN_TEXT.toString()));
       studentsLastNameColumn = new TableColumn();
       studentsLastNameColumn.setText(props.getProperty(CourseSiteGeneratorProp.STUDENTS_LASTNAMECOLUMN_TEXT.toString()));
       studentsTeamColumn = new TableColumn();
       studentsTeamColumn.setText(props.getProperty(CourseSiteGeneratorProp.STUDENTS_TEAMCOLUMN_TEXT.toString()));
       studentsRoleColumn = new TableColumn();
       studentsRoleColumn.setText(props.getProperty(CourseSiteGeneratorProp.STUDENTS_ROLECOLUMN_TEXT.toString()));
       studentsTable.getColumns().addAll(
            studentsFirstNameColumn,
            studentsLastNameColumn,
            studentsTeamColumn,
            studentsRoleColumn);
       studentsPane.add(studentsHeaderLabel,0,0);
       studentsPane.add(studentsTable,0,1);
       studentsAddEditLabel = new Label();
       studentsAddEditLabel.setText(props.getProperty(CourseSiteGeneratorProp.STUDENTS_ADDEDITLABEL_TEXT.toString()));
       studentsFirstNameLabel = new Label();
       studentsFirstNameLabel.setText(props.getProperty(CourseSiteGeneratorProp.STUDENTS_FIRSTNAMELABEL_TEXT.toString()));
       studentsLastNameLabel = new Label();
       studentsLastNameLabel.setText(props.getProperty(CourseSiteGeneratorProp.STUDENTS_LASTNAMELABEL_TEXT.toString()));
       studentsTeamLabel= new Label();
       studentsTeamLabel.setText(props.getProperty(CourseSiteGeneratorProp.STUDENTS_TEAMLABEL_TEXT.toString()));
       studentsRoleLabel = new Label();
       studentsRoleLabel.setText(props.getProperty(CourseSiteGeneratorProp.STUDENTS_ROLELABEL_TEXT.toString()));
       studentsFirstNameTextField = new TextField();
       studentsLastNameTextField = new TextField();
       studentsTeamComboBox = new ComboBox();
       studentsRoleTextField = new TextField();
       studentsAddUpdateButton = new Button();
       studentsAddUpdateButton.setText(props.getProperty(CourseSiteGeneratorProp.STUDENTS_ADDUPDATEBUTTON_TEXT.toString()));
       studentsClearButton = new Button();
       studentsClearButton.setText(props.getProperty(CourseSiteGeneratorProp.STUDENTS_CLEARBUTTON_TEXT.toString()));
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
       
       ///////////////////////////TA DATA////////////////////////////////////////////
       // INIT THE HEADER ON THE LEFT
       /*
        tasHeaderBox = new HBox();
        String tasHeaderText = props.getProperty(CourseSiteGeneratorProp.TAS_HEADER_TEXT.toString());
        tasHeaderLabel = new Label(tasHeaderText);
        tasHeaderBox.getChildren().add(tasHeaderLabel);

        // MAKE THE TABLE AND SETUP THE DATA MODEL
        taTable = new TableView();
        taTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        TAData data = (TAData) app.getDataComponent();
        ObservableList<TeachingAssistant> tableData = data.getTeachingAssistants();
        taTable.setItems(tableData);
        String nameColumnText = props.getProperty(CourseSiteGeneratorProp.NAME_COLUMN_TEXT.toString());
        String emailColumnText = props.getProperty(CourseSiteGeneratorProp.EMAIL_COLUMN_TEXT.toString());
        nameColumn = new TableColumn(nameColumnText);
        emailColumn = new TableColumn(emailColumnText);
        nameColumn.setCellValueFactory(
                new PropertyValueFactory<TeachingAssistant, String>("name")
        );
        emailColumn.setCellValueFactory(
                new PropertyValueFactory<TeachingAssistant, String>("email")
        );
        taTable.getColumns().add(nameColumn);
        taTable.getColumns().add(emailColumn);

        // ADD BOX FOR ADDING A TA
        String namePromptText = props.getProperty(CourseSiteGeneratorProp.NAME_PROMPT_TEXT.toString());
        String emailPromptText = props.getProperty(CourseSiteGeneratorProp.EMAIL_PROMPT_TEXT.toString());
        String addButtonText = props.getProperty(CourseSiteGeneratorProp.ADD_BUTTON_TEXT.toString());
        String clearButtonText = props.getProperty(CourseSiteGeneratorProp.CLEAR_BUTTON_TEXT.toString());
        nameTextField = new TextField();
        emailTextField = new TextField();
        nameTextField.setPromptText(namePromptText);
        emailTextField.setPromptText(emailPromptText);
        addButton = new Button(addButtonText);
        clearButton = new Button(clearButtonText);
        addBox = new HBox();
        nameTextField.prefWidthProperty().bind(addBox.widthProperty().multiply(.4));
        emailTextField.prefWidthProperty().bind(addBox.widthProperty().multiply(.4));
        addButton.prefWidthProperty().bind(addBox.widthProperty().multiply(.1));
        clearButton.prefWidthProperty().bind(addBox.widthProperty().multiply(.1));
        addBox.getChildren().add(nameTextField);
        addBox.getChildren().add(emailTextField);
        addBox.getChildren().add(addButton);
        addBox.getChildren().add(clearButton);

        // INIT THE HEADER ON THE RIGHT
        officeHoursHeaderBox = new HBox();
        String officeHoursGridText = props.getProperty(CourseSiteGeneratorProp.OFFICE_HOURS_SUBHEADER.toString());
        officeHoursHeaderLabel = new Label(officeHoursGridText);
        officeHoursHeaderBox.getChildren().add(officeHoursHeaderLabel);
        
        // THESE WILL STORE PANES AND LABELS FOR OUR OFFICE HOURS GRID
        officeHoursGridPane = new GridPane();
        officeHoursGridTimeHeaderPanes = new HashMap();
        officeHoursGridTimeHeaderLabels = new HashMap();
        officeHoursGridDayHeaderPanes = new HashMap();
        officeHoursGridDayHeaderLabels = new HashMap();
        officeHoursGridTimeCellPanes = new HashMap();
        officeHoursGridTimeCellLabels = new HashMap();
        officeHoursGridTACellPanes = new HashMap();
        officeHoursGridTACellLabels = new HashMap();

        // ORGANIZE THE LEFT AND RIGHT PANES
        VBox leftPane = new VBox();
        leftPane.getChildren().add(tasHeaderBox);        
        leftPane.getChildren().add(taTable);        
        leftPane.getChildren().add(addBox);
        VBox rightPane = new VBox();
        rightPane.getChildren().add(officeHoursHeaderBox);
        
        time_options = FXCollections.observableArrayList(
        props.getProperty(CourseSiteGeneratorProp.TIME_12AM.toString()),
        props.getProperty(CourseSiteGeneratorProp.TIME_1AM.toString()),
        props.getProperty(CourseSiteGeneratorProp.TIME_2AM.toString()),
        props.getProperty(CourseSiteGeneratorProp.TIME_3AM.toString()),
        props.getProperty(CourseSiteGeneratorProp.TIME_4AM.toString()),
        props.getProperty(CourseSiteGeneratorProp.TIME_5AM.toString()),
        props.getProperty(CourseSiteGeneratorProp.TIME_6AM.toString()),
        props.getProperty(CourseSiteGeneratorProp.TIME_7AM.toString()),
        props.getProperty(CourseSiteGeneratorProp.TIME_8AM.toString()),
        props.getProperty(CourseSiteGeneratorProp.TIME_9AM.toString()),
        props.getProperty(CourseSiteGeneratorProp.TIME_10AM.toString()),
        props.getProperty(CourseSiteGeneratorProp.TIME_11AM.toString()),
        props.getProperty(CourseSiteGeneratorProp.TIME_12PM.toString()),
        props.getProperty(CourseSiteGeneratorProp.TIME_1PM.toString()),
        props.getProperty(CourseSiteGeneratorProp.TIME_2PM.toString()),
        props.getProperty(CourseSiteGeneratorProp.TIME_3PM.toString()),
        props.getProperty(CourseSiteGeneratorProp.TIME_4PM.toString()),
        props.getProperty(CourseSiteGeneratorProp.TIME_5PM.toString()),
        props.getProperty(CourseSiteGeneratorProp.TIME_6PM.toString()),
        props.getProperty(CourseSiteGeneratorProp.TIME_7PM.toString()),
        props.getProperty(CourseSiteGeneratorProp.TIME_8PM.toString()),
        props.getProperty(CourseSiteGeneratorProp.TIME_9PM.toString()),
        props.getProperty(CourseSiteGeneratorProp.TIME_10PM.toString()),
        props.getProperty(CourseSiteGeneratorProp.TIME_11PM.toString())
        );
        comboBox1 = new ComboBox(time_options);
        comboBox2 = new ComboBox(time_options);
        
        officeHoursHeaderBox.getChildren().add(comboBox1);
        comboBox1.setPrefHeight(42);
        comboBox1.setPrefWidth(150);
        comboBox1.getSelectionModel().select(data.getStartHour());
        comboBox1.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                if(t != null && t1 != null)
                    if(comboBox1.getSelectionModel().getSelectedIndex() != data.getStartHour())
                        controller.changeTime();
            }
        });
        officeHoursHeaderBox.getChildren().add(comboBox2);
        comboBox2.setPrefHeight(42);
        comboBox2.setPrefWidth(150);
        comboBox2.getSelectionModel().select(data.getEndHour());
        comboBox2.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {
                if(t != null && t1 != null)
                    if(comboBox2.getSelectionModel().getSelectedIndex() != data.getEndHour())
                        controller.changeTime();
            }    
        });
        rightPane.getChildren().add(officeHoursGridPane);
        
        // BOTH PANES WILL NOW GO IN A SPLIT PANE
        SplitPane sPane = new SplitPane(leftPane, new ScrollPane(rightPane));
        
        taTab.setContent(sPane);
       
       */
       
       
    }

    public GridPane getCourseInfoPane(){
        return courseInfoPane;
    }
    
    public GridPane getSiteTemplatePane(){
        return siteTemplatePane;
    }
    
    public GridPane getPageStylePane(){
        return pageStylePane;
    }
    
    public VBox getCourseDetailsPane(){
        return courseDetailsPane;
    }
    
    public Pane getWorkspacePane(){
        return app.getGUI().getAppPane();
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
