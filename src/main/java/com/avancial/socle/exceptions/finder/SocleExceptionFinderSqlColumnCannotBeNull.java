/**
 * 
 */
package com.avancial.socle.exceptions.finder;

import com.avancial.socle.exceptions.impl.SocleColumnCannotBeNullException;

/**
 * @author bruno.legloahec
 *
 */
public class SocleExceptionFinderSqlColumnCannotBeNull extends ASocleExceptionFinder {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   /**
    * Constructeur
    * 
    * @param next
    * @param e
    */
   public SocleExceptionFinderSqlColumnCannotBeNull() {
      super();
      this.socleException = new SocleColumnCannotBeNullException();
      this.messageToBeFound = "(Column).*(cannot be null)";

   }

}
