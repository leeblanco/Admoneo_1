package com.gre.user.dao;

import java.util.List;

import com.gre.model.Users;

public interface UserDao {

    public boolean add(Users user);

    public List<Users> searchAllUsers();

    public Users searchUser(int id);

    public Users searchUser(String name);
    
    public String retrieveToken(int id);

}
