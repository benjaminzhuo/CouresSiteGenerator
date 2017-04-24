/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import csg.CourseSiteGeneratorApp;
import csg.data.CourseSiteGeneratorData;
import csg.data.Recitation;
import csg.data.Schedule;
import csg.data.Student;
import csg.data.TeachingAssistant;
import csg.data.Team;
import csg.test_bed.TestSave;
import djf.components.AppDataComponent;
import static djf.settings.AppStartupConstants.APP_PROPERTIES_FILE_NAME;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author benjaminzhuo
 */
public class JUnitLoadTest {
    
    public JUnitLoadTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

 
    /**
     * Test of loadData method, of class TestSave.
     */
    @Test
    public void testLoadData() throws Exception {
        System.out.println("loadData");
        CourseSiteGeneratorApp app = new CourseSiteGeneratorApp();
        app.loadProperties(APP_PROPERTIES_FILE_NAME);
        CourseSiteGeneratorData data = new CourseSiteGeneratorData(app);
        String filePath = "/Users/benjaminzhuo/NetBeansProjects/jsonsavefiles/SiteSaveTest.json";
       
        TestSave.loadData(data, filePath);
        
        //TEST IF THE TAs WERE LOADED PROPERLY
        String testMonday = "4/22/2012";
        String testFriday = "4/22/2012";
        assertEquals(testMonday, data.getMonday());
        assertEquals(testFriday, data.getFriday());
        
        
        
        ObservableList <TeachingAssistant> testTAs = FXCollections.observableArrayList();
        TeachingAssistant testTA = new TeachingAssistant("Dan","dan@gmail.com");
        testTAs.add(testTA);
        for(int i = 0; i < testTAs.size();i++)
        {
            assertEquals(testTAs.get(i).getName(), ((TeachingAssistant)data.getTeachingAssistants().get(i)).getName());
            assertEquals(testTAs.get(i).getEmail(), ((TeachingAssistant)data.getTeachingAssistants().get(i)).getEmail());
        }
        
        //TEST IF THE TIMESLOTS WERE LOADED PROPERLY
        
        //TEST IF THE RECITATIONS WERE LOADED PROPERLY
        ObservableList <Recitation> testRecitations = FXCollections.observableArrayList();
        Recitation testRecitation = new Recitation("RO2", "McKenna","Wed 3:30pm-4:23pm","Old CS 2114","Jane Doe", "Joe Schmo");
        testRecitations.add(testRecitation);
        for(int i = 0; i < testRecitations.size();i++)
        {
            assertEquals(testRecitations.get(i).getSection(), ((Recitation)data.getRecitations().get(i)).getSection());
            assertEquals(testRecitations.get(i).getInstructor(), ((Recitation)data.getRecitations().get(i)).getInstructor());
            assertEquals(testRecitations.get(i).getDayTime(), ((Recitation)data.getRecitations().get(i)).getDayTime());
            assertEquals(testRecitations.get(i).getLocation(), ((Recitation)data.getRecitations().get(i)).getLocation());
            assertEquals(testRecitations.get(i).getFirstTA(), ((Recitation)data.getRecitations().get(i)).getFirstTA());
            assertEquals(testRecitations.get(i).getSecondTA(), ((Recitation)data.getRecitations().get(i)).getSecondTA());
        }
        
        //TEST IF THE SCHEDULE ITEMS WERE LOADED PROPERLY
        
        ObservableList <Schedule> testSchedules = FXCollections.observableArrayList();
        Schedule testSchedule = new Schedule("Holiday","2/9/207","3:00pm","SNOWDAY","Event Programming","asdfa.com","criteria");
        testSchedules.add(testSchedule);
        for(int i = 0; i < testSchedules.size();i++)
        {
            assertEquals(testSchedules.get(i).getType(), ((Schedule)data.getScheduleItems().get(i)).getType());
            assertEquals(testSchedules.get(i).getDate(), ((Schedule)data.getScheduleItems().get(i)).getDate());
            assertEquals(testSchedules.get(i).getTime(), ((Schedule)data.getScheduleItems().get(i)).getTime());
            assertEquals(testSchedules.get(i).getTitle(), ((Schedule)data.getScheduleItems().get(i)).getTitle());
            assertEquals(testSchedules.get(i).getTopic(), ((Schedule)data.getScheduleItems().get(i)).getTopic());
            assertEquals(testSchedules.get(i).getLink(), ((Schedule)data.getScheduleItems().get(i)).getLink());
            assertEquals(testSchedules.get(i).getCriteria(), ((Schedule)data.getScheduleItems().get(i)).getCriteria());
         
        }
        
        //TEST IF THE SCHEDULE ITEMS WERE LOADED PROPERLY
        
        ObservableList <Team> testTeams = FXCollections.observableArrayList();
        Team testTeam = new Team("Atomic Comic","552211","ffffff","atomic.com");
        testTeams.add(testTeam);
        for(int i = 0; i < testTeams.size();i++)
        {
           assertEquals(testTeams.get(i).getName(), ((Team)data.getTeams().get(i)).getName());
           assertEquals(testTeams.get(i).getColor(), ((Team)data.getTeams().get(i)).getColor());
           assertEquals(testTeams.get(i).getTextColor(), ((Team)data.getTeams().get(i)).getTextColor());
           assertEquals(testTeams.get(i).getLink(), ((Team)data.getTeams().get(i)).getLink());
        }
        
        //TEST IF THE SCHEDULE ITEMS WERE LOADED PROPERLY
        
        ObservableList <Student> testStudents = FXCollections.observableArrayList();
        Student testStudent = new Student("Jane","Doe","Atomic Comic","Data Designer");
        testStudents.add(testStudent);
        for(int i = 0; i < testStudents.size();i++)
        {
            assertEquals(testStudents.get(i).getFirstName(), ((Student)data.getStudents().get(i)).getFirstName());
            assertEquals(testStudents.get(i).getLastName(), ((Student)data.getStudents().get(i)).getLastName());
            assertEquals(testStudents.get(i).getTeam(), ((Student)data.getStudents().get(i)).getTeam());
            assertEquals(testStudents.get(i).getRole(), ((Student)data.getStudents().get(i)).getRole());
        }
        
        
      //  fail("The test case is a prototype.");
    }
    
}
