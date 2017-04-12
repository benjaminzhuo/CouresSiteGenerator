
package csg;

import csg.data.CourseData;
import csg.data.CourseSiteGeneratorData;
import csg.data.ProjectData;
import csg.data.RecitationData;
import csg.data.ScheduleData;
import csg.data.TAData;
import csg.file.CourseFiles;
import csg.file.CourseSiteGeneratorFiles;
import csg.file.ProjectFiles;
import csg.file.RecitationFiles;
import csg.file.ScheduleFiles;
import csg.file.TAFiles;
import csg.style.CourseSiteGeneratorStyle;
import csg.workspace.CourseSiteGeneratorWorkspace;
import djf.AppTemplate;
import java.util.Locale;
import static javafx.application.Application.launch;

/**
 *
 * @author benjaminzhuo
 */
public class CourseSiteGeneratorApp extends AppTemplate
{

    @Override
    public void buildAppComponentsHook() 
    {
       /* courseDataComponent = new CourseData(this);
        projectDataComponent = new ProjectData(this);
        recitationDataComponent = new RecitationData(this);
        scheduleDataComponent = new ScheduleData(this);
        taDataComponent = new TAData(this);
        */
        dataComponent = new CourseSiteGeneratorData(this);
        workspaceComponent = new CourseSiteGeneratorWorkspace(this);      
        fileComponent = new CourseSiteGeneratorFiles(this);
       /* courseFileComponent = new CourseFiles(this);
        projectFileComponent = new ProjectFiles(this);
        RecitatinFileComponent = new RecitationFiles(this);
        ScheduleFileComponent = new ScheduleFiles(this);
        taFileComponent = new TAFiles(this);
        */
        styleComponent = new CourseSiteGeneratorStyle(this);
    }
    
    public static void main(String[] args) 
    {
	Locale.setDefault(Locale.US);
	launch(args);
    }
   
}
