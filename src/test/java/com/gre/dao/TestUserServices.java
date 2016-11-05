package com.gre.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
        user.setLastname(userRandomValGen());
        user.setEmail(userRandomValGen()+"@alang");
        user.setToken(userRandomValGen());
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

        List<Users> listOfUsers = new ArrayList<Users>();
        UserService testUserSrv = new UserServiceImpl();

        listOfUsers.addAll(testUserSrv.searchAllUsers());

        logger.info("List all users: ");
        for (Users entries : listOfUsers) {

            userPrintUtil(entries);

        }
    }
 
    @Test
    public void testUserSearchById() {
        
        Users user = new Users();
        UserService testUserSrv = new UserServiceImpl();
        
        logger.info("Search User with ID = 1 ");
        user = testUserSrv.searchUserById(2);
        
        logger.info("Display user retrieved by ID ");
        userPrintUtil(user);
        
    }
    
    @Test
    public void testUserSearchByName() {
        
        Users userById = new Users();
        Users userByName = new Users();
        
        UserService testUserSrv = new UserServiceImpl();
        
        logger.info("Retrieve user id equal to 1 ");
        userById = testUserSrv.searchUserById(2);
        
        logger.info("Retrieve user by name equal to: "+ userById.getFirstname());
        userByName = testUserSrv.searchUserByName(userById.getFirstname());
        
        userPrintUtil(userByName);
        
    }
    
    
    /**
     * Utility to print all User field values
     * 
     * @author Lee
     * @param user
     *            object
     */
    public void userPrintUtil(Users user) {

        logger.info("ID: " + user.getUserId());
        logger.info("FirstName: " + user.getFirstname());
        logger.info("LastName: " + user.getLastname());
        logger.info("Email: " + user.getEmail());
        logger.info("Token: " + user.getToken());
        logger.info("CreatedDate: " + user.getCreatedDate());
        logger.info("CreatedDate: " + user.getUpdatedDate());
        
    }

    /**
     * Utility to print random values used in testing
     * 
     * @author Lee
     * @return randomValue returns a random set of characters
     */
    public String userRandomValGen() {

        String characterSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        int stringLength = 8;

        StringBuilder randomString = new StringBuilder();

        Random randomize = new Random();

        for (int x = 0; x < stringLength; x++) {
            randomString.append(characterSet.charAt(randomize.nextInt(characterSet.length())));
        }

        logger.info("Random String Generated: " + randomString.toString());

        return randomString.toString();
        
    }
}
