package com.gre.status.service;

import java.util.List;

import com.gre.model.Status;

public interface StatusService {

    public boolean add(Status status);

    public boolean update(Status status);

    public List<String> retrieveStatusValues();


}
