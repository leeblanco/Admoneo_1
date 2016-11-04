package com.gre.user.service;

import java.util.List;

import com.gre.model.Users;

public interface UserService {

    public boolean add(Users user);

    public List<Users> searchAllUsers();

    public Users searchUser(int id);

    public Users searchUser(String name);
    
}
