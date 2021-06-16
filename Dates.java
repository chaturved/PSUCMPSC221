/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roomscheduler;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

/**
 *
 * @author chaturvedsumanth
 */
public class Dates {
    
    // private Date date;
    private PreparedStatement adddate;
    private PreparedStatement getalldates;
    // private ResultSet resultset;
            
    public Dates(DBConnection dbconnection){
        try{
            
            this.adddate=dbconnection.getConnection().prepareStatement("INSERT INTO DATES (Date) VALUES (?)");
            this.getalldates=dbconnection.getConnection().prepareStatement("SELECT Date FROM DATES"); 
        }
        catch(SQLException sqlexception){
            
            sqlexception.printStackTrace();
        }
    }
        
    
    
    public void addDate(Date date){
        try{
            
           this.adddate.setDate(1,date);
           this.adddate.executeUpdate();
        }
        catch(SQLException sqlexception){
            
            sqlexception.printStackTrace();
        }  
          
    }
    
    public ArrayList<Date> getAllDates(){
       
        ArrayList<Date> results=null;
        ResultSet resultset=null;
        try{
            
            resultset=this.getalldates.executeQuery();
            results= new ArrayList<>();
            
            while(resultset.next()){
                
                results.add(resultset.getDate("Date"));
            }
              
        }
        catch(SQLException sqlexception){
            
            sqlexception.printStackTrace();
        }
        
        return results;
    }
}
