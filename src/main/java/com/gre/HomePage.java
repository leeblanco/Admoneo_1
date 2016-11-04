package com.gre;

import java.util.Date;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.gre.model.Users;
import com.gre.user.service.UserService;
import com.gre.user.service.impl.UserServiceImpl;

/**
 * Entry point class for apache wicket admoneo web application
 * 
 * @author Lee
 * */
public class HomePage extends WebPage {
    
    private static final long serialVersionUID = 1L;

    /**
     * Initialize homepage and add the register form component
     * 
     * @author Lee
     * */
    public HomePage(final PageParameters parameters) {
        
        super(parameters);

        add(new RegisterForm("registerForm"));
        
    }
    
    /**
     * Inner class contains fields that corresponds to the html elements
     * of HomePage.html. These fields are accessible via wicket:id tag
     * inside the html page. The main function of this class is to 
     * register new users in the admoneo web app.
     * 
     *  @author Lee
     * */
    private class RegisterForm extends Form {
        
        private TextField firstNameField;
        private TextField lastNameField;
        private TextField emailField;
        private PasswordTextField tokenField;
        private Label loginStatusLbl;
        
        /**
         * RegisterForm constructor initializes register fields available
         * in the html homepage.
         * 
         * @author Lee
         * */
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
         * After clicking on submit it will insert the data into the database
         * by calling user service layer add which will in turn call a User
         * DAO layer to insert the data. If the insert was successful message
         * will be displayed that user has registered successfully otherwise
         * it will show an error.
         * 
         * @author Lee
         * */
        public final void onSubmit() {
            
            Users user = new Users();
            UserService newUser = new UserServiceImpl();
            
            boolean status = false;
            
            String firstName = (String) firstNameField.getDefaultModelObject();
            String lastName = (String) lastNameField.getDefaultModelObject();
            String email = (String) emailField.getDefaultModelObject();
            String token = (String) tokenField.getDefaultModelObject();
            
            user.setFirstname(firstName);
            user.setLastname(lastName);
            user.setEmail(email);
            user.setToken(token);
            user.setUpdatedDate(new Date());
            user.setCreatedDate(new Date());
            
            //Add registered user in DB
            status = newUser.add(user);
            
            if(status == true) {
             
                loginStatusLbl.setDefaultModelObject("Congratulations! You are successfully registered");
            
            }else {
             
                loginStatusLbl.setDefaultModelObject("Something went wrong during registration process!");
                
            }
        }
    }
}
