package com.gre.dao;

import java.util.Date;

import org.hibernate.Session;
import org.jboss.logging.Logger;
import org.junit.Before;
import org.junit.Test;

import com.gre.dao.util.HibernateSession;
import com.gre.model.Users;
import com.gre.user.service.UserService;
import com.gre.user.service.impl.UserServiceImpl;

public class TestUserServices {

    final static Logger logger = Logger.getLogger(TestUserServices.class);

    HibernateSession hibernate = new HibernateSession();

    @Before
    public void testHibernateSession() {

        Session session;

        logger.info("Test for Hibernate session... Retrieve session object ");

        session = hibernate.getSession();

        if (session == null) {

            logger.info("Session is null ");

        } else {
            logger.info("Session created");
        }
    }

    @Test
    public void testUserAdd() {

        UserService testAdd = new UserServiceImpl();

        Users user = new Users();

        user.setFirstname("Dimaz");
        user.setLastname("Alang");
        user.setEmail("dimaz@alang");
        user.setToken("t0k3n");
        user.setUpdatedDate(new Date());
        user.setCreatedDate(new Date());

        logger.info("Add user object ");

        boolean status = testAdd.add(user);

        if (status == false) {
            
            logger.info("User object was not added successfully ");
            
        } else {
            
            logger.info("User was added successfully ");
            
        }
    }
}
