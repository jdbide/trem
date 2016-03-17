package com.avancial.app.model.managedbean;

import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class IndexBean {

   private String locale = "en";
   private String message = "";
   private String email = "";

   public String getLocale() {
      return locale;
   }

   public void setLocale(String locale) {
      this.locale = locale;
   }

   public String getMessage() {
      return message;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String changeLocale(String locale){
      // Change the locale attribute
      this.locale = locale;
      // Change the locale of the view
      FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(this.locale));
      return "";
   }

   public String send(){
      if(this.message.equals("")){
         // Bring the error message using the Faces Context
         String errorMessage = FacesContext.getCurrentInstance().getApplication().
               getResourceBundle(FacesContext.getCurrentInstance(),"msg").
               getString("message_missing");
         // Add View Faces Message
         FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
         errorMessage, errorMessage);
         // Add the message into context for a specific component
         FacesContext.getCurrentInstance().addMessage("form:message", message);
      }

      if(this.email.equals("")){
         // Bring the error message using the Faces Context
         String errorMessage = FacesContext.getCurrentInstance().getApplication().
               getResourceBundle(FacesContext.getCurrentInstance(),"msg").
                                          getString("email_missing");
         // Add View Faces Message
         // Cause the application doesn't functional if you've got missing the email
         // The severity case is Error or fatal error
         FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,errorMessage,
                                         errorMessage);
         // Add the message into context for a specific component
         FacesContext.getCurrentInstance().addMessage("form:email", message);
         // Return empty token for navigation handler
         return "";
      }

      // Return confirmation message that the message has been sent

      // Bring the information message using the Faces Context
      String confirmMessage = FacesContext.getCurrentInstance().getApplication().
            getResourceBundle(FacesContext.getCurrentInstance(),"msg").
                                  getString("email_sent");
      // Add View Faces Message
      FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,confirmMessage, confirmMessage);
      // The component id is null, so this message is considered as a view message
      FacesContext.getCurrentInstance().addMessage(null, message);
      // Return empty token for navigation handler
      return "";
   }
}