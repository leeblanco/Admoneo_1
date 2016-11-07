package com.gre.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.jboss.logging.Logger;
import org.junit.Before;
import org.junit.Test;

import com.gre.dao.util.HibernateSession;
import com.gre.model.User;
import com.gre.user.service.UserService;
import com.gre.user.service.impl.UserServiceImpl;

public class TestUserServices {

    final static Logger logger = Logger.getLogger(TestUserServices.class);

    HibernateSession hibernate = new HibernateSession();
    
    TestUtil util = new TestUtil();

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

        User user = new User();

        user.setFirstname("Dimaz");
        user.setLastname(util.randomDataGen());
        user.setEmail(util.randomDataGen()+"@alang");
        user.setToken(util.randomDataGen());
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
    
    @Test
    public void testUserSearchAll() {

        List<User> listOfUsers = new ArrayList<User>();
        UserService testUserSrv = new UserServiceImpl();

        listOfUsers.addAll(testUserSrv.searchAllUsers());

        logger.info("List all users: ");
        for (User entries : listOfUsers) {

            util.userPrintUtil(entries);

        }
    }
 
    @Test
    public void testUserSearchById() {
        
        User user = new User();
        UserService testUserSrv = new UserServiceImpl();
        
        logger.info("Search User with ID = 1 ");
        user = testUserSrv.searchUserById(2);
        
        logger.info("Display user retrieved by ID ");
        util.userPrintUtil(user);
        
    }
    
    @Test
    public void testUserSearchByName() {
        
        User userById = new User();
        User userByName = new User();
        
        UserService testUserSrv = new UserServiceImpl();
        
        logger.info("Retrieve user id equal to 1 ");
        userById = testUserSrv.searchUserById(2);
        
        logger.info("Retrieve user by name equal to: "+ userById.getFirstname());
        userByName = testUserSrv.searchUserByName(userById.getFirstname());
        
        util.userPrintUtil(userByName);
        
    }
}
