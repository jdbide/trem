/**
 * 
 */
package com.avancial.socle.exceptions.impl;

import java.util.Locale;

import com.avancial.socle.resources.MessageController;
import com.avancial.socle.resources.constants.SOCLE_constants;

/**
 * @author bruno.legloahec
 *
 */
public class SocleGenericException extends ASocleException {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   /**
    * Constructeur
    * 
    * @param originalException
    */
   public SocleGenericException() {
      super();
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.socle.exceptions.ASocleException#getClientMessage()
    */
   @Override
   public String getClientMessage() {
      return MessageController.getTraduction(SOCLE_constants.EXCEPTION_UNKNOWN.toString());
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.socle.exceptions.impl.ISocleException#getClientMessage(java.util.Locale)
    */
   @Override
   public String getClientMessage(Locale locale) {
      return MessageController.getTraduction(SOCLE_constants.EXCEPTION_UNKNOWN.toString(), locale);
   }

}
