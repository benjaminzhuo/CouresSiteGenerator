/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.style;

import djf.AppTemplate;
import csg.CourseSiteGeneratorApp;
import csg.data.TeachingAssistant;
import csg.workspace.CourseSiteGeneratorWorkspace;
import djf.components.AppStyleComponent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author benjaminzhuo
 */
public class CourseSiteGeneratorStyle extends AppStyleComponent
{
    CourseSiteGeneratorApp app;
    
    public static String WORKSPACE_PANE = "workspace_pane";
    public static String VBOX_PANE_STYLE = "vbox_pane_style";
    public static String GRID_PANE_STYLE = "grid_pane_style";
    public static String SITE_TEMPLATE_PANE = "site_template_pane";
    public static String PAGE_STYLE_PANE = "page_style_pane";
    public static String COURSE_DETAILS_PANE = "course_details_pane";
    public static String HEADER_STYLE = "header_style";
    public static String TAB_PANE_STYLE = "tab_pane_style";
    
    
    
     // WE'LL USE THIS FOR ORGANIZING LEFT AND RIGHT CONTROLS
    public static String CLASS_PLAIN_PANE = "plain_pane";
    
    // THESE ARE THE HEADERS FOR EACH SIDE
    public static String CLASS_HEADER_PANE = "header_pane";
    public static String CLASS_HEADER_LABEL = "header_label";

    // ON THE LEFT WE HAVE THE TA ENTRY
    public static String CLASS_TA_TABLE = "ta_table";
    public static String CLASS_TA_TABLE_COLUMN_HEADER = "ta_table_column_header";
    public static String CLASS_ADD_TA_PANE = "add_ta_pane";
    public static String CLASS_ADD_TA_TEXT_FIELD = "add_ta_text_field";
    public static String CLASS_ADD_TA_BUTTON = "add_ta_button";

    // ON THE RIGHT WE HAVE THE OFFICE HOURS GRID
    public static String CLASS_OFFICE_HOURS_GRID = "office_hours_grid";
    public static String CLASS_OFFICE_HOURS_GRID_TIME_COLUMN_HEADER_PANE = "office_hours_grid_time_column_header_pane";
    public static String CLASS_OFFICE_HOURS_GRID_TIME_COLUMN_HEADER_LABEL = "office_hours_grid_time_column_header_label";
    public static String CLASS_OFFICE_HOURS_GRID_DAY_COLUMN_HEADER_PANE = "office_hours_grid_day_column_header_pane";
    public static String CLASS_OFFICE_HOURS_GRID_DAY_COLUMN_HEADER_LABEL = "office_hours_grid_day_column_header_label";
    public static String CLASS_OFFICE_HOURS_GRID_TIME_CELL_PANE = "office_hours_grid_time_cell_pane";
    public static String CLASS_OFFICE_HOURS_GRID_TIME_CELL_LABEL = "office_hours_grid_time_cell_label";
    public static String CLASS_OFFICE_HOURS_GRID_TA_CELL_PANE = "office_hours_grid_ta_cell_pane";
    public static String CLASS_OFFICE_HOURS_GRID_TA_CELL_LABEL = "office_hours_grid_ta_cell_label";

    // FOR HIGHLIGHTING CELLS, COLUMNS, AND ROWS
    public static String CLASS_HIGHLIGHTED_GRID_CELL = "highlighted_grid_cell";
    public static String CLASS_HIGHLIGHTED_GRID_ROW_OR_COLUMN = "highlighted_grid_row_or_column";
    
    
    public CourseSiteGeneratorStyle(CourseSiteGeneratorApp initApp) {
        app = initApp;
        super.initStylesheet(app);
        app.getGUI().initFileToolbarStyle();
        initCourseSiteWorkspaceStyle();
    }

    private void initCourseSiteWorkspaceStyle() {
        
        CourseSiteGeneratorWorkspace workspaceComponent = (CourseSiteGeneratorWorkspace)app.getWorkspaceComponent();
        
        workspaceComponent.getWorkspacePane().getStyleClass().add(WORKSPACE_PANE);
        workspaceComponent.getTabPane().getStyleClass().add(TAB_PANE_STYLE);
        
        workspaceComponent.getCourseInfoPane().getStyleClass().add(GRID_PANE_STYLE);
        workspaceComponent.getSiteTemplatePane().getStyleClass().add(GRID_PANE_STYLE);
        workspaceComponent.getPageStylePane().getStyleClass().add(GRID_PANE_STYLE);
        workspaceComponent.getCourseDetailsPane().getStyleClass().add(VBOX_PANE_STYLE);
        workspaceComponent.getCourseInfoLabel().getStyleClass().add(HEADER_STYLE);
        workspaceComponent.getSiteTemplateLabel().getStyleClass().add(HEADER_STYLE);
        workspaceComponent.getPageStyleLabel().getStyleClass().add(HEADER_STYLE);
        
        workspaceComponent.getRecitationLabel().getStyleClass().add(HEADER_STYLE);
        workspaceComponent.getRecitationPagePane().getStyleClass().add(VBOX_PANE_STYLE);
        workspaceComponent.getRecitationDataPane().getStyleClass().add(GRID_PANE_STYLE);
        
        workspaceComponent.getProjectsPane().getStyleClass().add(VBOX_PANE_STYLE);
        workspaceComponent.getStudentsPane().getStyleClass().add(GRID_PANE_STYLE);
        workspaceComponent.getTeamsPane().getStyleClass().add(GRID_PANE_STYLE);
        workspaceComponent.getStudentsLabel().getStyleClass().add(HEADER_STYLE);
        workspaceComponent.getTeamsLabel().getStyleClass().add(HEADER_STYLE);
        workspaceComponent.getProjectsLabel().getStyleClass().add(HEADER_STYLE);
        
        workspaceComponent.getSchedulePagePane().getStyleClass().add(VBOX_PANE_STYLE);
        workspaceComponent.getCalendarBoundariesPane().getStyleClass().add(GRID_PANE_STYLE);
        workspaceComponent.getScheduleItemsPane().getStyleClass().add(GRID_PANE_STYLE);
        workspaceComponent.getScheduleItemsLabel().getStyleClass().add(HEADER_STYLE);
        workspaceComponent.getCalendarBoundariesLabel().getStyleClass().add(HEADER_STYLE);
        
        
        
        
         workspaceComponent.getTAsHeaderBox().getStyleClass().add(CLASS_HEADER_PANE);
        workspaceComponent.getTAsHeaderLabel().getStyleClass().add(CLASS_HEADER_LABEL);

        // LEFT SIDE - THE TABLE
        TableView<TeachingAssistant> taTable = workspaceComponent.getTATable();
        taTable.getStyleClass().add(CLASS_TA_TABLE);
        for (TableColumn tableColumn : taTable.getColumns()) {
            tableColumn.getStyleClass().add(CLASS_TA_TABLE_COLUMN_HEADER);
        }

        // LEFT SIDE - THE TA DATA ENTRY
        workspaceComponent.getAddBox().getStyleClass().add(CLASS_ADD_TA_PANE);
        workspaceComponent.getNameTextField().getStyleClass().add(CLASS_ADD_TA_TEXT_FIELD);
        workspaceComponent.getEmailTextField().getStyleClass().add(CLASS_ADD_TA_TEXT_FIELD);
        workspaceComponent.getAddButton().getStyleClass().add(CLASS_ADD_TA_BUTTON);
        workspaceComponent.getClearButton().getStyleClass().add(CLASS_ADD_TA_BUTTON);

        // RIGHT SIDE - THE HEADER
        workspaceComponent.getOfficeHoursSubheaderBox().getStyleClass().add(CLASS_HEADER_PANE);
        workspaceComponent.getOfficeHoursSubheaderLabel().getStyleClass().add(CLASS_HEADER_LABEL);
    
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void initOfficeHoursGridStyle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
