
package com.dao;

import com.model.User;


public interface UserDao {
    public boolean userRegistration(User u);
    
    public User loginUser(String email, String password);
    
    public boolean updateProfile(User u);
    
    public User getUserByUserId(Integer userId);
    
    public boolean updateUserOtherDetail(User u);
    
    public boolean checkEmail(String email);
}
