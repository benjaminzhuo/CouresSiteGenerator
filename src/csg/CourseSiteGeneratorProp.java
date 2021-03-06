package csg;

/**
 * This enum provides a list of all the user interface
 * text that needs to be loaded from the XML properties
 * file. By simply changing the XML file we could initialize
 * this application such that all UI controls are provided
 * in another language.
 * 
 * @author Richard McKenna
 * @version 1.0
 */
public enum CourseSiteGeneratorProp {
    // FOR SIMPLE OK/CANCEL DIALOG BOXES
    OK_PROMPT,
    CANCEL_PROMPT,
    
    // THESE ARE FOR TEXT PARTICULAR TO THE APP'S WORKSPACE CONTROLS
    TAS_HEADER_TEXT,
    NAME_COLUMN_TEXT,
    EMAIL_COLUMN_TEXT,
    NAME_PROMPT_TEXT,
    EMAIL_PROMPT_TEXT,
    ADD_BUTTON_TEXT,
    OFFICE_HOURS_SUBHEADER,
    OFFICE_HOURS_TABLE_HEADERS,
    DAYS_OF_WEEK,
    UPDATING_HOURS_TITLE,
    UPDATING_HOURS_MESSAGE,
    
    // THESE ARE FOR ERROR MESSAGES PARTICULAR TO THE APP
    MISSING_RECITATION_COURSE_TITLE,
    MISSING_RECITATION_COURSE_MESSAGE,
    MISSING_RECITATION_INSTRUCTOR_TITLE,
    MISSING_RECITATION_INSTRUCTOR_MESSAGE,
    MISSING_RECITATION_DAYTIME_TITLE,
    MISSING_RECITATION_DAYTIME_MESSAGE,
    MISSING_RECITATION_LOCATION_TITLE,
    MISSING_RECITATION_LOCATION_MESSAGE,
    MISSING_TA_NAME_TITLE,
    MISSING_TA_NAME_MESSAGE,
    MISSING_TA_EMAIL_TITLE,
    MISSING_TA_EMAIL_MESSAGE,
    TA_NAME_AND_EMAIL_NOT_UNIQUE_TITLE,
    TA_NAME_AND_EMAIL_NOT_UNIQUE_MESSAGE,
    EMAIL_ADDRESS_ERROR,
    EMAIL_ADDRESS_MESSAGE,
    START_HOUR_ERROR,
    START_HOUR_MESSAGE,
    END_HOUR_ERROR,
    END_HOUR_MESSAGE,
    //TAB PANE STUFF
    COURSE_TAB_HEADER_TEXT,
    RECITATION_TAB_HEADER_TEXT,
    PROJECT_TAB_HEADER_TEXT,
    SCHEDULE_TAB_HEADER_TEXT,
    TA_TAB_HEADER_TEXT,
    //COURSE DETAILS STUFF
    COURSEINFO_HEADER_TEXT,
    COURSEINFO_SUBJECT_TEXT,
    COURSEINFO_NUMBER_TEXT,
    COURSEINFO_SEMESTER_TEXT,
    COURSEINFO_YEAR_TEXT,
    COURSEINFO_TITLE_TEXT,
    COURSEINFO_INSTRUCTORNAME_TEXT,
    COURSEINFO_INSTRUCTORHOME_TEXT,
    COURSEINFO_EXPORTDIRECTORY_TEXT,
    COURSEINFO_CHANGEBUTTON_TEXT,
    
    SITETEMPLATE_HEADER_TEXT,
    SITETEMPLATE_INFOLABEL_TEXT,
    SITETEMPLATE_DIRECTORY_TEXT,
    SITETEMPLATE_DIRECTORYBUTTON_TEXT,
    SITETEMPLATE_PAGES_TEXT,
    SITETEMPLATE_USECOLUMN_TEXT,
    SITETEMPLATE_NAVBARCOLUMN_TEXT,
    SITETEMPLATE_FILENAMECOLUMN_TEXT,
    SITETEMPLATE_SCRIPTCOLUMN_TEXT,
    
    PAGESTYLE_HEADERLABEL_TEXT,
    PAGESTYLE_BANNERLABEL_TEXT,               
    PAGESTYLE_LEFTFOOTERLABEL_TEXT,                
    PAGESTYLE_RIGHTFOOTERLABEL_TEXT,              
    PAGESTYLE_BANNERBUTTON_TEXT,               
    PAGESTYLE_LEFTBUTTON_TEXT,                
    PAGESTYLE_RIGHTBUTTON_TEXT,               
    PAGESTYLE_STYLESHEETLABEL_TEXT,             
    PAGESTYLE_NOTE_TEXT,
    
    RECITATION_LABEL_TEXT,
    RECITATION_SECTIONCOLUMN_TEXT,
    RECITATION_INSTRUCTORCOLUMN_TEXT,
    RECITATION_DAYANDTIMECOLUMN_TEXT,
    RECITATION_LOCATIONCOLUMN_TEXT,
    RECITATION_FIRSTTACOLUMN_TEXT,
    RECITATION_SECONDTACOLUMN_TEXT,
    RECITATION_ADDEDITLABEL_TEXT,
    RECITATION_SECTIONLABEL_TEXT,
    RECITATION_INSTRUCTORLABEL_TEXT,
    RECITATION_DAYANDTIMELABEL_TEXT,
    RECITATION_LOCATIONLABEL_TEXT,
    RECITATION_FIRSTTALABEL_TEXT,
    RECITATION_SECONDTALABEL_TEXT,
    RECITATION_ADDEDITBUTTON_TEXT,
    RECITATION_CLEARBUTTON_TEXT,
    
    CALENDAR_HEADER_TEXT,
    CALENDAR_MONDAYLABEL_TEXT,
    CALENDAR_FRIDAYLABEL_TEXT,
    
    SCHEDULEITEM_HEADER_TEXT,
    SCHEDULEITEM_TYPECOLUMN_TEXT,
    SCHEDULEITEM_DATECOLUMN_TEXT,
    SCHEDULEITEM_TITLECOLUMN_TEXT,
    SCHEDULEITEM_TOPICCOLUMN_TEXT,
    SCHEDULEITEM_ADDEDITLABEL_TEXT,
    SCHEDULEITEM_TYPELABEL_TEXT,
    SCHEDULEITEM_DATELABEL_TEXT,
    SCHEDULEITEM_TIMELABEL_TEXT,
    SCHEDULEITEM_TITLELABEL_TEXT,
    SCHEDULEITEM_TOPICLABEL_TEXT,
    SCHEDULEITEM_LINKLABEL_TEXT,
    SCHEDULEITEM_CRITERIALABEL_TEXT,
    SCHEDULEITEM_ADDUPDATEBUTTON_TEXT,
    SCHEDULEITEM_CLEARBUTTON_TEXT,
    
      
    PROJECTS_HEADER_TEXT,               
    TEAMS_TEAMSLABEL_TEXT,
    TEAMS_NAMECOLUMN_TEXT,
    TEAMS_COLORCOLUMN_TEXT,
    TEAMS_TEXTCOLORCOLUMN_TEXT,
    TEAMS_LINKCOLUMN_TEXT,
    TEAMS_ADDEDITLABEL_TEXT,
    TEAMS_NAMELABEL_TEXT,
    TEAMS_COLORLABEL_TEXT,
    TEAMS_TEXTCOLORLABEL_TEXT,
    TEAMS_LINKLABEL_TEXT,
    TEAMS_ADDUPDATEBUTTON_TEXT,
    TEAMS_CLEARBUTTON_TEXT,
    STUDENTS_HEADER_TEXT,
    STUDENTS_FIRSTNAMECOLUMN_TEXT,
    STUDENTS_LASTNAMECOLUMN_TEXT,
    STUDENTS_TEAMCOLUMN_TEXT,
    STUDENTS_ROLECOLUMN_TEXT,
    STUDENTS_ADDEDITLABEL_TEXT,
    STUDENTS_FIRSTNAMELABEL_TEXT,
    STUDENTS_LASTNAMELABEL_TEXT,
    STUDENTS_TEAMLABEL_TEXT,
    STUDENTS_ROLELABEL_TEXT,
    STUDENTS_ADDUPDATEBUTTON_TEXT,
    STUDENTS_CLEARBUTTON_TEXT,
    TIME_12AM,
    TIME_1AM,
    TIME_2AM,
    TIME_3AM,
    TIME_4AM,
    TIME_5AM,
    TIME_6AM,
    TIME_7AM,
    TIME_8AM,
    TIME_9AM,
    TIME_10AM,
    TIME_11AM,
    TIME_12PM,
    TIME_1PM,
    TIME_2PM,
    TIME_3PM,
    TIME_4PM,
    TIME_5PM,
    TIME_6PM,
    TIME_7PM,
    TIME_8PM,
    TIME_9PM,
    TIME_10PM,
    TIME_11PM, 
    START_HOUR_PROMPT_TEXT, 
    END_HOUR_PROMPT_TEXT,
    UPDATE_TA_BUTTON_TEXT, 
    CLEAR_BUTTON_TEXT, 
    CHANGE_TIME_BUTTON_TEXT,
        

    NO_UPDATE_TITLE,
    NO_UPDATE_MESSAGE,
    INVALID_TIME_INPUT_TITLE,
    INVALID_TIME_INPUT_MESSAGE,
    INVALID_TA_EMAIL_TITLE,
    INVALID_TA_EMAIL_MESSAGE,
    UPDATE_TIME_TITLE,
    UPDATE_TIME_MESSAGE, 
    GRID_BUTTON_TEXT, 
    RECITATION_EDITBUTTON_TEXT,
    RECITATION_NOT_UNIQUE_TITLE,
    RECITATION_NOT_UNIQUE_MESSAGE,
    MISSING_RECITATION_FIRSTTA_TITLE,
    MISSING_RECITATION_FIRSTTA_MESSAGE,
    MISSING_RECITATION_SECONDTA_TITLE,
    MISSING_RECITATION_SECONDTA_MESSAGE,
    RECITATION_ADDBUTTON_TEXT, 
    SECTION_PROMPT_TEXT, 
    LOCATION_PROMPT_TEXT,
    FIRSTTA_PROMPT_TEXT,
    SECONDTA_PROMPT_TEXT, 
    INSTRUCTOR_PROMPT_TEXT,
    DAYANDTIME_PROMPT_TEXT,
    MISSING_RECITATION_SECTION_TITLE,
    MISSING_RECITATION_SECTION_MESSAGE
    
    


    
    

    
}
