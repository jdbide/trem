/**
 * 
 */
package com.avancial.app.webService.bean;

/**
 * @author sebastien.benede
 *
 */
public class ResponseBean {
   
   private boolean status;
   private String message;

   /**
    * @return the importOK
    */
   public boolean isStatus() {
      return this.status;
   }

   /**
    * @param importOK the importOK to set
    */
   public void setStatus(boolean status) {
      this.status = status;
   }

   /**
    * @return the message
    */
   public String getMessage() {
      return this.message;
   }

   /**
    * @param message the message to set
    */
   public void setMessage(String message) {
      this.message = message;
   }
}
