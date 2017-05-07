package csg.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import djf.components.AppDataComponent;
import djf.components.AppFileComponent;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.ObservableList;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonWriter;
import javax.json.JsonWriterFactory;
import javax.json.stream.JsonGenerator;
import csg.CourseSiteGeneratorApp;
import csg.data.CourseSiteGeneratorData;
import csg.data.Recitation;
import csg.data.Schedule;
import csg.data.Student;

import csg.file.TimeSlot;
import csg.data.TeachingAssistant;
import csg.data.Team;
import java.io.File;
import org.apache.commons.io.FileUtils;



/**
 * This class serves as the file component for the TA
 * manager app. It provides all saving and loading 
 * services for the application.
 * 
 * @author Richard McKenna
 */
public class CourseSiteGeneratorFiles implements AppFileComponent {
    // THIS IS THE APP ITSELF
    CourseSiteGeneratorApp app;
    
    // THESE ARE USED FOR IDENTIFYING JSON TYPES
    static final String JSON_START_HOUR = "startHour";
    static final String JSON_END_HOUR = "endHour";
    static final String JSON_OFFICE_HOURS = "officeHours";
    static final String JSON_DAY = "day";
    static final String JSON_TIME = "time";
    static final String JSON_NAME = "name";
    static final String JSON_UNDERGRAD_TAS = "undergrad_tas";
    static final String JSON_EMAIL = "email";
    static final String JSON_RECITATION_ITEMS = "recitations";
    static final String JSON_RECITATION_SECTION = "section";
    static final String JSON_RECITATION_INSTRUCTOR = "recitation_instructor";
    static final String JSON_RECITATION_DAYTIME = "day_time";
    static final String JSON_RECITATION_LOCATION = "location";
    static final String JSON_RECITATION_TA = "ta_1";
    static final String JSON_RECITATION_SECOND_TA = "ta_2";
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
    static final String JSON_PAGESTYLE_RIGHTFOOTER = "pagestyle_rightfooter";
    static final String JSON_PAGESTYLE_STYLESHEET = "pagestyle_stylesheet";
    static final String JSON_PAGESTYLE = "pagestyle";
    static final String JSON_SCHEDULE_TYPE = "schedule_type";
    static final String JSON_SCHEDULE_DATE = "schedule_date";
    static final String JSON_SCHEDULE_TITLE = "schedule_title";
    static final String JSON_SCHEDULE_TIME = "schedule_time";
    static final String JSON_SCHEDULE_LINK = "schedule_link";
    static final String JSON_SCHEDULE_CRITERIA = "schedule_criteria";
    static final String JSON_SCHEDULE_TOPIC = "schedule_topic";
    static final String JSON_SCHEDULE = "schedules";
    static final String JSON_MONDAY = "starting_monday";
    static final String JSON_FRIDAY = "starting_friday";
    public CourseSiteGeneratorFiles(CourseSiteGeneratorApp initApp) {
        app = initApp;
    }

    @Override
    public void loadData(AppDataComponent data, String filePath) throws IOException {
	// CLEAR THE OLD DATA OUT
	CourseSiteGeneratorData dataManager = (CourseSiteGeneratorData)data;

	// LOAD THE JSON FILE WITH ALL THE DATA
	JsonObject json = loadJSONFile(filePath);

	// LOAD THE START AND END HOURS
	String startHour = json.getString(JSON_START_HOUR);
        String endHour = json.getString(JSON_END_HOUR);
        dataManager.initHours(startHour, endHour);
        dataManager.setStartHour(Integer.parseInt(startHour));
        dataManager.setEndHour(Integer.parseInt(endHour));
        dataManager.showHours();
        
        //LOAD STARTING MONDAY AND ENDING FRIDAY
        String monday = json.getString(JSON_MONDAY);
        String friday = json.getString(JSON_FRIDAY);
        dataManager.setMonday(monday);
        dataManager.setFriday(friday);
        dataManager.showDates();
        
        // LOAD THE COURSE INFO
        
        String subject = json.getString(JSON_COURSE_SUBJECT);
        String number = json.getString(JSON_COURSE_NUMBER);
        String semester = json.getString(JSON_COURSE_SEMESTER);
        String year = json.getString(JSON_COURSE_YEAR);
        String title = json.getString(JSON_COURSE_TITLE);
        String instname = json.getString(JSON_COURSE_INSTRUCTORNAME);
        String insthome = json.getString(JSON_COURSE_INSTRUCTORHOME);
        String exportdir = json.getString(JSON_COURSE_EXPORT);
        
        dataManager.setCourseInfo(subject, number, semester, year, title, instname, insthome, exportdir);
        dataManager.showCourseInfo();
        
        String banner = json.getString(JSON_PAGESTYLE_BANNER);
        String leftFooter = json.getString(JSON_PAGESTYLE_LEFTFOOTER);
        String rightFooter = json.getString(JSON_PAGESTYLE_RIGHTFOOTER);
        String stylesheet = json.getString(JSON_PAGESTYLE_STYLESHEET);
        
        dataManager.addPageStyle(banner, leftFooter, rightFooter, stylesheet);
        
        // NOW LOAD ALL THE UNDERGRAD TAs
        JsonArray jsonTAArray = json.getJsonArray(JSON_UNDERGRAD_TAS);
        for (int i = 0; i < jsonTAArray.size(); i++) {
            JsonObject jsonTA = jsonTAArray.getJsonObject(i);
            String name = jsonTA.getString(JSON_NAME);
            String email = jsonTA.getString(JSON_EMAIL);
            dataManager.addTA(name, email);
        }

        // AND THEN ALL THE OFFICE HOURS
        JsonArray jsonOfficeHoursArray = json.getJsonArray(JSON_OFFICE_HOURS);
        for (int i = 0; i < jsonOfficeHoursArray.size(); i++) {
            JsonObject jsonOfficeHours = jsonOfficeHoursArray.getJsonObject(i);
            String day = jsonOfficeHours.getString(JSON_DAY);
            String time = jsonOfficeHours.getString(JSON_TIME);
            String name = jsonOfficeHours.getString(JSON_NAME);
            dataManager.addOfficeHoursReservation(day, time, name);
        }
        
        // NOW LOAD ALL THE RECITATIONS
        JsonArray jsonRecitationArray = json.getJsonArray(JSON_RECITATION_ITEMS);
        for (int i = 0; i < jsonRecitationArray.size(); i++) {
            JsonObject jsonRecitation = jsonRecitationArray.getJsonObject(i);
            String section = jsonRecitation.getString(JSON_RECITATION_SECTION);
            String instructor = jsonRecitation.getString(JSON_RECITATION_INSTRUCTOR);
            String daytime = jsonRecitation.getString(JSON_RECITATION_DAYTIME);
            String location = jsonRecitation.getString(JSON_RECITATION_LOCATION);
            String ta = jsonRecitation.getString(JSON_RECITATION_TA);
            String secondta = jsonRecitation.getString(JSON_RECITATION_SECOND_TA);
            dataManager.addRecitation(section, instructor, daytime, location, ta, secondta);
        }

        // NOW LOAD ALL THE SCHEDULES
        JsonArray jsonScheduleArray = json.getJsonArray(JSON_SCHEDULE);
        for (int i = 0; i < jsonScheduleArray.size(); i++) {
            JsonObject jsonSchedule = jsonScheduleArray.getJsonObject(i);
            String type = jsonSchedule.getString(JSON_SCHEDULE_TYPE);
            String date = jsonSchedule.getString(JSON_SCHEDULE_DATE);
            String time = jsonSchedule.getString(JSON_SCHEDULE_TIME);
            String schedTitle = jsonSchedule.getString(JSON_SCHEDULE_TITLE);
            String topic = jsonSchedule.getString(JSON_SCHEDULE_TOPIC);
            String link = jsonSchedule.getString(JSON_SCHEDULE_LINK);
            String criteria = jsonSchedule.getString(JSON_SCHEDULE_CRITERIA);
            dataManager.addScheduleItem(type, date, time, schedTitle, topic, link, criteria);
        }

        // NOW LOAD ALL THE TEAMS
        JsonArray jsonTeamsArray = json.getJsonArray(JSON_TEAMS);
        for (int i = 0; i < jsonTeamsArray.size(); i++) {
            JsonObject jsonTeam = jsonTeamsArray.getJsonObject(i);
            String teamName = jsonTeam.getString(JSON_TEAM_NAME);
            String teamColor = jsonTeam.getString(JSON_TEAM_COLOR);
            String teamTextColor = jsonTeam.getString(JSON_TEAM_TEXTCOLOR);
            String teamLink = jsonTeam.getString(JSON_TEAM_LINK);
            dataManager.addTeam(teamName, teamColor, teamTextColor, teamLink);
        }

        // NOW LOAD ALL THE STUDENTS
        JsonArray jsonStudentsArray = json.getJsonArray(JSON_STUDENTS);
        for (int i = 0; i < jsonStudentsArray.size(); i++) {
            JsonObject jsonStudent = jsonStudentsArray.getJsonObject(i);
            String firstName = jsonStudent.getString(JSON_STUDENT_FIRSTNAME);
            String lastName = jsonStudent.getString(JSON_STUDENT_LASTNAME);
            String team = jsonStudent.getString(JSON_STUDENT_TEAM);
            String role = jsonStudent.getString(JSON_STUDENT_ROLE);
            dataManager.addStudent(firstName, lastName, team, role);
        }
        
       
    }
      
    // HELPER METHOD FOR LOADING DATA FROM A JSON FORMAT
    private JsonObject loadJSONFile(String jsonFilePath) throws IOException {
	InputStream is = new FileInputStream(jsonFilePath);
	JsonReader jsonReader = Json.createReader(is);
	JsonObject json = jsonReader.readObject();
	jsonReader.close();
	is.close();
	return json;
    }

    @Override
    public void saveData(AppDataComponent data, String filePath) throws IOException {
	CourseSiteGeneratorData dataManager = (CourseSiteGeneratorData)data;

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
                    .add(JSON_SCHEDULE_TIME, schedule.getTime())
                    .add(JSON_SCHEDULE_TITLE, schedule.getTitle())
                    .add(JSON_SCHEDULE_TOPIC, schedule.getTopic())
                    .add(JSON_SCHEDULE_LINK, schedule.getLink())
                    .add(JSON_SCHEDULE_CRITERIA, schedule.getCriteria()).build();
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
	ArrayList<TimeSlot> officeHours = TimeSlot.buildOfficeHoursList(dataManager);
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
                .add(JSON_COURSE_SUBJECT,"" + dataManager.getCourseInfo().getSubject())
                .add(JSON_COURSE_NUMBER,"" + dataManager.getCourseInfo().getNumber())
                .add(JSON_COURSE_SEMESTER,"" + dataManager.getCourseInfo().getSemester())
                .add(JSON_COURSE_YEAR,"" + dataManager.getCourseInfo().getYear())
                .add(JSON_COURSE_TITLE,"" + dataManager.getCourseInfo().getTitle())
                .add(JSON_COURSE_INSTRUCTORNAME,"" + dataManager.getCourseInfo().getInstructorName())
                .add(JSON_COURSE_INSTRUCTORHOME,"" + dataManager.getCourseInfo().getInstructorHome())
                .add(JSON_COURSE_EXPORT,"" + dataManager.getCourseInfo().getDirectory())
                .add(JSON_PAGESTYLE_BANNER,""+ dataManager.getBannerPath())
                .add(JSON_PAGESTYLE_LEFTFOOTER,""+ dataManager.getLeftFooterPath())
                .add(JSON_PAGESTYLE_RIGHTFOOTER,""+ dataManager.getRightFooterPath())
                .add(JSON_PAGESTYLE_STYLESHEET,""+ dataManager.getStylesheet())
		.add(JSON_START_HOUR, "" + dataManager.getStartHour())
		.add(JSON_END_HOUR, "" + dataManager.getEndHour())
                .add(JSON_UNDERGRAD_TAS, undergradTAsArray)
                .add(JSON_OFFICE_HOURS, timeSlotsArray)
                .add(JSON_RECITATION_ITEMS, recitationItemsArray)
                .add(JSON_SCHEDULE, scheduleItemsArray)
                .add(JSON_TEAMS, teamsArray)
                .add(JSON_STUDENTS, studentsArray)
                .add(JSON_MONDAY, dataManager.getMonday())
                .add(JSON_FRIDAY, dataManager.getFriday())
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
    
    public void saveOfficeHoursData(AppDataComponent data, String filePath) throws IOException {
	CourseSiteGeneratorData dataManager = (CourseSiteGeneratorData)data;

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

        
        /*
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
                    .add(JSON_SCHEDULE_TIME, schedule.getTime())
                    .add(JSON_SCHEDULE_TITLE, schedule.getTitle())
                    .add(JSON_SCHEDULE_TOPIC, schedule.getTopic())
                    .add(JSON_SCHEDULE_LINK, schedule.getLink())
                    .add(JSON_SCHEDULE_CRITERIA, schedule.getCriteria()).build();
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
        */
	// NOW BUILD THE TIME SLOT JSON OBJCTS TO SAVE
        
	JsonArrayBuilder timeSlotArrayBuilder = Json.createArrayBuilder();
	ArrayList<TimeSlot> officeHours = TimeSlot.buildOfficeHoursList(dataManager);
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
               // .add(JSON_COURSE_SUBJECT,"" + dataManager.getCourseInfo().getSubject())
              //  .add(JSON_COURSE_NUMBER,"" + dataManager.getCourseInfo().getNumber())
              //  .add(JSON_COURSE_SEMESTER,"" + dataManager.getCourseInfo().getSemester())
              //  .add(JSON_COURSE_YEAR,"" + dataManager.getCourseInfo().getYear())
              //  .add(JSON_COURSE_TITLE,"" + dataManager.getCourseInfo().getTitle())
              //  .add(JSON_COURSE_INSTRUCTORNAME,"" + dataManager.getCourseInfo().getInstructorName())
             //   .add(JSON_COURSE_INSTRUCTORHOME,"" + dataManager.getCourseInfo().getInstructorHome())
             //   .add(JSON_COURSE_EXPORT,"" + dataManager.getCourseInfo().getDirectory())
             //   .add(JSON_PAGESTYLE_BANNER,""+ dataManager.getBannerPath())
             ////   .add(JSON_PAGESTYLE_LEFTFOOTER,""+ dataManager.getLeftFooterPath())
             //   .add(JSON_PAGESTYLE_RIGHTFOOTER,""+ dataManager.getRightFooterPath())
             //   .add(JSON_PAGESTYLE_STYLESHEET,""+ dataManager.getStylesheet())
		//.add(JSON_START_HOUR, "" + dataManager.getStartHour())
		//.add(JSON_END_HOUR, "" + dataManager.getEndHour())
                .add(JSON_UNDERGRAD_TAS, undergradTAsArray)
                .add(JSON_OFFICE_HOURS, timeSlotsArray)
             //   .add(JSON_RECITATION_ITEMS, recitationItemsArray)
              //  .add(JSON_SCHEDULE, scheduleItemsArray)
             // //  .add(JSON_TEAMS, teamsArray)
             //   .add(JSON_STUDENTS, studentsArray)
             //   .add(JSON_MONDAY, dataManager.getMonday())
             //   .add(JSON_FRIDAY, dataManager.getFriday())
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
    public void saveRecitationsData(AppDataComponent data, String filePath) throws IOException {
	CourseSiteGeneratorData dataManager = (CourseSiteGeneratorData)data;

        // NOW BUILD THE TA JSON OBJCTS TO SAVE
	/*JsonArrayBuilder taArrayBuilder = Json.createArrayBuilder();
	ObservableList<TeachingAssistant> tas = dataManager.getTeachingAssistants();
	for (TeachingAssistant ta : tas) {	    
	    JsonObject taJson = Json.createObjectBuilder()
		    .add(JSON_NAME, ta.getName())
		    .add(JSON_EMAIL, ta.getEmail()).build();
	    taArrayBuilder.add(taJson);
	}
	JsonArray undergradTAsArray = taArrayBuilder.build();

        
        */
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

        /*
        JsonArrayBuilder scheduleArrayBuilder = Json.createArrayBuilder();
	ObservableList<Schedule> schedules = dataManager.getScheduleItems();
	for (Schedule schedule : schedules) {	    
            
	    JsonObject scheduleJson = Json.createObjectBuilder()
		    .add(JSON_SCHEDULE_TYPE, schedule.getType())
		    .add(JSON_SCHEDULE_DATE, schedule.getDate())
                    .add(JSON_SCHEDULE_TIME, schedule.getTime())
                    .add(JSON_SCHEDULE_TITLE, schedule.getTitle())
                    .add(JSON_SCHEDULE_TOPIC, schedule.getTopic())
                    .add(JSON_SCHEDULE_LINK, schedule.getLink())
                    .add(JSON_SCHEDULE_CRITERIA, schedule.getCriteria()).build();
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
        */
	// NOW BUILD THE TIME SLOT JSON OBJCTS TO SAVE
        /*
	JsonArrayBuilder timeSlotArrayBuilder = Json.createArrayBuilder();
	ArrayList<TimeSlot> officeHours = TimeSlot.buildOfficeHoursList(dataManager);
	for (TimeSlot ts : officeHours) {	    
	    JsonObject tsJson = Json.createObjectBuilder()
		    .add(JSON_DAY, ts.getDay())
		    .add(JSON_TIME, ts.getTime())
		    .add(JSON_NAME, ts.getName()).build();
	    timeSlotArrayBuilder.add(tsJson);
	}
	JsonArray timeSlotsArray = timeSlotArrayBuilder.build();
        */
	// THEN PUT IT ALL TOGETHER IN A JsonObject
	JsonObject dataManagerJSO = Json.createObjectBuilder()
               // .add(JSON_COURSE_SUBJECT,"" + dataManager.getCourseInfo().getSubject())
              //  .add(JSON_COURSE_NUMBER,"" + dataManager.getCourseInfo().getNumber())
              //  .add(JSON_COURSE_SEMESTER,"" + dataManager.getCourseInfo().getSemester())
              //  .add(JSON_COURSE_YEAR,"" + dataManager.getCourseInfo().getYear())
              //  .add(JSON_COURSE_TITLE,"" + dataManager.getCourseInfo().getTitle())
              //  .add(JSON_COURSE_INSTRUCTORNAME,"" + dataManager.getCourseInfo().getInstructorName())
             //   .add(JSON_COURSE_INSTRUCTORHOME,"" + dataManager.getCourseInfo().getInstructorHome())
             //   .add(JSON_COURSE_EXPORT,"" + dataManager.getCourseInfo().getDirectory())
             //   .add(JSON_PAGESTYLE_BANNER,""+ dataManager.getBannerPath())
             ////   .add(JSON_PAGESTYLE_LEFTFOOTER,""+ dataManager.getLeftFooterPath())
             //   .add(JSON_PAGESTYLE_RIGHTFOOTER,""+ dataManager.getRightFooterPath())
             //   .add(JSON_PAGESTYLE_STYLESHEET,""+ dataManager.getStylesheet())
		//.add(JSON_START_HOUR, "" + dataManager.getStartHour())
		//.add(JSON_END_HOUR, "" + dataManager.getEndHour())
               // .add(JSON_UNDERGRAD_TAS, undergradTAsArray)
              //  .add(JSON_OFFICE_HOURS, timeSlotsArray)
                 .add(JSON_RECITATION_ITEMS, recitationItemsArray)
              //  .add(JSON_SCHEDULE, scheduleItemsArray)
             // //  .add(JSON_TEAMS, teamsArray)
             //   .add(JSON_STUDENTS, studentsArray)
             //   .add(JSON_MONDAY, dataManager.getMonday())
             //   .add(JSON_FRIDAY, dataManager.getFriday())
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
    
    // IMPORTING/EXPORTING DATA IS USED WHEN WE READ/WRITE DATA IN AN
    // ADDITIONAL FORMAT USEFUL FOR ANOTHER PURPOSE, LIKE ANOTHER APPLICATION

    @Override
    public void importData(AppDataComponent data, String filePath) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void exportData(AppDataComponent data, String filePath) throws IOException {
        
        File exportDir = new File(System.getProperty("user.dir")+"/CourseSiteGeneratorTester/public_html");
        File selectedDir = new File(filePath);
        FileUtils.copyDirectoryToDirectory(exportDir, selectedDir);
        saveOfficeHoursData(data, filePath + "/public_html/js/OfficeHoursGridData.json");
        saveRecitationsData(data, filePath + "/public_html/js/Recitations.json");
    
    }
}