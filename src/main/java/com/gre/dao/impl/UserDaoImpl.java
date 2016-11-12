package com.gre.dao.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jboss.logging.Logger;

import com.gre.dao.UserDao;
import com.gre.dao.util.DateUtility;
import com.gre.dao.util.HibernateSession;
import com.gre.model.User;

public class UserDaoImpl extends HibernateSession implements UserDao {

   final static Logger logger = Logger.getLogger(UserDaoImpl.class);

   private DateUtility util = new DateUtility();
   /**
    * Adds new user in User table using the input param Returns true if the
    * insert is successful and false if the insert fails or status object is
    * null.
    * 
    * @author Lee
    * @param user
    *           contains user information to be inserted in to database
    * 
    * @return update returns true if insert is successful otherwise false
    */
   @Override
   public boolean add(User user) {

      boolean update = false;

      if (user != null) {

         logger.info("Insert New Value in User Table ");
         Session session = getSession();

         try {

            Transaction trans = session.beginTransaction();

            // Set newStatus object with the new input parameters from
            // status input param
            logger.info("Insert Status Object with new values ");
            User newUser = new User();

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
    * @return listOfUsers returns all users retrieved from User table
    */
   @Override
   public List<User> searchAllUsers() {

      List<User> listOfUsers = new ArrayList<User>();

      Session session = getSession();
      try {

         Transaction tx = session.beginTransaction();

         String sql = "select firstname, lastname, email, token, createdDate, updatedDate from User";
//         listOfUsers = session.createQuery(sql).list();
         // listOfUsers = session.createQuery("from Users").list();

         Query query = session.createQuery(sql);
         
         List<Object> retrievedUser = (List<Object>) query.list();
         logger.debug("RetrievedUserList contains : " + retrievedUser.size());
         
         Iterator iterHibObjList = retrievedUser.iterator();
         
         /*
          * Retrieved user sample value from query.list and iterate of the Object array
          * using Iterator. Example value of Object array below
          * 
          * [Test1, lastnametest1, test1@test.com, 2016-11-05, 2016-11-05]
          * [Test2, lastnametest2, test1@test.com, 2016-11-05, 2016-11-05]
          * 
          */
         while (iterHibObjList.hasNext()) {
            
            Object[] obj = (Object[]) iterHibObjList.next();
            
            User user = new User();
            
//            user.setUserId(Integer.valueOf(String.valueOf(obj[0])));
            user.setFirstname(String.valueOf(obj[0]));
            user.setLastname(String.valueOf(obj[1]));
            user.setEmail( String.valueOf(obj[2]));
            user.setToken( String.valueOf(obj[3]));
            
            Date createdDate = Date.valueOf(util.formatStringToLocalDate(String.valueOf(obj[4])));
            Date updatedDate = Date.valueOf(util.formatStringToLocalDate(String.valueOf(obj[5])));
            user.setCreatedDate( createdDate );
            user.setUpdatedDate( updatedDate );
            
            listOfUsers.add(user);
         }
                 
         tx.commit();

      } catch (HibernateException hEx) {

         logger.error("Error encountered in retrieving user data " + hEx);

      }

      return listOfUsers;
   }

   /**
    * This method will search a specific user on User table using the id
    * 
    * @author Lee
    * @param userId
    *           user id to be retrieved
    * @return User returns user object
    */
   public User searchUserById(int userId) {

      User user = new User();
      Session session = getSession();

      try {

         Transaction tx = session.beginTransaction();

         user = (User) session.get(User.class, userId);

         tx.commit();

         logger.debug("UserID: " + user.getUserId() + " First name: " + user.getFirstname() + " LastName: "
               + user.getLastname() + " Email: " + user.getEmail() + " UpdatedDate: " + user.getCreatedDate()
               + " CreatedDate: " + user.getCreatedDate());

      } catch (Exception hEx) {

         logger.error("There is a problem retrieving user record using id. " + hEx);

      } 

      return user;
   }

   /**
    * This method will search a specific user on User table using customers name
    * 
    * @param firstName
    *           user name to be searched
    * @return User user object retrieved using input param name
    * @author Lee
    */
   public User searchUserByName(String firstName) {

      User user = new User();
      Session session = getSession();

      try {

         Transaction tx = session.beginTransaction();

         String sql = "select user.firstname, user.lastname, user.email, user.createdDate "+
            ", user.updatedDate from User user where user.firstname = :firstname";
         logger.info("Search by name query");

         Query query = session.createQuery(sql);
         query.setParameter("firstname", firstName);

         List<Object> retrievedUser = (List<Object>) query.list();
         logger.debug("RetrievedUserList contains : " + retrievedUser.size());
         Iterator iterHibObjList = retrievedUser.iterator();
         
         /*
          * Retrieved user sample value from query.list and iterate of the Object array
          * using Iterator. Example value of Object array below
          * 
          * [Test1, lastnametest1, test1@test.com, 2016-11-05, 2016-11-05]
          * [Test2, lastnametest2, test1@test.com, 2016-11-05, 2016-11-05]
          * 
          */
         while (iterHibObjList.hasNext()) {
            
            Object[] obj = (Object[]) iterHibObjList.next();
            
            user.setFirstname(String.valueOf(obj[0]));
            user.setLastname(String.valueOf(obj[1]));
            user.setEmail( String.valueOf(obj[2]) );
            
            Date createdDate = Date.valueOf(util.formatStringToLocalDate(String.valueOf(obj[3])));
            Date updatedDate = Date.valueOf(util.formatStringToLocalDate(String.valueOf(obj[4])));
            user.setCreatedDate( createdDate );
            user.setUpdatedDate( updatedDate );
            
         }
         
         tx.commit();

         logger.debug("First name: " + user.getFirstname() + " LastName: "+ user.getLastname() +
               " Email: " + user.getEmail() + " UpdatedDate: " + user.getCreatedDate()
               + " CreatedDate: " + user.getCreatedDate());

      } catch (Exception hEx) {

         logger.error("There is a problem retrieving user record using firstname. " + hEx);

      } 

      return user;
   }

   /**
    * This method will search a token from User table based on input param id.
    * If no id is found it will return NA
    * 
    * @author Lee
    * @param id
    *           user id to be retrieved
    * @return token returns the token associate to the id
    */
   public String retrieveToken(int id) {

      User user = new User();
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

      }

      return token;
   }
}
