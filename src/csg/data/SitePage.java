/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author benjaminzhuo
 */
public class SitePage {
    
    private final StringProperty use;
    private final StringProperty navbarTitle;
    private final StringProperty fileName;  
    private final StringProperty script;
    
    public SitePage(String initUse, String initNavbarTitle, String initFileName, String initScript){
        
        use =  new SimpleStringProperty(initUse);
        navbarTitle =  new SimpleStringProperty(initNavbarTitle);
        fileName =  new SimpleStringProperty(initFileName);
        script =  new SimpleStringProperty(initScript);
        
    }
    
    public String getUse(){
        return use.get();
    }
    public String getNavbarTitle(){
        return navbarTitle.get();
    }
    public String getFileName(){
        return fileName.get();
    }
    public String getScript(){
        return script.get();
    }
}
