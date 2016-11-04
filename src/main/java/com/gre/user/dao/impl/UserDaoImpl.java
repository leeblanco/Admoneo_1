package com.gre.user.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jboss.logging.Logger;

import com.gre.dao.util.HibernateSession;
import com.gre.model.Users;
import com.gre.user.dao.UserDao;

public class UserDaoImpl extends HibernateSession implements UserDao {

    final static Logger logger = Logger.getLogger(UserDaoImpl.class);

    /**
     * Adds new user in User table using the input param Returns true if the
     * insert is successful and false if the insert fails or status object is
     * null.
     * 
     * @author Lee
     * @param user   contains user information to be inserted in to database
     *            
     * @return update returns true if insert is successful otherwise false
     */
    @Override
    public boolean add(Users user) {

        boolean update = false;

        if (user != null) {

            logger.info("Insert New Value in User Table ");
            Session session = getSession();

            try {

                Transaction trans = session.beginTransaction();

                // Set newStatus object with the new input parameters from
                // status input param
                logger.info("Insert Status Object with new values ");
                Users newUser = new Users();

                newUser.setFirstname(user.getFirstname());
                newUser.setLastname(user.getLastname());
                newUser.setEmail(user.getEmail());
                newUser.setToken(user.getToken());
                newUser.setCreatedDate(user.getCreatedDate());
                newUser.setUpdatedDate(user.getUpdatedDate());

                logger.info("Insert User data into Database");
                session.save(newUser);
                trans.commit();

                // set update to true successfully committed transaction
                update = true;

            } catch (Exception hEx) {

                logger.error("Error encountered in inserting NewUser Object");
                // Status Update failed
                update = false;

                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                    logger.info("Transaction rollback, newUser object is not inserted");
                }

                logger.error(hEx);
            }
        } else {

            logger.info("status object is null : " + user);
            update = false;
        }
        return update;
    }

    /**
     * This method will retrieve all user records in table
     * 
     * @author Lee
     * @return listOfUsers   returns all users retrieved from User table
     */
    @Override
    public List<Users> searchAllUsers() {

        List<Users> listOfUsers = new ArrayList<Users>();

        Session session = getSession();
        try {

            Transaction tx = session.getTransaction();
            tx.begin();

            listOfUsers = session.createQuery("from User").list();

            tx.commit();

        } catch (HibernateException hEx) {

            logger.error("Error encountered in retrieving user data " + hEx);

        } finally {

            session.close();
        }

        return listOfUsers;
    }

    /**
     * This method will search a specific user on User table using the id
     * 
     * @author Lee
     * @param id   user id to be retrieved
     * @return User returns user object
     */
    public Users searchUser(int id) {

        Users user = new Users();
        Session session = getSession();

        try {

            Transaction tx = session.beginTransaction();

            String sql = "select firstname, lastname, email, createddate, updateddate from User where userid= :id";
            logger.info("Search by ID query : " + sql);

            Query query = session.createSQLQuery(sql);
            query.setParameter("userid", id);

            // Should only be one
            int noOfResult = query.executeUpdate();
            logger.debug("Number of results returned: " + noOfResult);

            List<Users> retrievedUser = query.list();

            for (Users entry : retrievedUser) {

                user.setUserId(entry.getUserId());
                user.setFirstname(entry.getFirstname());
                user.setLastname(entry.getLastname());
                user.setEmail(entry.getEmail());
                user.setCreatedDate(entry.getCreatedDate());
                user.setUpdatedDate(entry.getCreatedDate());

            }

            tx.commit();

            logger.debug("UserID: " + user.getUserId() + " First name: " + user.getFirstname() + " LastName: "
                    + user.getLastname() + " Email: " + user.getEmail() + " UpdatedDate: " + user.getCreatedDate()
                    + " CreatedDate: " + user.getCreatedDate());

        } catch (Exception hEx) {

            logger.error("There is a problem retrieving user record using id. " + hEx);

        } finally {

            session.close();

        }

        return user;
    }

    /**
     * This method will search a specific user on User table using customers
     * name
     * 
     * @param name    user name to be searched
     * @return User   user object retrieved using input param name 
     * @author Lee
     */
    public Users searchUser(String name) {

        Users user = new Users();
        Session session = getSession();

        try {

            Transaction tx = session.beginTransaction();

            String sql = "select firstname, lastname, email, createddate, updateddate from User where name = :name";
            logger.info("Search by name query");

            Query query = session.createSQLQuery(sql);
            query.setParameter("name", name);

            // Should only be one
            int noOfResult = query.executeUpdate();
            logger.debug("Number of results returned: " + noOfResult);

            List<Users> retrievedUser = query.list();
            logger.debug("RetrievedUserList contains : " + retrievedUser.size());

            for (Users entry : retrievedUser) {

                user.setUserId(entry.getUserId());
                user.setFirstname(entry.getFirstname());
                user.setLastname(entry.getLastname());
                user.setEmail(entry.getEmail());
                user.setCreatedDate(entry.getCreatedDate());
                user.setUpdatedDate(entry.getCreatedDate());

            }

            tx.commit();

            logger.debug("UserID: " + user.getUserId() + " First name: " + user.getFirstname() + " LastName: "
                    + user.getLastname() + " Email: " + user.getEmail() + " UpdatedDate: " + user.getCreatedDate()
                    + " CreatedDate: " + user.getCreatedDate());

        } catch (Exception hEx) {

            logger.error("There is a problem retrieving user record using name. " + hEx);

        } finally {

            session.close();

        }

        return user;
    }

    /**
     * This method will search a token from User table based on 
     * input param id. If no id is found it will return NA
     * 
     * @author Lee
     * @param id   user id to be retrieved
     * @return token   returns the token associate to the id  
     */
    public String retrieveToken(int id) {

        Users user = new Users();
        String token = "NA";
        Session session = getSession();

        try {

            Transaction tx = session.beginTransaction();

            String sql = "select token from User where userid = :id";
            logger.info("Search by token query : " + sql);

            Query query = session.createSQLQuery(sql);
            query.setParameter("userid", id);

            // Should only be one
            int noOfResult = query.executeUpdate();
            logger.debug("Number of results returned: " + noOfResult);

            List<String> tokens = query.list();
            
            tx.commit();

            logger.debug("Token retrieved: " + token);

        } catch (Exception hEx) {

            logger.error("There is a problem retrieving user record using id. " + hEx);

        } finally {

            session.close();

        }

        return token;
    }
}
