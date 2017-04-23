/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.test_bed;

import csg.CourseSiteGeneratorApp;
import csg.data.CourseSiteGeneratorData;
import csg.data.Recitation;
import csg.data.Schedule;
import csg.data.Student;
import csg.data.TeachingAssistant;
import csg.data.Team;
import csg.file.CourseSiteGeneratorFiles;
import csg.file.TimeSlot;
import csg.style.CourseSiteGeneratorStyle;
import djf.components.AppDataComponent;
import static djf.settings.AppStartupConstants.APP_PROPERTIES_FILE_NAME;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;

/**
 *
 * @author benjaminzhuo
 */
public class TestSave {
    
    
    static final String JSON_START_HOUR = "startHour";
    static final String JSON_END_HOUR = "endHour";
    static final String JSON_OFFICE_HOURS = "officeHours";
    static final String JSON_DAY = "day";
    static final String JSON_TIME = "time";
    static final String JSON_NAME = "name";
    static final String JSON_UNDERGRAD_TAS = "undergrad_tas";
    static final String JSON_EMAIL = "email";
    static final String JSON_RECITATION_ITEMS = "recitation_items";
    static final String JSON_RECITATION_SECTION = "recitation_section";
    static final String JSON_RECITATION_INSTRUCTOR = "recitation_instructor";
    static final String JSON_RECITATION_DAYTIME = "recitation_daytime";
    static final String JSON_RECITATION_LOCATION = "recitation_location";
    static final String JSON_RECITATION_TA = "recitation_ta";
    static final String JSON_RECITATION_SECOND_TA = "recitation_secondta";
    static final String JSON_TEAM_NAME = "team_name";
    static final String JSON_TEAM_COLOR = "team_color";
    static final String JSON_TEAM_TEXTCOLOR = "team_textcolor";
    static final String JSON_TEAM_LINK = "team_link";
    static final String JSON_TEAMS = "teams";
    static final String JSON_STUDENT_FIRSTNAME = "student_firstname";
    static final String JSON_STUDENT_LASTNAME = "student_lastname";
    static final String JSON_STUDENT_TEAM = "student_team";
    static final String JSON_STUDENT_ROLE = "student_role";
    static final String JSON_STUDENTS = "students";
    static final String JSON_COURSE_INFO = "course_info";
    static final String JSON_COURSE_SUBJECT = "course_subject";
    static final String JSON_COURSE_NUMBER = "course_number";
    static final String JSON_COURSE_SEMESTER = "course_semester";
    static final String JSON_COURSE_TITLE = "course_title";
    static final String JSON_COURSE_INSTRUCTORNAME = "course_instructorname";
    static final String JSON_COURSE_INSTRUCTORHOME = "course_instructorhome";
    static final String JSON_COURSE_YEAR = "course_year";
    static final String JSON_COURSE_EXPORT = "course_export";
    static final String JSON_PAGESTYLE_BANNER = "pagestyle_banner";
    static final String JSON_PAGESTYLE_LEFTFOOTER = "pagestyle_leftfooter";
    static final String JSON_PAGESTLYE_RIGHTFOOTER = "pagestyle_rightfooter";
    static final String JSON_PAGESTLYE_STYLESHEET = "pagestyle_stylesheet";
    static final String JSON_PAGESTYLE = "pagestyle";
    static final String JSON_SCHEDULE_TYPE = "schedule_type";
    static final String JSON_SCHEDULE_DATE = "schedule_date";
    static final String JSON_SCHEDULE_TITLE = "schedule_title";
    static final String JSON_SCHEDULE_TOPIC = "schedule_topic";
    static final String JSON_SCHEDULE = "schedules";
    public static void main(String[] args){
      
        
        CourseSiteGeneratorApp app = new CourseSiteGeneratorApp();
        app.loadProperties(APP_PROPERTIES_FILE_NAME);
        CourseSiteGeneratorData data = new CourseSiteGeneratorData(app);
        //CourseSiteGeneratorFiles files = new CourseSiteGeneratorFiles(app);
        
        data.addTestTimeSlot("MONDAY","3:00PM","Dan");
        data.addTA("Dan","dan@gmail.com");
        data.setCourseInfo("CSE","219,","Fall","2017","CS","McKenna","www.cs.stonybrook.edu","/courses219");
        //All objects acquired from Observable lists that are linked with tables
        data.addPageStyle("/file/yale.png", "/file/yale.png", "/file/yale.png", "sea_wolf.css");
        data.addRecitation("RO2", "McKenna","Wed 3:30pm-4:23pm","Old CS 2114","Jane Doe", "Joe Schmo");
        data.addScheduleItem("Holiday","2/9/207","3:00pm","SNOWDAY","Event Programming","asdfa.com","criteria");
        data.addTeam("Atomic Comic","552211","ffffff","atomic.com");
        data.addStudent("Jane","Doe","Atomic Comic","Data Designer");
     
        
        try{
        saveData(data, "/Users/benjaminzhuo/NetBeansProjects/jsonsavefiles/SiteSaveTest.json");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public static void saveData(AppDataComponent data, String filePath) throws IOException {
	// GET THE DATA
	CourseSiteGeneratorData dataManager = (CourseSiteGeneratorData)data;

        
      	    
	JsonObject courseStyleJson = Json.createObjectBuilder()
		    .add(JSON_PAGESTYLE_BANNER, dataManager.getBannerPath())
		    .add(JSON_PAGESTYLE_LEFTFOOTER, dataManager.getLeftFooterPath())
                    .add(JSON_PAGESTLYE_RIGHTFOOTER, dataManager.getRightFooterPath())
                    .add(JSON_PAGESTLYE_STYLESHEET, dataManager.getStylesheet())
                   
                .build();
	
        JsonObject courseInfoJson = Json.createObjectBuilder()
		    .add(JSON_COURSE_SUBJECT, dataManager.getCourseInfo().getSubject())
		    .add(JSON_COURSE_NUMBER, dataManager.getCourseInfo().getNumber())
                    .add(JSON_COURSE_SEMESTER, dataManager.getCourseInfo().getSemester())
                    .add(JSON_COURSE_YEAR, dataManager.getCourseInfo().getYear())
                    .add(JSON_COURSE_TITLE, dataManager.getCourseInfo().getTitle())
                    .add(JSON_COURSE_INSTRUCTORNAME, dataManager.getCourseInfo().getInstructorName())
                    .add(JSON_COURSE_INSTRUCTORHOME, dataManager.getCourseInfo().getInstructorHome())
                    .add(JSON_COURSE_EXPORT, dataManager.getCourseInfo().getDirectory())
                .build();

        
        // NOW BUILD THE TA JSON OBJCTS TO SAVE
	JsonArrayBuilder taArrayBuilder = Json.createArrayBuilder();
	ObservableList<TeachingAssistant> tas = dataManager.getTeachingAssistants();
	for (TeachingAssistant ta : tas) {	    
	    JsonObject taJson = Json.createObjectBuilder()
		    .add(JSON_NAME, ta.getName())
		    .add(JSON_EMAIL, ta.getEmail()).build();
	    taArrayBuilder.add(taJson);
	}
	JsonArray undergradTAsArray = taArrayBuilder.build();

        
        
	// NOW BUILD THE RECITATION JSON OBJCTS TO SAVE
	JsonArrayBuilder recitationsArrayBuilder = Json.createArrayBuilder();
	ObservableList<Recitation> recitations = dataManager.getRecitations();
	for (Recitation recitation : recitations) {	    
            
	    JsonObject recitationJson = Json.createObjectBuilder()
		    .add(JSON_RECITATION_SECTION, recitation.getSection())
		    .add(JSON_RECITATION_INSTRUCTOR, recitation.getInstructor())
                    .add(JSON_RECITATION_DAYTIME, recitation.getDayTime())
                    .add(JSON_RECITATION_LOCATION, recitation.getLocation())
                    .add(JSON_RECITATION_TA, recitation.getFirstTA())
                    .add(JSON_RECITATION_SECOND_TA, recitation.getSecondTA()).build();
	    recitationsArrayBuilder.add(recitationJson);
	}
	JsonArray recitationItemsArray = recitationsArrayBuilder.build();

        
        JsonArrayBuilder scheduleArrayBuilder = Json.createArrayBuilder();
	ObservableList<Schedule> schedules = dataManager.getScheduleItems();
	for (Schedule schedule : schedules) {	    
            
	    JsonObject scheduleJson = Json.createObjectBuilder()
		    .add(JSON_SCHEDULE_TYPE, schedule.getType())
		    .add(JSON_SCHEDULE_DATE, schedule.getDate())
                    .add(JSON_SCHEDULE_TITLE, schedule.getTitle())
                    .add(JSON_SCHEDULE_TOPIC, schedule.getTopic()).build();
	    scheduleArrayBuilder.add(scheduleJson);
	}
	JsonArray scheduleItemsArray = scheduleArrayBuilder.build();
        
        // NOW BUILD THE TEAMS JSON OBJCTS TO SAVE
	JsonArrayBuilder teamsArrayBuilder = Json.createArrayBuilder();
	ObservableList<Team> teams = dataManager.getTeams();
	for (Team team : teams) {	    
            
	    JsonObject teamsJson = Json.createObjectBuilder()
		    .add(JSON_TEAM_NAME, team.getName())
		    .add(JSON_TEAM_COLOR, team.getColor())
                    .add(JSON_TEAM_TEXTCOLOR, team.getTextColor())
                    .add(JSON_TEAM_LINK, team.getLink()).build();
	    teamsArrayBuilder.add(teamsJson);
	}
	JsonArray teamsArray = teamsArrayBuilder.build();
        
        JsonArrayBuilder studentsArrayBuilder = Json.createArrayBuilder();
	ObservableList<Student> students = dataManager.getStudents();
	for (Student student : students) {	    
            
	    JsonObject studentsJson = Json.createObjectBuilder()
		    .add(JSON_STUDENT_FIRSTNAME, student.getFirstName())
		    .add(JSON_STUDENT_LASTNAME, student.getLastName())
                    .add(JSON_STUDENT_TEAM, student.getTeam())
                    .add(JSON_STUDENT_ROLE, student.getRole()).build();
	    studentsArrayBuilder.add(studentsJson);
	}
	JsonArray studentsArray = studentsArrayBuilder.build();
        
	// NOW BUILD THE TIME SLOT JSON OBJCTS TO SAVE
        
	JsonArrayBuilder timeSlotArrayBuilder = Json.createArrayBuilder();
	ArrayList<TimeSlot> officeHours = dataManager.getTestOfficeHours();
	for (TimeSlot ts : officeHours) {	    
	    JsonObject tsJson = Json.createObjectBuilder()
		    .add(JSON_DAY, ts.getDay())
		    .add(JSON_TIME, ts.getTime())
		    .add(JSON_NAME, ts.getName()).build();
	    timeSlotArrayBuilder.add(tsJson);
	}
	JsonArray timeSlotsArray = timeSlotArrayBuilder.build();
        
	// THEN PUT IT ALL TOGETHER IN A JsonObject
	JsonObject dataManagerJSO = Json.createObjectBuilder()
                .add(JSON_COURSE_INFO, "" + courseInfoJson)
                .add(JSON_PAGESTYLE,""+courseStyleJson)
		.add(JSON_START_HOUR, "" + dataManager.getStartHour())
		.add(JSON_END_HOUR, "" + dataManager.getEndHour())
                .add(JSON_UNDERGRAD_TAS, undergradTAsArray)
                .add(JSON_OFFICE_HOURS, timeSlotsArray)
                .add(JSON_RECITATION_ITEMS, recitationItemsArray)
                .add(JSON_SCHEDULE, scheduleItemsArray)
                .add(JSON_TEAMS, teamsArray)
                .add(JSON_STUDENTS, studentsArray)
		.build();
	
	// AND NOW OUTPUT IT TO A JSON FILE WITH PRETTY PRINTING
	Map<String, Object> properties = new HashMap<>(1);
	properties.put(JsonGenerator.PRETTY_PRINTING, true);
	JsonWriterFactory writerFactory = Json.createWriterFactory(properties);
	StringWriter sw = new StringWriter();
	JsonWriter jsonWriter = writerFactory.createWriter(sw);
	jsonWriter.writeObject(dataManagerJSO);
	jsonWriter.close();

	// INIT THE WRITER
	OutputStream os = new FileOutputStream(filePath);
	JsonWriter jsonFileWriter = Json.createWriter(os);
	jsonFileWriter.writeObject(dataManagerJSO);
	String prettyPrinted = sw.toString();
	PrintWriter pw = new PrintWriter(filePath);
	pw.write(prettyPrinted);
	pw.close();
    }
    
    
    
    
}
