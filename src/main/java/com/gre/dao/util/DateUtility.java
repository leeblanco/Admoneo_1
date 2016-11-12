package com.gre.dao.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import org.jboss.logging.Logger;

public class DateUtility {

   private final static Logger logger = Logger.getLogger(DateUtility.class);

   /**
    * Java 8 ready String to Date converter using localdate class to convert
    * Strings into LocalDate format
    * 
    * @author Lee
    * @param inpDate
    *           this will return LocalDate
    */
   public LocalDate formatStringToLocalDate(String inpDate) {

      logger.info("Converting String date value: " + inpDate + " to date");

      DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
      LocalDate formattedDate = LocalDate.parse(inpDate, format);

      logger.info("Formatted Date: " + formattedDate);

      return formattedDate;
   }

   /**
    * Converts Date into String
    * 
    * @author Lee
    * @param inpDate
    * @return String
    */
   public String formatDateToString(Date inpDate) {

      DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      String dateString = df.format(inpDate);

      return dateString;
   }
}
