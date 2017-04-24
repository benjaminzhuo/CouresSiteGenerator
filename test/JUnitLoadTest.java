/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import csg.CourseSiteGeneratorApp;
import csg.data.CourseSiteGeneratorData;
import csg.data.Recitation;
import csg.data.TeachingAssistant;
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
      //  fail("The test case is a prototype.");
    }
    
}
