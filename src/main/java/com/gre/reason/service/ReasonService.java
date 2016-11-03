package com.gre.reason.service;

import java.util.List;

import com.gre.model.Reason;

public interface ReasonService {

    public boolean add(Reason reason);

    public boolean update(Reason reason);

    public List<String> retrieveReasonValues();


}
