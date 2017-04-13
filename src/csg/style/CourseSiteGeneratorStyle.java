/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.style;

import djf.AppTemplate;
import csg.CourseSiteGeneratorApp;
import csg.workspace.CourseSiteGeneratorWorkspace;
import djf.components.AppStyleComponent;

/**
 *
 * @author benjaminzhuo
 */
public class CourseSiteGeneratorStyle extends AppStyleComponent
{
    CourseSiteGeneratorApp app;
    
    public static String WORKSPACE_PANE = "workspace_pane";
    public static String COURSE_INFO_PANE = "course_info_pane";
    public static String SITE_TEMPLATE_PANE = "site_template_pane";
    public static String PAGE_STYLE_PANE = "page_style_pane";
    
    public CourseSiteGeneratorStyle(CourseSiteGeneratorApp initApp) {
        app = initApp;
        super.initStylesheet(app);
        app.getGUI().initFileToolbarStyle();
        initCourseSiteWorkspaceStyle();
    }

    private void initCourseSiteWorkspaceStyle() {
        
        CourseSiteGeneratorWorkspace workspaceComponent = (CourseSiteGeneratorWorkspace)app.getWorkspaceComponent();
        workspaceComponent.getCourseInfoPane().getStyleClass().add(COURSE_INFO_PANE);
        workspaceComponent.getWorkspacePane().getStyleClass().add(WORKSPACE_PANE);
        workspaceComponent.getSiteTemplatePane().getStyleClass().add(SITE_TEMPLATE_PANE);
        workspaceComponent.getPageStylePane().getStyleClass().add(PAGE_STYLE_PANE);
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
