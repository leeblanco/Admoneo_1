package com.gre.user.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jboss.logging.Logger;

import com.gre.dao.util.HibernateSession;
import com.gre.model.User;
import com.gre.user.dao.UserDao;

public class UserDaoImpl extends HibernateSession implements UserDao{

    final static Logger logger = Logger.getLogger(UserDaoImpl.class);
    
    /**
     * Adds new user in User table using the input param 
     * Returns true if the insert is successful
     * and false if the insert fails or status object is null.
     * 
     * @author Lee
     * @param User user
     * @return boolean
     * */
    @Override
    public boolean add(User user){
        
        boolean update = false;
        
        if (user != null){
        
            logger.info("Insert New Value in User Table ");
            Session session = getSession();
        
            try{
        
                Transaction trans = session.getTransaction();
                trans.begin();
                
                //Set newStatus object with the new input parameters from status input param
                logger.info("Insert Status Object with new values ");
                User newUser = new User();
                
                newUser.setFirstname(user.getFirstname());
                newUser.setLastname(user.getLastname());
                newUser.setEmail(user.getEmail());
                newUser.setCreatedDate(user.getCreatedDate());
                newUser.setUpdatedDate(user.getUpdatedDate());
                
                logger.info("Insert User data into Database");
                session.save(newUser);
                trans.commit();
                
                //set update to true successfully committed transaction
                update = true;
        
            }catch(Exception hEx){
        
                logger.error("Error encountered in inserting NewUser Object");
                //Status Update failed
                update = false;
                
                if (session.getTransaction().isActive()){
                    session.getTransaction().rollback();
                    logger.info("Transaction rollback, newUser object is not inserted");
                }
            
                logger.error(hEx);
            }        
        }else{
            
            logger.info("status object is null : "+ user);
            update = false;
        }
        return update;
    }

    /**
     * This method will retrieve all user records in table
     * 
     * @author Lee
     * @return List<User>
     * 
     * */
    @Override
    public List<User> searchAllUsers(){
        
        List<User> listOfUsers = new ArrayList<User>();
        
        Session session = getSession();
        try{
            
            Transaction tx = session.getTransaction();
            tx.begin();
            
            listOfUsers = session.createQuery("from User").list();
            
            tx.commit();
        
        }catch(HibernateException hEx){
            
            logger.error("Error encountered in retrieving user data "+ hEx);
            
        }finally{
            
            session.close();
        }
        
        return listOfUsers; 
    }
    
    
    /**
     * This method will search a specific user on User table using the id
     * 
     * @param int id
     * @return User
     * 
     * @author Lee
     * */
    public User searchUser(int id){
        
        User user = new User();
        Session session = getSession();
        
        try{
            
            Transaction tx = session.beginTransaction();
            
            String sql = "select firstname, lastname, email, createddate, updateddate from User where userid='"+id+"'";
            logger.info("Search by ID query : " +sql);
            
            SQLQuery query = session.createSQLQuery(sql);
            
            List<User> retrievedUser = query.list();
            
            for (User entry: retrievedUser){
                
                user.setUserId(entry.getUserId());
                user.setFirstname(entry.getFirstname());
                user.setLastname(entry.getLastname());
                user.setEmail(entry.getEmail());
                user.setCreatedDate(entry.getCreatedDate());
                user.setUpdatedDate(entry.getCreatedDate());
                
            }
            
            tx.commit();
            
            logger.debug("UserID: " + user.getUserId() +" First name: " +user.getFirstname() + 
                    " LastName: "+ user.getLastname()+ " Email: "+ user.getEmail()+
                    " UpdatedDate: "+ user.getCreatedDate()+ " CreatedDate: "+ user.getCreatedDate());
        
        }catch(Exception hEx){
            
            logger.error("There is a problem retrieving user record using id. "+ hEx);
            
        }finally{
            
            session.close();
            
        }
        
        return user;
    }

    /**
     * This method will search a specific user on User table using customers name
     * 
     * @param String name
     * @return User
     * 
     * @author Lee
     * */
    public User searchUser(String name){
        
        User user = new User();
        Session session = getSession();
        
        try{
            
            Transaction tx = session.beginTransaction();
            
            String sql = "select firstname, lastname, email, createddate, updateddate from User where like='%"+name+"%";
            logger.info("Search by name query");
            SQLQuery query = session.createSQLQuery(sql);
            
            List<User> retrievedUser = query.list();
            logger.debug("RetrievedUserList contains : "+ retrievedUser.size());
            
            for (User entry: retrievedUser){
                
                user.setUserId(entry.getUserId());
                user.setFirstname(entry.getFirstname());
                user.setLastname(entry.getLastname());
                user.setEmail(entry.getEmail());
                user.setCreatedDate(entry.getCreatedDate());
                user.setUpdatedDate(entry.getCreatedDate());
                
            }
            
            tx.commit();
            
            logger.debug("UserID: " + user.getUserId() +" First name: " +user.getFirstname() + 
                    " LastName: "+ user.getLastname()+ " Email: "+ user.getEmail()+
                    " UpdatedDate: "+ user.getCreatedDate()+ " CreatedDate: "+ user.getCreatedDate());
        
        }catch(Exception hEx){
            
            logger.error("There is a problem retrieving user record using id. "+ hEx);
            
        }finally{
            
            session.close();
            
        }
        
        return user;
    }
}
