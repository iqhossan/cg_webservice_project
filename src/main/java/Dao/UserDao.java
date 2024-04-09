package Dao;

import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private Connection conn;

    public UserDao(Connection conn){
        super();
        this.conn = conn;
    }

    public boolean UserRegister(User user){
        boolean f = false;
        try{
           String sql = "insert into cg_db.user (name, username, password, email, mobile) values (?, ?, ?, ?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,user.getName());
            ps.setString(2,user.getUsername());
            ps.setString(3,user.getPassword());
            ps.setString(4,user.getEmail());
            ps.setString(5,user.getMobile());

            int i = ps.executeUpdate();
            if(i==1) return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return f;
    }


    public boolean updateUser(User user,int userId){
        boolean f = false;
        try{
            String sql = "Update cg_db.user set name = ? , username = ?, password=?, email=?, mobile=? Where userId=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,user.getName());
            ps.setString(2,user.getUsername());
            ps.setString(3,user.getPassword());
            ps.setString(4,user.getEmail());
            ps.setString(5,user.getMobile());
            ps.setInt(6,userId);
            int i = ps.executeUpdate();
            if(i==1) return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return f;
    }

    public User getUser(int userId){
        try{

            String sql = "select * from cg_db.user where userId=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,userId);
            ResultSet rs = ps.executeQuery();
            User user = new User();
            while(rs.next()){
                user.setUserId(rs.getInt("userId"));
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setMobile(rs.getString("mobile"));
            }
            return user;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUser(){
        try{
            List<User> listOfUser = new ArrayList<>();
            String sql = "select * from cg_db.user";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            User user = new User();
            while(rs.next()){
                user.setUserId(rs.getInt("userId"));
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setMobile(rs.getString("mobile"));
                listOfUser.add(user);
            }
            return listOfUser;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
