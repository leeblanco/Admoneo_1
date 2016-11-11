package com.gre;

import java.util.Date;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.jboss.logging.Logger;

import com.gre.model.User;
import com.gre.service.UserService;
import com.gre.service.impl.UserServiceImpl;

/**
 * Entry point class for apache wicket admoneo web application
 * 
 * @author Lee
 */
public class HomePage extends WebPage {

   private static final long serialVersionUID = 1L;
   private static final Logger logger = Logger.getLogger(HomePage.class);

   /*
    * This will initialize Homepage with standard nav links such as Home, Status
    * and Task.
    * 
    * @author Lee
    **/
   public HomePage() {

      add(new RegisterForm("registerForm"));

      add(new Link("Home") {

         @Override
         public void onClick() {

            setResponsePage(new HomePage());

         }
      });

      add(new Link("Task") {

         @Override
         public void onClick() {

            setResponsePage(new ReminderPage());

         }
      });

      add(new Link("Status") {

         @Override
         public void onClick() {

            setResponsePage(new Status());

         }
      });
   }

   /**
    * Initialize homepage and add the register form component
    * 
    * @author Lee
    */
   public HomePage(final PageParameters parameters) {

      super(parameters);

      add(new RegisterForm("registerForm"));

      add(new Link("Home") {

         @Override
         public void onClick() {

            setResponsePage(new HomePage());

         }
      });

      add(new Link("Task") {

         @Override
         public void onClick() {

            setResponsePage(new ReminderPage());

         }
      });

      add(new Link("Status") {

         @Override
         public void onClick() {

            setResponsePage(new Status());

         }
      });
   }

   /**
    * Inner class contains fields that corresponds to the html elements of
    * HomePage.html. These fields are accessible via wicket:id tag inside the
    * html page. The main function of this class is to register new users in the
    * admoneo web app.
    * 
    * @author Lee
    */
   private class RegisterForm extends Form {

      private TextField firstNameField;
      private TextField lastNameField;
      private TextField emailField;
      private PasswordTextField tokenField;
      private Label loginStatusLbl;

      /**
       * RegisterForm constructor initializes register fields available in the
       * html homepage.
       * 
       * @author Lee
       */
      public RegisterForm(String id) {

         super(id);

         firstNameField = new TextField("firstName", Model.of(""));
         lastNameField = new TextField("lastName", Model.of(""));
         emailField = new TextField("email", Model.of(""));
         tokenField = new PasswordTextField("token", Model.of(""));
         loginStatusLbl = new Label("loginStatus", Model.of(""));

         add(firstNameField);
         add(lastNameField);
         add(emailField);
         add(tokenField);
         add(loginStatusLbl);

      }

      /**
       * After clicking on submit it will insert the data into the database by
       * calling user service layer add which will in turn call a User DAO layer
       * to insert the data. If the insert was successful message will be
       * displayed that user has registered successfully otherwise it will show
       * an error.
       * 
       * @author Lee
       */
      public final void onSubmit() {

         User user = new User();
         UserService newUser = new UserServiceImpl();

         boolean status = false;

         String firstName = (String) firstNameField.getDefaultModelObject();
         String lastName = (String) lastNameField.getDefaultModelObject();
         String email = (String) emailField.getDefaultModelObject();
         String token = (String) tokenField.getDefaultModelObject();

         logger.info("Setting user object setter fields");

         user.setFirstname(firstName);
         user.setLastname(lastName);
         user.setEmail(email);
         user.setToken(token);
         user.setUpdatedDate(new Date());
         user.setCreatedDate(new Date());

         logger.info("First Name: " + firstName + " LastName: " + lastName + " Email: " + email + " Token: " + token
               + " Created Date: " + user.getCreatedDate() + " UpdateDate: " + user.getUpdatedDate());

         // Add registered user in DB
         status = newUser.add(user);

         if (status == true) {

            loginStatusLbl.setDefaultModelObject("Congratulations! You have successfully registered");

         } else {

            loginStatusLbl.setDefaultModelObject("Something went wrong during registration process!");

         }

         logger.info("Redirect to reminder page ");
         setResponsePage(new ReminderPage());

      }
   }

}
