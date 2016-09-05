/**
 * 
 */
package com.avancial.socle.exceptions.finder;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.avancial.socle.exceptions.impl.ASocleException;
import com.avancial.socle.exceptions.impl.SocleGenericException;

/**
 * @author bruno.legloahec
 *
 */
public abstract class ASocleExceptionFinder implements Serializable {
   /**
    * 
    */
   private static final long     serialVersionUID = 1L;
   protected ASocleException     socleException;
   private ASocleExceptionFinder next;
   private Exception             originalException;

   protected StringBuilder       buffer           = new StringBuilder();
   protected String              messageToBeFound;

   /**
    * Constructeur
    */
   public ASocleExceptionFinder() {
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.socle.exceptions.ISocleExceptionFinder#getSocleException (com.avancial.socle.exceptions.ISocleExceptionFinder, java.lang.Exception)
    */
   public ASocleException getSocleException(Exception e) {
      this.socleException.setOriginalException(e);
      // On tente de récupérer l'exception parente
      Throwable t = e.getCause();
      if (null != t && null != t.getCause())
         this.buffer.append(t.getCause().getMessage());
      else
         this.buffer.append(e.getMessage());

      if (this.buffer.lastIndexOf(this.messageToBeFound) > -1)
         return this.socleException;
      else {
         Pattern pattern = Pattern.compile(this.messageToBeFound);
         Matcher m = pattern.matcher(this.buffer);
         if (m.find())
            return this.socleException;
      }
      if (null != this.next)
         return this.next.getSocleException(e);
      else
         e.printStackTrace();
      SocleGenericException gen = new SocleGenericException();
      gen.setOriginalException(e);
      return gen;
   }

   public ASocleExceptionFinder getNext() {
      return this.next;
   };

   /**
    * @param next
    *           the next to set
    */
   public void setNext(ASocleExceptionFinder next) {
      this.next = next;
   }

   /**
    * @return the originalException
    */
   public Exception getOriginalException() {
      return originalException;
   }

   /**
    * @param originalException
    *           the originalException to set
    */
   public void setOriginalException(Exception originalException) {
      this.originalException = originalException;
   }

}
