package com.gre.user.dao;

import java.util.List;

import com.gre.model.User;

public interface UserDao {

    public void add(User user);
    public List<User> searchAllUsers();
    public User searchUser(int id);
    public User searchUser(String name);
    public List<String> retrieveUsers();
    
}
