package com.gre.user.dao;

import java.util.List;

import com.gre.model.User;

public interface UserDao {

    public boolean add(User user);

    public List<User> searchAllUsers();

    public User searchUserById(int id);

    public User searchUserByName(String name);
    
    public String retrieveToken(int id);

}
