
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
import java.sql.Date;

/**
 *
 * @author chaturvedsumanth
 */
public class ReservationQueries {
    
    // private ReservationEntry reservationentry;
    private PreparedStatement addreservationentry;
    private PreparedStatement getreservationsbyfaculty;
    private PreparedStatement getreservationsbydate;
    private PreparedStatement getroomsreservedbydate;
    private PreparedStatement deletereservation;
    private PreparedStatement getreservationsbyroom;
    // private ResultSet resultset;
    
    public ReservationQueries(DBConnection dbconnection){
        try{
            
            this.addreservationentry=dbconnection.getConnection().prepareStatement("INSERT INTO Reservations (Faculty,Room,Date,Seats,Timestamp) VALUES (?,?,?,?,?)");
            this.getreservationsbyfaculty=dbconnection.getConnection().prepareStatement("SELECT * FROM Reservations WHERE Faculty=? ORDER BY Date");
            this.getreservationsbydate=dbconnection.getConnection().prepareStatement("SELECT * FROM Reservations WHERE Date=?");
            this.getroomsreservedbydate=dbconnection.getConnection().prepareStatement("SELECT Room,Seats FROM Reservations WHERE Date=?");
            this.deletereservation=dbconnection.getConnection().prepareStatement("DELETE FROM Reservations WHERE Faculty=? AND Date=?");
            this.getreservationsbyroom=dbconnection.getConnection().prepareStatement("SELECT Faculty,Room,Date,Seats,Timestamp FROM Reservations WHERE Room=?");
        }
        catch(SQLException sqlexception){
            
            sqlexception.printStackTrace();
        }
    }
        
    public void addReservationEntry(ReservationEntry reservationentry){
        try{
            
            this.addreservationentry.setString(1, reservationentry.getFaculty());
            this.addreservationentry.setString(2, reservationentry.getRoom());
            this.addreservationentry.setDate(3, reservationentry.getDate());
            this.addreservationentry.setInt(4, reservationentry.getSeats());
            this.addreservationentry.setTimestamp(5,reservationentry.getTimestamp());
            this.addreservationentry.executeUpdate();
        }
        catch(SQLException sqlexception){
            sqlexception.printStackTrace();
        }
        
    }
    
    public ArrayList<ReservationEntry> getReservationsByFaculty(String Faculty){
     
        ArrayList<ReservationEntry> results=null;
        ResultSet resultset=null;
        try{
            this.getreservationsbyfaculty.setString(1, Faculty);
            resultset=this.getreservationsbyfaculty.executeQuery();
            results= new ArrayList<>();
            
            while(resultset.next()){
                
                results.add(new ReservationEntry(resultset.getString("Faculty"),resultset.getString("Room"),resultset.getDate("Date"),resultset.getInt("Seats"),resultset.getTimestamp("Timestamp")));
            }
              
        }
        catch(SQLException sqlexception){
            
            sqlexception.printStackTrace();
        }
        
        return results;
    }
    
    public ArrayList<ReservationEntry> getReservationsByDate(Date date){
        
        ArrayList<ReservationEntry> results=null;
        ResultSet resultset=null;
        try{
            this.getreservationsbydate.setDate(1, date);
            resultset=this.getreservationsbydate.executeQuery();
            results= new ArrayList<>();
            
            while(resultset.next()){
                
                results.add(new ReservationEntry(resultset.getString("Faculty"),resultset.getString("Room"),resultset.getDate("Date"),resultset.getInt("Seats"),resultset.getTimestamp("Timestamp")));
            }
              
        }
        catch(SQLException sqlexception){
            
            sqlexception.printStackTrace();
        }
        
        return results;
    }
    
    public ArrayList<RoomEntry> getRoomsReservedByDate(Date date){
        
        ArrayList<RoomEntry> results=null;
        ResultSet resultset=null;
        try{
            this.getroomsreservedbydate.setDate(1, date);
            resultset=this.getroomsreservedbydate.executeQuery();
            results= new ArrayList<>();
            
            while(resultset.next()){
                
                results.add(new RoomEntry(resultset.getString("Room"),resultset.getInt("Seats")));
            }
              
        }
        catch(SQLException sqlexception){
            
            sqlexception.printStackTrace();
        }
        
        return results;
    }
    
    public int deleteReservation(ReservationEntry reservationentry){
        
        int cnt=0;
        
        try{
            
            this.deletereservation.setString(1, reservationentry.getFaculty());
            this.deletereservation.setDate(2, reservationentry.getDate());
            cnt=this.deletereservation.executeUpdate();
        }
        catch(SQLException sqlexception){
            
            sqlexception.printStackTrace();
        }
        
        return cnt;
        
    }
    
    public ArrayList<ReservationEntry> getReservationsByRoom(String roomName){
        
        ArrayList<ReservationEntry> results=null;
        ResultSet resultset=null;
        try{
            this.getreservationsbyroom.setString(1, roomName);
            resultset=this.getreservationsbyroom.executeQuery();
            results= new ArrayList<>();
            
            while(resultset.next()){
                
                results.add(new ReservationEntry(resultset.getString("Faculty"),resultset.getString("Room"),resultset.getDate("Date"),resultset.getInt("Seats"),resultset.getTimestamp("Timestamp")));
            }
              
        }
        catch(SQLException sqlexception){
            
            sqlexception.printStackTrace();
        }
        
        return results;
    
    }
}


