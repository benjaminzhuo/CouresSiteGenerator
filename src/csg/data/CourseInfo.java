/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csg.data;

/**
 *
 * @author benjaminzhuo
 */
public class CourseInfo {
    
    protected String subject;
    protected String number;
    protected String semester;
    protected String year;
    protected String title;
    protected String instructorName;
    protected String instructorHome;
    protected String exportDirectory;
    
    public CourseInfo(){
        
    }
    
    public CourseInfo(String initSubject, String initNumber, String initSemester,String initYear, String initTitle,String initInstructorName,
                            String initInstructorHome, String initExportDirectory)
    {
        subject = initSubject;
        number = initNumber;
        semester = initSemester;
        year = initYear;
        title = initTitle;
        instructorName = initInstructorName;
        instructorHome = initInstructorHome;
        exportDirectory = initExportDirectory;
                
    }
    
    public String getSubject(){
        return subject;
    }
    public String getNumber(){
        return number;
    }
    public String getSemester(){
        return semester;
    }
    public String getYear(){
        return year;
    }
    public String getTitle(){
        return title;
    }
    public String getInstructorName(){
        return instructorName;
    }
    public String getInstructorHome(){
        return instructorHome;
    }
    public String getDirectory(){
        return exportDirectory;
    }
}
    
    

