/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;


import djf.components.AppDataComponent;
import csg.CourseSiteGeneratorApp;
import csg.CourseSiteGeneratorProp;
import csg.file.TimeSlot;
import csg.workspace.CourseSiteGeneratorWorkspace;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import properties_manager.PropertiesManager;
/**
 *
 * @author benjaminzhuo
 */
public class CourseSiteGeneratorData implements AppDataComponent
{
    String calendarMonday;
    String calendarFriday;
    String bannerPath;
    String leftFooterPath;
    String rightFooterPath;
    String styleSheet;
    // WE'LL NEED ACCESS TO THE APP TO NOTIFY THE GUI WHEN DATA CHANGES
    CourseSiteGeneratorApp app;

    // NOTE THAT THIS DATA STRUCTURE WILL DIRECTLY STORE THE
    // DATA IN THE ROWS OF THE TABLE VIEW
    ObservableList<TeachingAssistant> teachingAssistants;
    ObservableList<Recitation> recitations;
    ObservableList<Schedule> scheduleItems;
    ObservableList<Student> students;
    ObservableList<Team> teams;
    CourseInfo courseInfo;
    

    // THIS WILL STORE ALL THE OFFICE HOURS GRID DATA, WHICH YOU
    // SHOULD NOTE ARE StringProperty OBJECTS THAT ARE CONNECTED
    // TO UI LABELS, WHICH MEANS IF WE CHANGE VALUES IN THESE
    // PROPERTIES IT CHANGES WHAT APPEARS IN THOSE LABELS
    HashMap<String, StringProperty> officeHours;
    ArrayList<TimeSlot> testOfficeHours;

    // THESE ARE THE LANGUAGE-DEPENDENT VALUES FOR
    // THE OFFICE HOURS GRID HEADERS. NOTE THAT WE
    // LOAD THESE ONCE AND THEN HANG ON TO THEM TO
    // INITIALIZE OUR OFFICE HOURS GRID
    ArrayList<String> gridHeaders;

    // THESE ARE THE TIME BOUNDS FOR THE OFFICE HOURS GRID. NOTE
    // THAT THESE VALUES CAN BE DIFFERENT FOR DIFFERENT FILES, BUT
    // THAT OUR APPLICATION USES THE DEFAULT TIME VALUES AND PROVIDES
    // NO MEANS FOR CHANGING THESE VALUES
    int startHour;
    int endHour;

    // DEFAULT VALUES FOR START AND END HOURS IN MILITARY HOURS
    public static final int MIN_START_HOUR = 9;
    public static final int MAX_END_HOUR = 20;

    /**
     * This constructor will setup the required data structures for use, but
     * will have to wait on the office hours grid, since it receives the
     * StringProperty objects from the Workspace.
     *
     * @param initApp The application this data manager belongs to.
     */
    public CourseSiteGeneratorData(CourseSiteGeneratorApp initApp) {
        // KEEP THIS FOR LATER
        app = initApp;

        // CONSTRUCT THE LIST OF TAs FOR THE TABLE
        courseInfo = new CourseInfo();
        students = FXCollections.observableArrayList();
        teams = FXCollections.observableArrayList();
        recitations = FXCollections.observableArrayList();
        teachingAssistants = FXCollections.observableArrayList();
        scheduleItems = FXCollections.observableArrayList();
        // THESE ARE THE DEFAULT OFFICE HOURS
        startHour = MIN_START_HOUR;
        endHour = MAX_END_HOUR;

        //THIS WILL STORE OUR OFFICE HOURS
        officeHours = new HashMap();
        testOfficeHours = new ArrayList<TimeSlot>();
        

        // THESE ARE THE LANGUAGE-DEPENDENT OFFICE HOURS GRID HEADERS
        PropertiesManager props = PropertiesManager.getPropertiesManager();
        ArrayList<String> timeHeaders = props.getPropertyOptionsList(CourseSiteGeneratorProp.OFFICE_HOURS_TABLE_HEADERS);
        ArrayList<String> dowHeaders = props.getPropertyOptionsList(CourseSiteGeneratorProp.DAYS_OF_WEEK);
        gridHeaders = new ArrayList();
        gridHeaders.addAll(timeHeaders);
        gridHeaders.addAll(dowHeaders);
    }

    /**
     * Called each time new work is created or loaded, it resets all data and
     * data structures such that they can be used for new values.
     */
    @Override
    
    public void resetData() {
        startHour = MIN_START_HOUR;
        endHour = MAX_END_HOUR;
        teachingAssistants.clear();
        officeHours.clear();
    }
    
    public void setMonday(String a){
        calendarMonday = a;
    }
    
    public void setFriday(String a){
        calendarFriday = a;
    }
    
    
    
    
    
    
    
    
    public ObservableList<Schedule> getScheduleItems(){
        return scheduleItems;
    }
    public String getMonday(){
        return calendarMonday;
    }
    public String getFriday(){
        return calendarFriday;
    }
    
    public void addTestTimeSlot(String initDay, String initTime, String initName){
        TimeSlot ts = new TimeSlot(initDay, initTime, initName);
        testOfficeHours.add(ts);
    }
    
    public ArrayList<TimeSlot> getTestOfficeHours(){
        return testOfficeHours;
    }
    
    public void addPageStyle(String a, String b, String c, String d){
        bannerPath = a;
        leftFooterPath = b;
        rightFooterPath = c;
        styleSheet = d;
    }
    
    public String getBannerPath(){
        return bannerPath;
    }
    public String getLeftFooterPath(){
        return leftFooterPath;
    }
    public String getRightFooterPath(){
        return rightFooterPath;
    }
    public String getStylesheet(){
        return styleSheet;
    }
    
    public void setCourseInfo(String a, String b, String c, String d, String e, String f, String g, String h){
        courseInfo = new CourseInfo(a,b,c,d,e,f,g,h);
    }
    
    public CourseInfo getCourseInfo(){
        return courseInfo;
    }
    // ACCESSOR METHODS
    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setStartHour(int startTime) {
        startHour = startTime;
    }

    public void setEndHour(int endTime) {
        endHour = endTime;
    }

    public ArrayList<String> getGridHeaders() {
        return gridHeaders;
    }

    public void setTeachingAssistants(ObservableList newList){
        teachingAssistants = newList;
    }
    public ObservableList getTeachingAssistants() {
        return teachingAssistants;
    }
    
    public ObservableList getRecitations() {
        return recitations;
    }
    
    public ObservableList getStudents() {
        return students;
    }
    
    public ObservableList getTeams() {
        return teams;
    }

    public String getCellKey(int col, int row) {
        return col + "_" + row;
    }

    public StringProperty getCellTextProperty(int col, int row) {
        String cellKey = getCellKey(col, row);
        return officeHours.get(cellKey);
    }

    public HashMap<String, StringProperty> getOfficeHours() {
        return officeHours;
    }

    public int getNumRows() {
        return ((endHour - startHour) * 2) + 1;
    }

    public String getTimeString(int militaryHour, boolean onHour) {
        String minutesText = "00";
        if (!onHour) {
            minutesText = "30";
        }

        // FIRST THE START AND END CELLS
        int hour = militaryHour;
        if (hour > 12) {
            hour -= 12;
        }
        String cellText = "" + hour + ":" + minutesText;
        if (militaryHour < 12) {
            cellText += "am";
        } else {
            cellText += "pm";
        }
        return cellText;
    }

    public String getCellKey(String day, String time) {
        int col = gridHeaders.indexOf(day);
        int row = 1;
        int hour = Integer.parseInt(time.substring(0, time.indexOf("_")));
        int milHour = hour;
        if (hour < startHour) {
            milHour += 12;
        }
        row += (milHour - startHour) * 2;
        if (time.contains("_30")) {
            row += 1;
        }
        return getCellKey(col, row);
    }

    public TeachingAssistant getTA(String testName) {
        for (TeachingAssistant ta : teachingAssistants) {
            if (ta.getName().equals(testName)) {
                return ta;
            }
        }
        return null;
    }

    /**
     * This method is for giving this data manager the string property for a
     * given cell.
     */
    public void setCellProperty(int col, int row, StringProperty prop) {
        String cellKey = getCellKey(col, row);
        officeHours.put(cellKey, prop);
    }

    /**
     * This method is for setting the string property for a given cell.
     */
    public void setGridProperty(ArrayList<ArrayList<StringProperty>> grid,
            int column, int row, StringProperty prop) {
        grid.get(row).set(column, prop);
    }

    /*
    * THis method updates the time for office hours 
     */
    public void updateTime(int newStartHour, int newEndHour) {
        HashMap<String, StringProperty> newOfficeHours;
        newOfficeHours = new HashMap();
        CourseSiteGeneratorWorkspace workspaceComponent = (CourseSiteGeneratorWorkspace) app.getWorkspaceComponent();
        int shiftIndex = (newEndHour - newStartHour) * 2 + 1;
        int oldStartRow = (newStartHour - startHour) * 2 + 1;
        int newStartRow = 1;
        if (newStartHour >= startHour && newEndHour <= endHour) // IF New Time is 10 and old time is 8.  New End is 16 , old end is 20         && newEndHour<=endHour
        {

            for (int col = 2; col < 7; col++) {
                for (int i = 0; i < ((newEndHour - newStartHour) * 2); i++) {

                    int row = oldStartRow;
                    String cellKey = col + "_" + row;
                    StringProperty prop = officeHours.get(cellKey);
                    String newCellKey = col + "_" + newStartRow;
                    newOfficeHours.put(newCellKey, prop);
                    oldStartRow++;
                    newStartRow++;

                }
                oldStartRow = (newStartHour - startHour) * 2 + 1;
                newStartRow = 1;
            }
        } else if (newStartHour >= startHour && newEndHour > endHour) {

            for (int col = 2; col < 7; col++) {
                for (int i = 0; i < ((endHour - newStartHour) * 2); i++) {

                    int row = oldStartRow;
                    String cellKey = col + "_" + row;
                    StringProperty prop = officeHours.get(cellKey);
                    String newCellKey = col + "_" + newStartRow;
                    newOfficeHours.put(newCellKey, prop);
                    oldStartRow++;
                    newStartRow++;

                }
                oldStartRow = (newStartHour - startHour) * 2 + 1;
                newStartRow = 1;
            }
        } else if (startHour > newStartHour && endHour >= newEndHour) {
            oldStartRow = 1;
            newStartRow = (startHour - newStartHour) * 2 + 1;
            for (int col = 2; col < 7; col++) {
                for (int i = 0; i < ((newEndHour - startHour) * 2); i++) {

                    int row = oldStartRow;
                    String cellKey = col + "_" + row;
                    StringProperty prop = officeHours.get(cellKey);
                    String newCellKey = col + "_" + newStartRow;
                    newOfficeHours.put(newCellKey, prop);
                    oldStartRow++;
                    newStartRow++;

                }
                oldStartRow = 1;
                newStartRow = (startHour - newStartHour) * 2 + 1;
            }
        } else if (startHour > newStartHour && newEndHour > endHour) {
            oldStartRow = 1;
            newStartRow = (startHour - newStartHour) * 2 + 1;
            for (int col = 2; col < 7; col++) {
                for (int i = 0; i < ((endHour - startHour) * 2); i++) {

                    int row = oldStartRow;
                    String cellKey = col + "_" + row;
                    StringProperty prop = officeHours.get(cellKey);
                    String newCellKey = col + "_" + newStartRow;
                    newOfficeHours.put(newCellKey, prop);
                    oldStartRow++;
                    newStartRow++;

                }
                oldStartRow = 1;
                newStartRow = (startHour - newStartHour) * 2 + 1;
            }
        }

        workspaceComponent.resetWorkspace();
        initOfficeHoursUpdate(newStartHour, newEndHour);

        for (HashMap.Entry<String, StringProperty> entry : newOfficeHours.entrySet()) {
            String key = entry.getKey();
            StringProperty prop = newOfficeHours.get(key);
            toggleTAOfficeHoursUpdate(key, prop);
        }
    }
    
  
    
    
    
    
    
    
    private void initOfficeHours(int initStartHour, int initEndHour) {
        // NOTE THAT THESE VALUES MUST BE PRE-VERIFIED
        startHour = initStartHour;
        endHour = initEndHour;

        // EMPTY THE CURRENT OFFICE HOURS VALUES
        officeHours.clear();
        // WE'LL BUILD THE USER INTERFACE COMPONENT FOR THE
        // OFFICE HOURS GRID AND FEED THEM TO OUR DATA
        // STRUCTURE AS WE GO
        CourseSiteGeneratorWorkspace workspaceComponent = (CourseSiteGeneratorWorkspace) app.getWorkspaceComponent();
        workspaceComponent.reloadOfficeHoursGrid(this);
    }

    private void initOfficeHoursUpdate(int initStartHour, int initEndHour) {
        // NOTE THAT THESE VALUES MUST BE PRE-VERIFIED
        startHour = initStartHour;
        endHour = initEndHour;

        // EMPTY THE CURRENT OFFICE HOURS VALUES
        officeHours.clear();

        // WE'LL BUILD THE USER INTERFACE COMPONENT FOR THE
        // OFFICE HOURS GRID AND FEED THEM TO OUR DATA
        // STRUCTURE AS WE GO
        CourseSiteGeneratorWorkspace workspaceComponent = (CourseSiteGeneratorWorkspace) app.getWorkspaceComponent();
        workspaceComponent.reloadOfficeHoursGrid(this);
    }

    public void initHours(String startHourText, String endHourText) {
        int initStartHour = Integer.parseInt(startHourText);
        int initEndHour = Integer.parseInt(endHourText);
        if ((initStartHour >= MIN_START_HOUR)
                && (initEndHour <= MAX_END_HOUR)
                && (initStartHour <= initEndHour)) {
            // THESE ARE VALID HOURS SO KEEP THEM
            initOfficeHours(initStartHour, initEndHour);
        }
    }

    public boolean containsTA(String testName, String testEmail) {
        for (TeachingAssistant ta : teachingAssistants) {
            if (ta.getName().equals(testName)) {
                return true;
            }
            if (ta.getEmail().equals(testEmail)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsRecitation(String section) {
        for (Recitation ta : recitations) {
            if (ta.getSection().equals(section)) {
                return true;
            }
        }
        return false;
    }
    
    public void addScheduleItem(String initType, String initDate, String initTime, String initTitle, String initTopic, String initLink, String initCriteria)
    {
        Schedule scheduleItem = new Schedule(initType, initDate, initTime, initTitle, initTopic, initLink, initCriteria);
        scheduleItems.add(scheduleItem);
    }
    public void addTeam(String initName, String initColor, String initTextColor, String initLink)
    {
        Team team = new Team(initName, initColor, initTextColor, initLink);
        teams.add(team);
    }
    public void addStudent(String initFirstName, String initLastName, String initTeam, String initRole)
    {
        Student student = new Student(initFirstName, initLastName, initTeam, initRole);
        students.add(student);
    }
    public void addTA(String initName, String initEmail) {
        // MAKE THE TA
        TeachingAssistant ta = new TeachingAssistant(initName, initEmail);

        // ADD THE TA
        if (!containsTA(initName, initEmail)) {
            teachingAssistants.add(ta);
        }

        // SORT THE TAS
        Collections.sort(teachingAssistants);
    }
    
    public void addRecitation(String initSection, String initInstructor, String initDayTime, String initLocation, String initTA, String initSecondTA) {
        // MAKE THE RECITATION
        Recitation recitation = new Recitation(initSection, initInstructor, initDayTime, initLocation,initTA, initSecondTA);

        // ADD THE TA
        if (!containsRecitation(initSection)) {
            recitations.add(recitation);
        }

        // SORT THE TAS
        //Collections.sort(recitations);
    }

    public void removeTA(String name) {
        for (TeachingAssistant ta : teachingAssistants) {
            if (name.equals(ta.getName())) {
                teachingAssistants.remove(ta);
                return;
            }
        }
    }

    public void addOfficeHoursReservation(String day, String time, String taName) {
        String cellKey = getCellKey(day, time);
        toggleTAOfficeHours(cellKey, taName);
    }

    /**
     * This function toggles the taName in the cell represented by cellKey.
     * Toggle means if it's there it removes it, if it's not there it adds it.
     */
    public void toggleTAOfficeHours(String cellKey, String taName) {
        StringProperty cellProp = officeHours.get(cellKey);
        String cellText = cellProp.getValue();

        // IF IT ALREADY HAS THE TA, REMOVE IT
        if (cellText.contains(taName)) {
            removeTAFromCell(cellProp, taName);
        } // OTHERWISE ADD IT
        else if (cellText.length() == 0) {
            cellProp.setValue(taName);
        } else {
            cellProp.setValue(cellText + "\n" + taName);
        }
    }

    public void toggleTAOfficeHoursUpdate(String cellKey, StringProperty prop) {
        String cellText2 = prop.getValue();
        StringProperty cellProp = officeHours.get(cellKey);
        //String cellText = cellProp.getValue();
        cellProp.set(cellText2);

    }

    /**
     * This method removes taName from the office grid cell represented by
     * cellProp.
     */
    public void removeTAFromCell(StringProperty cellProp, String taName) {
        // GET THE CELL TEXT
        String cellText = cellProp.getValue();
        // IS IT THE ONLY TA IN THE CELL?
        if (cellText.equals(taName)) {
            cellProp.setValue("");
        } // IS IT THE FIRST TA IN A CELL WITH MULTIPLE TA'S?
        else if (cellText.indexOf(taName) == 0) {
            int startIndex = cellText.indexOf("\n") + 1;
            cellText = cellText.substring(startIndex);
            cellProp.setValue(cellText);
        } // IS IT IN THE MIDDLE OF A LIST OF TAs
        else if (cellText.indexOf(taName) < cellText.indexOf("\n", cellText.indexOf(taName))) {
            int startIndex = cellText.indexOf("\n" + taName);
            int endIndex = startIndex + taName.length() + 1;
            cellText = cellText.substring(0, startIndex) + cellText.substring(endIndex);
            cellProp.setValue(cellText);
        } // IT MUST BE THE LAST TA
        else {
            int startIndex = cellText.indexOf("\n" + taName);
            cellText = cellText.substring(0, startIndex);
            cellProp.setValue(cellText);
        }
    }

    public void renameTaCell(StringProperty cellProp, String taName, String newName) {
        // GET THE CELL TEXT
        String cellText = cellProp.getValue();
        // IS IT THE ONLY TA IN THE CELL?
        if (cellText.equals(taName)) {
            cellProp.setValue(newName);
        } // IS IT THE FIRST TA IN A CELL WITH MULTIPLE TA'S?
        else if (cellText.indexOf(taName) == 0) {
            int startIndex = cellText.indexOf("\n") + 1;
            cellText = cellText.substring(startIndex);
            cellProp.setValue(cellText + "\n" + newName);
        } // IS IT IN THE MIDDLE OF A LIST OF TAs
        else if (cellText.indexOf(taName) < cellText.indexOf("\n", cellText.indexOf(taName))) {
            int startIndex = cellText.indexOf("\n" + taName);
            int endIndex = startIndex + taName.length() + 1;
            cellText = cellText.substring(0, startIndex) + cellText.substring(endIndex);
            cellProp.setValue(cellText + "\n" + newName);
        } // IT MUST BE THE LAST TA
        else {
            int startIndex = cellText.indexOf("\n" + taName);
            cellText = cellText.substring(0, startIndex);
            cellProp.setValue(cellText + "\n" + newName);
        }
    }

    public void updateTaCell(StringProperty cellProp, String taName, String newName) {
        // GET THE CELL TEXT
        String cellText = cellProp.getValue();
        // IS IT THE ONLY TA IN THE CELL?
        if (cellText.equals(taName)) {
            cellProp.setValue("");
        } // IS IT THE FIRST TA IN A CELL WITH MULTIPLE TA'S?
        else if (cellText.indexOf(taName) == 0) {
            int startIndex = cellText.indexOf("\n") + 1;
            cellText = cellText.substring(startIndex);
            cellProp.setValue(newName + cellText);
        } // IS IT IN THE MIDDLE OF A LIST OF TAs
        else if (cellText.indexOf(taName) < cellText.indexOf("\n", cellText.indexOf(taName))) {
            int startIndex = cellText.indexOf("\n" + taName);
            int endIndex = startIndex + taName.length() + 1;
            cellText = cellText.substring(0, startIndex) + cellText.substring(endIndex);
            cellProp.setValue(cellText);
        } // IT MUST BE THE LAST TA
        else {
            int startIndex = cellText.indexOf("\n" + taName);
            cellText = cellText.substring(0, startIndex);
            cellProp.setValue(cellText);
        }
    }
    
}
