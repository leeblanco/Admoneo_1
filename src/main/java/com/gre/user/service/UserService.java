package com.gre.user.service;

import java.util.List;

import com.gre.model.Users;

public interface UserService {

    public boolean add(Users user);

    public List<Users> searchAllUsers();

    public Users searchUserById(int id);

    public Users searchUserByName(String name);
    
}
