package com.gre;

import java.util.Locale;

import org.apache.wicket.PageReference;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.datetime.markup.html.form.DateTextField;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.jboss.logging.Logger;

import com.gre.model.Todo;
import com.gre.todo.service.TodoService;
import com.gre.todo.service.impl.TodoServiceImpl;

public class NewTaskPage extends WebPage {

   private static final Logger logger = Logger.getLogger(NewTaskPage.class);
   
   public NewTaskPage(final PageReference mainWindowPageRef,final ModalWindow modalWindow) {
      
     add( new RegisterForm("newTaskForm")); 
//     add( new PriorityDropDown("priority"));
     
   }
   
   private class RegisterForm extends Form {

      private TextField<String> projectNameField;
      private TextField<String> projectOwnerField;
      private TextField<String> statusField;
      private TextField<String> reasonField;
      private TextArea<String> descriptionField;
      
      private Label submitStatus;
      private Label textFieldLbl;
      
      DateTextField completionDate;
      
      private final Locale LOCALE_EN = new Locale("en");
      private Locale selectedLocale = LOCALE_EN;
      
      PriorityDropDown priority;// = new PriorityDropDown("priority");
      
      /**
       * RegisterForm constructor initializes register fields available in the
       * reminder page create new task.
       * 
       * @author Lee
       */
      public RegisterForm(String id) {

         super(id);
         
         projectNameField = new TextField<String>("projectName", Model.of(""));
         projectOwnerField = new TextField<String>("projectOwner", Model.of(""));
         statusField = new TextField<String>("taskStatus", Model.of(""));
         reasonField = new TextField<String>("taskReason", Model.of(""));
         descriptionField = new TextArea<String>("taskDescription", Model.of(""));
         submitStatus = new Label("submitStatus", Model.of(""));
         textFieldLbl = new Label("taskDescription", Model.of(""));
         
         priority = new PriorityDropDown("priority");
         
         add(projectNameField);
         add(projectOwnerField);
         add(statusField);
         add(reasonField);
         add(submitStatus);
         add(textFieldLbl);
         add(priority);

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

         Todo task = new Todo();
         TodoService taskMgr = new TodoServiceImpl();

         boolean txStatus = false;

         String projName = (String) projectNameField.getDefaultModelObject();
         String projOwner = (String) projectOwnerField.getDefaultModelObject();
         String reason = (String) reasonField.getDefaultModelObject();
         String status = (String) statusField.getDefaultModelObject();
         String description = descriptionField.toString();

         logger.info("Setting user object setter fields");
         
         task.setProjectName(projName);
         task.setProjectOwner(projOwner);
         task.setReasonId(Integer.parseInt(reason));
         task.setStatusId(Integer.parseInt(status));
         task.setDescription(description);

         logger.info("Project Name: "+ projName+ " Project Owner: "+ projOwner+ " Reason: "+ reason +
               " Status: "+ status + " Description: "+ description +
               " Completion Date: "+ task.getCompletionDate() );
         
         // Add registered user in DB
         txStatus = taskMgr.addTodo(task);

         if (txStatus == true) {

            submitStatus.setDefaultModelObject("Task was added");

         } else {

            submitStatus.setDefaultModelObject("Task was not added");

         }
         
         logger.info("Redirect to Status page ");
         setResponsePage(new Status());
         
      }
   }
}
