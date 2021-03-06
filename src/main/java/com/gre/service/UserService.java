package com.gre.service;

import java.util.List;

import com.gre.model.User;

public interface UserService {

    public boolean add(User user);

    public List<User> searchAllUsers();

    public User searchUserById(int id);

    public User searchUserByName(String name);
    
}
