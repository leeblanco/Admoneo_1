package com.gre.reason.dao;

import java.util.List;

import com.gre.model.Reason;

public interface ReasonDao {

    public boolean add(Reason reason);
    public boolean update(Reason reason);
    public List<String> retrieveReasonValues();
    
}
