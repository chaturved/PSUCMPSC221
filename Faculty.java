/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roomscheduler;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author chaturvedsumanth
 */
public class Faculty {
    
    //private String Name;
    private PreparedStatement insertFaculty;
    private PreparedStatement retrieveAllFaculty;
    //private ResultSet resultSet;
    
    public Faculty(DBConnection dbconnection){
       try{
          
          this.insertFaculty=dbconnection.getConnection().prepareStatement("INSERT INTO Faculty (Name) VALUES (?)");
          this.retrieveAllFaculty=dbconnection.getConnection().prepareStatement("SELECT Name FROM Faculty");
       }
       catch(SQLException sqlexception){
            
            sqlexception.printStackTrace();
        }
    }
    public String addFaculty(String name){
        
        try{
           
           this.insertFaculty.setString(1, name);
           this.insertFaculty.executeUpdate();
          
        }
        catch(SQLException sqlexception){
            if(sqlexception instanceof SQLIntegrityConstraintViolationException){
                return "Faculty Already Exists";
            }
            else{
                sqlexception.printStackTrace();
                return "Error";
            }
            
        }
        
        return "Faculty Sucessfully Added";
    }
    
    public ArrayList<String> getAllFaculty(){
        
        ArrayList<String> results=null;
        ResultSet resultset=null;
        try{
            
            resultset=this.retrieveAllFaculty.executeQuery();
            results= new ArrayList<>();
            
            while(resultset.next()){
                
                results.add(resultset.getString("Name"));
            }
              
        }
        catch(SQLException sqlexception){
            
            sqlexception.printStackTrace();
        }
        
        return results;
    }
    
}
