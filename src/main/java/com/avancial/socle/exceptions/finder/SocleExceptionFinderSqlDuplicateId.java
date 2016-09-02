/**
 * 
 */
package com.avancial.socle.exceptions.finder;

import com.avancial.socle.exceptions.impl.SocleDuplicateIdException;

/**
 * @author bruno.legloahec
 *
 */
public class SocleExceptionFinderSqlDuplicateId extends ASocleExceptionFinder {

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
   public SocleExceptionFinderSqlDuplicateId() {
      super();
      this.socleException = new SocleDuplicateIdException();
      this.messageToBeFound = "Duplicate entry";

   }

}
