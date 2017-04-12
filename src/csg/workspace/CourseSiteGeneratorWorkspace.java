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
    Button addUpdate;
    Button clear;
    ////////////////////////////////Schedule///////////////////////////////////
    
           
    
    
    
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
        ;
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
        
        addUpdate = new Button();
        addUpdate.setText("Add/Update");
        clear = new Button();
        clear.setText("Clear");
        recitationDataPane.add(addUpdate, 0, 9);
        recitationDataPane.add(clear, 1, 9);
    
        
      
       recitationTab.setContent(recitationDataPane);
       courseTab.setContent(courseDetailsPane);
       
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
