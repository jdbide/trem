/**
 * 
 */
package com.avancial.socle.authentification.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 * @author bruno.legloahec
 *
 */
@Named("loginController")
@SessionScoped
public class LoginController implements Serializable {
   /**
    * 
    */
   private static final long serialVersionUID = -9067413077594187136L;
   private String            errorMessage;

   /**
    * @return the errorMessage
    */
   public String getErrorMessage() {
      return errorMessage;
   }

   /**
    * @param errorMessage
    *           the errorMessage to set
    */
   public void setErrorMessage(String errorMessage) {
      this.errorMessage = errorMessage;
   }

}
