
package csg;

import csg.data.CourseSiteGeneratorData;
import csg.file.CourseSiteGeneratorFiles;
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
        dataComponent = new CourseSiteGeneratorData(this);
        workspaceComponent = new CourseSiteGeneratorWorkspace(this);      
        fileComponent = new CourseSiteGeneratorFiles(this);
        styleComponent = new CourseSiteGeneratorStyle(this);
    }
    
    public static void main(String[] args) 
    {
	Locale.setDefault(Locale.US);
	launch(args);
    }
   
}
