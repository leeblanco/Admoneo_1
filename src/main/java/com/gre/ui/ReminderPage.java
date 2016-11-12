package com.gre.ui;

import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.PropertyModel;
import org.jboss.logging.Logger;

/**
 * The reminder page class holds the logic for creating new or editing tasks
 * which is accessible via two buttons. Each button will open a modal window
 * which will take user input for creating new task. The other for editing
 * existing task.
 * 
 * @author Lee
 * 
 */
public class ReminderPage extends WebPage {

   private static final long serialVersionUID = 1L;
   private static final Logger logger = Logger.getLogger(ReminderPage.class);
   
   String resultVal;
   
   /**
    * ReminderPage constructor initializes the nav links Home, Reminder and 
    * Task page using inner classes.
    * 
    * @author Lee
    * */
   public ReminderPage() {

      Link home = new Link("Home") {

         @Override
         public void onClick() {

            this.setResponsePage(new HomePage());

         }
      };

      Link status = new Link("Status") {

         @Override
         public void onClick() {

            this.setResponsePage(new Status());

         }
      };

      Link task = new Link("Task") {

         @Override
         public void onClick() {

            this.setResponsePage(new ReminderPage());

         }
      };

      this.add(home);
      this.add(status);
      this.add(task);
      
      final ModalWindow createNewTaskModal;
      
      final Label result;
      
//      add(result = new Label("result", new PropertyModel(this,"result")));
//      result.setOutputMarkupId(true);
      
      add(createNewTaskModal = new ModalWindow("newTask"));
      
      createNewTaskModal.setCookieName("newTaskCookie");
      
      createNewTaskModal.setPageCreator(new ModalWindow.PageCreator() {
         
         @Override
         public Page createPage() {
            
            return new NewTaskPage(ReminderPage.this.getPageReference(), createNewTaskModal);
            
         }
      });
      
      createNewTaskModal.setWindowClosedCallback(new ModalWindow.WindowClosedCallback() {
         
         @Override
         public void onClose(AjaxRequestTarget target) {
            
//            target.add(result);
            
         }
      });
      
      createNewTaskModal.setCloseButtonCallback(new ModalWindow.CloseButtonCallback() {
         
         @Override
         public boolean onCloseButtonClicked(AjaxRequestTarget target) {
            
            setResult("Window is closed");
            return false;
         }
      });
      
      add(new AjaxLink<Void>("CreateNew") {
         
         @Override
         public void onClick(AjaxRequestTarget target){
            
            createNewTaskModal.show(target);
         }
      });
      
   }
   
   public void setResult(String result){
      
      this.resultVal=result;
      
   }

}
