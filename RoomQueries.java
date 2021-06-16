/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roomscheduler;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
/**
 *
 * @author chaturvedsumanth
 */
public class RoomQueries {
    
    // private RoomEntry roomentry;
    private PreparedStatement addroom;
    private PreparedStatement getallpossiblerooms;
    private PreparedStatement getallroomsnames;
    private PreparedStatement droproom;
    //private ResultSet resultset;
    
    public RoomQueries(DBConnection dbconnection){
        try{
            
           this.addroom=dbconnection.getConnection().prepareStatement("INSERT INTO Rooms (Name,Seats) VALUES (?,?)");
           this.getallpossiblerooms=dbconnection.getConnection().prepareStatement("SELECT Name,Seats FROM Rooms");
           this.getallroomsnames=dbconnection.getConnection().prepareStatement("SELECT Name FROM Rooms");
           this.droproom=dbconnection.getConnection().prepareStatement("DELETE FROM Rooms WHERE Name=?");
        }
        catch(SQLException sqlexception){
            
            sqlexception.printStackTrace();
        }
    }
    
    public void addRoom(RoomEntry roomentry){
        try{
            
            this.addroom.setString(1,roomentry.getName());
            this.addroom.setInt(2,roomentry.getSeats());
            this.addroom.executeUpdate();
        }
        catch(SQLException sqlexception){
            
            sqlexception.printStackTrace();
        }
    }
    
    public ArrayList<RoomEntry> getAllPossibleRooms(){
        
        ArrayList<RoomEntry> results=null;
        ResultSet resultset=null;
        try{
            
            resultset=this.getallpossiblerooms.executeQuery();
            results= new ArrayList<>();
            
            while(resultset.next()){
                
                results.add(new RoomEntry(resultset.getString("Name"),resultset.getInt("Seats")));
            }
              
        }
        catch(SQLException sqlexception){
            
            sqlexception.printStackTrace();
        }
        
        return results;
    }
    
    public ArrayList<String> getAllRoomsNames(){
        
        ArrayList<String> results=null;
        ResultSet resultset=null;
        try{
            
            resultset=this.getallroomsnames.executeQuery();
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
    
    public void dropRoom(String roomname){
    
        try{
        
            this.droproom.setString(1, roomname);
            this.droproom.executeUpdate();
        }
        catch(SQLException sqlexception){
        
            sqlexception.printStackTrace();
        }
    }
        
}
