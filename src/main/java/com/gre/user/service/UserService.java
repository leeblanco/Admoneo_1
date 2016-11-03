package com.gre.user.service;

import java.util.List;

import com.gre.model.User;

public interface UserService {

    public boolean add(User user);

    public List<User> searchAllUsers();

    public User searchUser(int id);

    public User searchUser(String name);
}
