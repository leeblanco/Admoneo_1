package com.gre;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

public class Status extends WebPage {

   public Status() {
      
      Link home = new Link("Home"){
         
         @Override
         public void onClick() {
            
            this.setResponsePage(new HomePage() );
            
         }
      };

      Link status = new Link("Status"){
         
         @Override
         public void onClick() {
            
            this.setResponsePage(new Status() );
            
         }
      };
      
      Link task = new Link("task") {
         
         @Override
         public void onClick() {
            
            this.setResponsePage(new ReminderPage() );
            
         }
      };
      
      this.add(home);
      this.add(status);
      this.add(task);

   }
   
 }
