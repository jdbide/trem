/**
 * 
 */
package com.avancial.socle.exceptions.impl;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.avancial.socle.resources.MessageController;
import com.avancial.socle.resources.constants.SOCLE_constants;

/**
 * @author bruno.legloahec
 *
 */
public class SocleColumnCannotBeNullException extends ASocleException {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   /**
    * Constructeur
    */
   public SocleColumnCannotBeNullException() {
      super();
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.socle.exceptions.ASocleException#getClientMessage()
    */
   @Override
   public String getClientMessage() {
      Pattern p = Pattern.compile("'(.*?)'");
      Matcher m = p.matcher(this.getOriginalException().getMessage());
      return MessageController.getTraduction(SOCLE_constants.EXCEPTION_COLUMN_NOT_NULL.toString());
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.socle.exceptions.impl.ISocleException#getClientMessage(java.util.Locale)
    */
   @Override
   public String getClientMessage(Locale locale) {
      Pattern p = Pattern.compile("'(.*?)'");
      Matcher m = p.matcher(this.getOriginalException().getCause().getCause().getMessage());
      m.find();

      return MessageController.getTraductionWithParams(SOCLE_constants.EXCEPTION_COLUMN_NOT_NULL.toString(), m.group().toString(), locale);
   }

}
