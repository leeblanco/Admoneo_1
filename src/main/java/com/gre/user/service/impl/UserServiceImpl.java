package com.gre.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;

import com.gre.model.User;
import com.gre.user.dao.UserDao;
import com.gre.user.dao.impl.UserDaoImpl;
import com.gre.user.service.UserService;

public class UserServiceImpl implements UserService {

    final static private Logger logger = Logger.getLogger(UserServiceImpl.class);

    private UserDao userDao;

    /**
     * This method will call User DAO to insert new records into User table.
     * Used for registering new users into the system. Checks if email from
     * user object is null or empty. If it is null or empty registerStatus
     * value will be false and returned to the caller. If the DB inserto into
     * User table is successful it will return true otherwise false.
     * 
     * @param User user
     * @return boolean
     * @author Lee
     */
    @Override
    public boolean add(User user) {

        boolean registerStatus = false;

        if (("".equalsIgnoreCase(user.getEmail())) || (null == user.getEmail())) {

            logger.info("Email Address is empty or null " + user.getEmail());

        } else {

            logger.info("Print User information ");
            printAllUserFields(user);
            
            logger.info("Inserting user into User table ");

            userDao = new UserDaoImpl();
            registerStatus = userDao.add(user);

            if (registerStatus == true) {

                logger.info("User added successfully ");

            } else {

                logger.info("User was not added on User table ");
            }
        }

        return registerStatus;
    }

    /**
     * This method will retrieve all users currently registered on User table
     *  
     * @return List<User>
     * @author Lee
     * */
    @Override
    public List<User> searchAllUsers() {
        
        List<User> userList = new ArrayList<User>();
        
        userDao = new UserDaoImpl();
        
        logger.info("Retrieving all users from User table ");
        userList.addAll(userDao.searchAllUsers());
        
        logger.info("List all users retrieved ");
        for (User entry: userList){
            
            printAllUserFields(entry);
            
        }
        
        return userList;
    }

    /**
     * This method will search User table using the id provided in the search results
     * 
     * @param int id
     * @return User
     * @author Lee
     * 
     * */
    @Override
    public User searchUser(int id) {
        
        User retrievedUser = new User();
        
        userDao = new UserDaoImpl();
        
        if (id < 0){
            
            logger.info("User id is lesser than the minimum id on record. "
                    + "Current value is : " + id +" SearchUser by id will terminate ");
            
        }else{
            
            retrievedUser = userDao.searchUser(id);
        
            printAllUserFields(retrievedUser);
            
        }
        
        return retrievedUser;
    }

    /**
     * This method will search Users using name param .
     * 
     *  @param name\
     *  @return User 
     *  @author User 
     * */
    @Override
    public User searchUser(String name) {
        User retrievedUser = new User();
        
        userDao = new UserDaoImpl();
        
        if ( ( "".equalsIgnoreCase(name) ) || null == name ){
            
            logger.info("User id is null or blank. "
                    + "Current value is : " + name +" SearchUser by name will terminate ");
            
        }else{
            
            retrievedUser = userDao.searchUser(name);
        
            printAllUserFields(retrievedUser);
            
        }
        
        return retrievedUser;
    }
    
    /**
     * This helper method is just used to print all user field values
     * 
     * @param User user
     * @return void
     * @author Lee
     * */
    public void printAllUserFields(User user){
        
        logger.info("Id : "+ user.getUserId());
        logger.info("First Name : " + user.getFirstname());
        logger.info("Last Name : " + user.getLastname());
        logger.info("Email : " + user.getEmail());
        logger.info("Created Date : " + user.getCreatedDate());
        logger.info("Updated Date : " + user.getUpdatedDate());
        
    }

    
}