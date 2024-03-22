/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;


import java.sql.*;
import model.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Afrique#
 */
public class ReservationDao {
     private String dbUrl = "jdbc:postgresql://localhost:5432/hotel_reservation_management_system_db";
     private String username = "postgres";
    private String password = "buruni";
    public String createReservation(Reservation user){
        
        try{
            Connection conn = DriverManager.getConnection(dbUrl, username, password);
            PreparedStatement pst = conn.prepareStatement("Insert into reservation (id,first_name,last_name,phone,room_number,address) values(?, ?, ?, ?, ?, ?)");
            pst.setInt(1, user.getId());
            pst.setString(2, user.getFirstname());
            pst.setString(3, user.getLastname());
            pst.setString(4, user.getPhone());
            pst.setString(5, user.getRoomprice());
            pst.setString(6, user.getAddress());
            int rowsAffected = pst.executeUpdate();
                   conn.close();  
            if(rowsAffected >=0){
                  return "User Saved Successful";
            }else{
                return "User not saved";
            }
        }catch(SQLException e){
           e.printStackTrace();
           return "Server Error";
        }
    }
//update
public String updateReservation(Reservation user){
try{
            Connection conn = DriverManager.getConnection(dbUrl, username, password);
            
            PreparedStatement pst = conn.prepareStatement("update reservation set first_name=?,last_name=?,phone=?,room_number=?,address=? where id=?");
            pst.setInt(6, user.getId());
            pst.setString(1 , user.getFirstname());
            pst.setString(2 , user.getLastname());
            pst.setString(3 , user.getPhone());
            pst.setString(4 , user.getRoomprice());
            pst.setString(5 , user.getAddress());
            int rowsAffected = pst.executeUpdate();
            conn.close();
            if(rowsAffected >=0){
                return "Data Update";
            }else{
                return "Data not Updated";
            }
        }catch(SQLException e){
           e.printStackTrace();
           return "Server Error";
        }
    
}
//delete
public String deleteReservation(Reservation user){
try{
            Connection conn = DriverManager.getConnection(dbUrl, username, password);
  
            PreparedStatement pst = conn.prepareStatement("delete from reservation where id=?");
            pst.setInt(1, user.getId());
            int rowsAffected = pst.executeUpdate();
            conn.close();
            if(rowsAffected >=0){
                return "Data Deleteded";
            }else{
                return "Data not Deleted";
            }
        }catch(SQLException e){
           e.printStackTrace();
           return "Server Error";
        }
    
}
 
public List<Reservation> allReservations(){
      try{
           Connection conn = DriverManager.getConnection(dbUrl, username, password);
           String sql="select * from reservation";
           PreparedStatement pst = conn.prepareStatement(sql);
           ResultSet result = pst.executeQuery();
            List<Reservation> ReservationList = new ArrayList<>();
            while(result.next()){
                Reservation user = new Reservation();
                user.setId(result.getInt("id"));
                user.setFirstname(result.getString("first_name"));
                user.setLastname(result.getString("last_name"));
                user.setPhone(result.getString("phone"));
                user.setRoomprice(result.getString("room_number"));
                user.setAddress(result.getString("address"));
                ReservationList.add(user);
                
            }
             conn.close();
           return ReservationList;
       }catch(Exception ex){
           ex.printStackTrace();
           return null;
    
}
}
}
