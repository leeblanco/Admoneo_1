package com.gre.dao.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.jboss.logging.Logger;

public class DateUtility {

   private final static Logger logger = Logger.getLogger(DateUtility.class);
   
   /**
    * Java 8 ready String to Date converter using localdate class to convert
    * Strings into LocalDate format
    * 
    * @author Lee
    * @param inpDate this will 
    * return LocalDate
    * */
   public LocalDate formatStringToLocalDate(String inpDate){
      
      logger.info("Converting String date value: "+ inpDate+ " to date");
      
      DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
      LocalDate formattedDate = LocalDate.parse(inpDate, format);
      
      logger.info("Formatted Date: "+ formattedDate);
      
      return formattedDate;
   }
}
