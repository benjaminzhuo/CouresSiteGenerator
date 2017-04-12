/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;


import djf.components.AppDataComponent;
import csg.CourseSiteGeneratorApp;
/**
 *
 * @author benjaminzhuo
 */
public class CourseSiteGeneratorData implements AppDataComponent
{
    CourseSiteGeneratorApp app;
            
    public CourseSiteGeneratorData(CourseSiteGeneratorApp initApp) 
    {
        app = initApp;
    }

    
    @Override
    public void resetData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
