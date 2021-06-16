/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roomscheduler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author chaturvedsumanth
 */
public class DBConnection {
    private Connection connection;
    private final String DATABASE_URL;
    
    public DBConnection(){
        
        this.DATABASE_URL="jdbc:derby://localhost:1527/RoomSchedulerChaturvedCSL5322";
        
        try{
            
          this.connection=DriverManager.getConnection(this.DATABASE_URL,"java","java");
          
        }
        catch(SQLException sqlException){
            
            sqlException.printStackTrace();
        }
     
    }
    
    public Connection getConnection(){
        
        return this.connection;
    }
    
}
