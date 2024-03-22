/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.sql.*;
import model.*;

/**
 *
 * @author Afrique#
 */
public class UserDao {
    private String dbUrl = "jdbc:postgresql://localhost:5432/hotel_reservation_management_system_db";
     private String username = "postgres";
    private String password = "buruni";
    
    public String createUser(User user){
        
        try{
            Connection conn = DriverManager.getConnection(dbUrl, username, password);
            
            String sql = "Insert into users values (?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, user.getId());
            pst.setString(2, user.getFullName());
            pst.setString(3, user.getEmail());
            pst.setString(4, user.getPassword());
            int rowsAffected = pst.executeUpdate();
            if(rowsAffected >=1){
                return "User with "+user.getFullName() +"Saved";
            }else{
                return "User not saved";
            }
        }catch(SQLException e){
           e.printStackTrace();
           return "Server Error";
        }


    }
    public User loginUser(User user){
        try{
            Connection conn = DriverManager.getConnection(dbUrl, username, password);
            
            String sql = "select * from users where email = ? and password  = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            
            pst.setString(1, user.getEmail());
            pst.setString(2, user.getPassword());
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                User user1  = new User();
                user1.setEmail(rs.getString(3));
                user1.setId(rs.getInt(1));
                user1.setPassword(rs.getString(4));
                user1.setFullName(rs.getString(2));
                return user1;
            }else{
                return null;
            }
        }catch(SQLException e){
           e.printStackTrace();
           return null;
        }
        
    }
public User SignupUser(User user){
        try{
            Connection conn = DriverManager.getConnection(dbUrl, username, password);
            
            String sql = "select * from users where email = ? and password  = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            
            pst.setString(1, user.getEmail());
            pst.setString(2, user.getPassword());
            ResultSet rs = pst.executeQuery();
            if(rs.next()){
                User user1  = new User();
                user1.setEmail(rs.getString(3));
                user1.setId(rs.getInt(1));
                user1.setPassword(rs.getString(4));
                user1.setFullName(rs.getString(2));
                return user1;
            }else{
                return null;
            }
        }catch(SQLException e){
           e.printStackTrace();
           return null;
        }
        
    }

   
    
}
