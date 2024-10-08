
package com.daoImpl;

import com.dao.UserDao;
import com.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class UserDaoImpl implements UserDao {

    private Connection con;

    public UserDaoImpl(Connection con) {
        this.con = con;
    }

    @Override
    public boolean userRegistration(User arg0) {
        boolean f = false;
        try {
            String insert = "insert into user(name,email,phoneNumber,password) values(?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(insert);
            ps.setString(1, arg0.getName());
            ps.setString(2, arg0.getEmail());
            ps.setString(3, arg0.getPhoneNumber());
            ps.setString(4, arg0.getPassword());
            Integer roll = ps.executeUpdate();
            if (roll == 1) {
                f = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error : " + e.getMessage());
        }
        return f;
    }

    @Override
    public User loginUser(String arg0, String arg1) {
        User u = new User();
        try {
            String login = "select * from user where email = ? and password = ?";
            PreparedStatement ps = con.prepareStatement(login);
            ps.setString(1, arg0);
            ps.setString(2, arg1);
            ResultSet set = ps.executeQuery();
            while (set.next()) {
            	u.setUserId(set.getInt("userId"));
                u.setCity(set.getString("city"));
                u.setEmail(set.getString("email"));
                u.setLandmark(set.getString("landmark"));
                u.setName(set.getString("name"));
                u.setPassword(set.getString("password"));
                u.setPhoneNumber(set.getString("phoneNumber"));
                u.setState(set.getString("state"));
                u.setZipCode(set.getString("zipCode"));
                u.setAddress(set.getString("address"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error : " + e.getMessage());
        }
        return u;
    }

    @Override
    public boolean updateProfile(User arg0) {
        boolean f = false;
        try {
            String update = "update user set name=?, email=?, phoneNumber=?, password=? where userId = ?";
            PreparedStatement ps = con.prepareStatement(update);
            ps.setString(1, arg0.getName());
            ps.setString(2, arg0.getEmail());
            ps.setString(3, arg0.getPhoneNumber());
            ps.setString(4, arg0.getPassword());
            ps.setInt(5, arg0.getUserId());

            Integer roll = ps.executeUpdate();
            if (roll == 1) {
                f = true;
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            e.printStackTrace();
        }
        return f;
    }

    @Override
    public User getUserByUserId(Integer arg0) {
        User u = new User();
        try {
            String login = "select * from user where userId=?";
            PreparedStatement ps = con.prepareStatement(login);
            ps.setInt(1, arg0);
            ResultSet set = ps.executeQuery();
            while (set.next()) {
                u.setCity(set.getString("city"));
                u.setEmail(set.getString("email"));
                u.setLandmark(set.getString("landmark"));
                u.setName(set.getString("name"));
                u.setPassword(set.getString("password"));
                u.setPhoneNumber(set.getString("phoneNumber"));
                u.setState(set.getString("state"));
                u.setUserId(set.getInt("userId"));
                u.setZipCode(set.getString("zipCode"));
                u.setAddress(set.getString("address"));
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public boolean updateUserOtherDetail(User arg0) {
        boolean f = false;
        try {
            String update = "update user set address=?, landmark=?, city=?, state=?, zipCode=? where userId = ?";
            PreparedStatement ps = con.prepareStatement(update);
            ps.setString(1, arg0.getAddress());
            ps.setString(2, arg0.getLandmark());
            ps.setString(3, arg0.getCity());
            ps.setString(4, arg0.getState());
            ps.setString(5, arg0.getZipCode());
            ps.setInt(6, arg0.getUserId());

            Integer roll = ps.executeUpdate();
            if (roll == 1) {
                f = true;
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            e.printStackTrace();
        }
        return f;
    }

    @Override
    public boolean checkEmail(String arg0) {
        boolean f = true;
        try {
            String update = "select * from user where email=?";
            PreparedStatement ps = con.prepareStatement(update);
            ps.setString(1, arg0);
            ResultSet set = ps.executeQuery();
            while (set.next()) {
                f = false;
            }
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            e.printStackTrace();
        }
        return f;
    }

}
