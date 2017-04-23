/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.test_bed;

import csg.CourseSiteGeneratorApp;
import csg.data.CourseSiteGeneratorData;
import csg.data.Recitation;
import csg.data.TeachingAssistant;
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
    public static void main(String[] args){
      
        
        CourseSiteGeneratorApp app = new CourseSiteGeneratorApp();
        app.loadProperties(APP_PROPERTIES_FILE_NAME);
        CourseSiteGeneratorData data = new CourseSiteGeneratorData(app);
        //CourseSiteGeneratorFiles files = new CourseSiteGeneratorFiles(app);
 
        
        data.addTA("Dan","dan@gmail.com");
        
        data.addRecitation("RO2", "McKenna","Wed 3:30pm-4:23pm","Old CS 2114","Jane Doe", "Joe Schmo");
        data.addScheduleItem("Holiday","2/9/207","3:00pm","SNOWDAY","Event Programming","asdfa.com","criteria");
        data.addTeam("Atomic Comic","552211","ffffff","atomic.com");
        data.addStudent("Jane","Doe","Atomic Comic","Data Designer");
        
        try{
        saveData(data, "/Users/benjaminzhuo/NetBeansProjects/jsonsavefiles/TestFileName.json");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public static void saveData(AppDataComponent data, String filePath) throws IOException {
	// GET THE DATA
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
		.add(JSON_START_HOUR, "" + dataManager.getStartHour())
		.add(JSON_END_HOUR, "" + dataManager.getEndHour())
                .add(JSON_RECITATION_ITEMS, recitationItemsArray)
                .add(JSON_UNDERGRAD_TAS, undergradTAsArray)
              //  .add(JSON_OFFICE_HOURS, timeSlotsArray)
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
