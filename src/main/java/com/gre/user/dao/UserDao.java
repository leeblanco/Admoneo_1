package com.gre.user.dao;

import java.util.List;

import com.gre.model.Users;

public interface UserDao {

    public boolean add(Users user);

    public List<Users> searchAllUsers();

    public Users searchUserById(int id);

    public Users searchUserByName(String name);
    
    public String retrieveToken(int id);

}
