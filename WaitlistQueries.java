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
public class WaitlistQueries {
    
    // private ReservationEntry waitlistentry;
    private PreparedStatement addwaitlistentry;
    private PreparedStatement getwaitlistbyfaculty;
    private PreparedStatement getwaitlistbydate;
    private PreparedStatement deletewaitlistentry;
    private PreparedStatement getwaitlistDTR; // order: date then reservation
    private PreparedStatement getwaitlistbydateR; // retrieving using date, ordering by reservation
    // private ResultSet resultset;
    
    public WaitlistQueries(DBConnection dbconnection){
        try{
            
            this.addwaitlistentry=dbconnection.getConnection().prepareStatement("INSERT INTO Waitlist (Faculty,Date,Seats,Timestamp) VALUES (?,?,?,?)");
            this.getwaitlistbyfaculty=dbconnection.getConnection().prepareStatement("SELECT * FROM Waitlist WHERE Faculty=? ORDER BY Date");
            this.getwaitlistbydate=dbconnection.getConnection().prepareStatement("SELECT * FROM Waitlist WHERE Date=?");
            this.deletewaitlistentry=dbconnection.getConnection().prepareStatement("DELETE FROM Waitlist WHERE Faculty=? AND Date=?");
            this.getwaitlistDTR=dbconnection.getConnection().prepareStatement("SELECT Faculty,Date,Seats,Timestamp FROM Waitlist ORDER BY Date,Timestamp");
            this.getwaitlistbydateR=dbconnection.getConnection().prepareStatement("SELECT Faculty,Date,Seats,Timestamp FROM Waitlist WHERE Date=? ORDER BY Timestamp");
        }
        catch(SQLException sqlexception){
            
            sqlexception.printStackTrace();
        }
    }
        
    public void addWaitlistEntry(WaitlistEntry waitlistentry){
        
        try{
            
            this.addwaitlistentry.setString(1, waitlistentry.getFaculty());
            this.addwaitlistentry.setDate(2, waitlistentry.getDate());
            this.addwaitlistentry.setInt(3, waitlistentry.getSeats());
            this.addwaitlistentry.setTimestamp(4,waitlistentry.getTimestamp());
            this.addwaitlistentry.executeUpdate();
        }
        catch(SQLException sqlexception){
            
            sqlexception.printStackTrace();
        }
    }
    
    public ArrayList<WaitlistEntry> getWaitlistByFaculty(String Faculty){
        
        ArrayList<WaitlistEntry> results=null;
        ResultSet resultset=null;
        try{
            this.getwaitlistbyfaculty.setString(1, Faculty);
            resultset=this.getwaitlistbyfaculty.executeQuery();
            results= new ArrayList<>();
            
            while(resultset.next()){
                
                results.add(new WaitlistEntry(resultset.getString("Faculty"),resultset.getDate("Date"),resultset.getInt("Seats"),resultset.getTimestamp("Timestamp")));
            }
              
        }
        catch(SQLException sqlexception){
            
            sqlexception.printStackTrace();
        }
        
        return results;
    }
    
    public ArrayList<WaitlistEntry> getWaitlistByDate(Date date){
        
        ArrayList<WaitlistEntry> results=null;
        ResultSet resultset=null;
        try{
            this.getwaitlistbydate.setDate(1, date);
            resultset=this.getwaitlistbydate.executeQuery();
            results= new ArrayList<>();
            
            while(resultset.next()){
                
                results.add(new WaitlistEntry(resultset.getString("Faculty"),resultset.getDate("Date"),resultset.getInt("Seats"),resultset.getTimestamp("Timestamp")));
            }
              
        }
        catch(SQLException sqlexception){
            
            sqlexception.printStackTrace();
        }
        
        return results;
    }
    
    public int deleteWaitlistEntry(WaitlistEntry waitlistentry){
        
        int cnt=0;
        
        try{
            
            this.deletewaitlistentry.setString(1, waitlistentry.getFaculty());
            this.deletewaitlistentry.setDate(2, waitlistentry.getDate());
            cnt=this.deletewaitlistentry.executeUpdate();
        }
        catch(SQLException sqlexception){
            
            sqlexception.printStackTrace();
        }
        
        return cnt;
    }
    
    public ArrayList<WaitlistEntry> getWaitlistDTR(){
        ArrayList<WaitlistEntry> results=null;
        ResultSet resultset=null; 
        try{
            resultset=this.getwaitlistDTR.executeQuery();
            results=new ArrayList<>();
            
            while(resultset.next()){
                results.add(new WaitlistEntry(resultset.getString("Faculty"),resultset.getDate("Date"),resultset.getInt("Seats"),resultset.getTimestamp("Timestamp")));
            }
        }
        catch(SQLException sqlexception){
            sqlexception.printStackTrace();
        }
        
        return results;
    }
    
    public ArrayList<WaitlistEntry> getWaitlistByDateR(Date date){
        ArrayList<WaitlistEntry> results=null;
        ResultSet resultset=null; 
        try{
            this.getwaitlistbydateR.setDate(1, date);
            resultset=this.getwaitlistbydateR.executeQuery();
            results=new ArrayList<>();
            
            while(resultset.next()){
                results.add(new WaitlistEntry(resultset.getString("Faculty"),resultset.getDate("Date"),resultset.getInt("Seats"),resultset.getTimestamp("Timestamp")));
            }
        }
        catch(SQLException sqlexception){
            sqlexception.printStackTrace();
        }
        
        return results;
    }
    
}
