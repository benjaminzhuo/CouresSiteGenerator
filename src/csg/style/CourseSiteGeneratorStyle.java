/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.style;

import djf.AppTemplate;
import csg.CourseSiteGeneratorApp;
import djf.components.AppStyleComponent;

/**
 *
 * @author benjaminzhuo
 */
public class CourseSiteGeneratorStyle extends AppStyleComponent
{
    CourseSiteGeneratorApp app;
    
    public CourseSiteGeneratorStyle(CourseSiteGeneratorApp initApp) {
        app = initApp;
        super.initStylesheet(app);
        app.getGUI().initFileToolbarStyle();
        initCourseSiteWorkspaceStyle();
    }

    private void initCourseSiteWorkspaceStyle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
