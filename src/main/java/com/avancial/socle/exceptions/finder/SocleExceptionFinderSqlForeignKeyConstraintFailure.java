/**
 * 
 */
package com.avancial.socle.exceptions.finder;

import com.avancial.socle.exceptions.impl.SocleForeignKeyConstraintFaillException;

/**
 * @author bruno.legloahec
 *
 */
public class SocleExceptionFinderSqlForeignKeyConstraintFailure extends ASocleExceptionFinder {

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
   public SocleExceptionFinderSqlForeignKeyConstraintFailure() {
      super();
      this.socleException = new SocleForeignKeyConstraintFaillException();
      this.messageToBeFound = "a foreign key constraint fails";

   }

}
