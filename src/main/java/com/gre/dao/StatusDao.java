package com.gre.dao;

import java.util.List;

import com.gre.model.Status;

public interface StatusDao {

    public boolean add(Status status);

    public boolean update(Status status);

    public List<String> retrieveStatusValues();

}
