/**
 * 
 */
package com.avancial.socle.exceptions;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import com.avancial.socle.exceptions.finder.ASocleExceptionFinder;
import com.avancial.socle.exceptions.finder.SocleExceptionFinderSqlColumnCannotBeNull;
import com.avancial.socle.exceptions.finder.SocleExceptionFinderSqlDuplicateId;
import com.avancial.socle.exceptions.finder.SocleExceptionFinderSqlForeignKeyConstraintFailure;
import com.avancial.socle.exceptions.impl.ASocleException;

/**
 * C'est le point d'entrée pour le client. Il est chargé de construire la chaine des Exception builder.
 * 
 * @author bruno.legloahec
 *
 */
@ApplicationScoped
public class SocleExceptionManager implements Serializable {
   /**
    * 
    */
   private static final long                                         serialVersionUID = 1L;

   protected static ASocleExceptionFinder                            finder           = null;

   private static SocleExceptionFinderSqlDuplicateId                 duplicateId      = new SocleExceptionFinderSqlDuplicateId();
   private static SocleExceptionFinderSqlColumnCannotBeNull          columnNotNull    = new SocleExceptionFinderSqlColumnCannotBeNull();
   private static SocleExceptionFinderSqlForeignKeyConstraintFailure foreignKey       = new SocleExceptionFinderSqlForeignKeyConstraintFailure();

   public SocleExceptionManager() {
   }

   @PostConstruct
   protected static void init() {

      // A laisser à la fin. Doit pointer sur le dernier finder
      columnNotNull.setNext(foreignKey);
      duplicateId.setNext(columnNotNull);
      SocleExceptionManager.finder = duplicateId;
   }

   /**
    * 
    * @param finder
    */
   public static void add(ASocleExceptionFinder finder) {
      SocleExceptionManager.finder = finder;
   }

   public static ASocleException getException(Exception e) {
      if (SocleExceptionManager.finder == null)
         SocleExceptionManager.init();
      return SocleExceptionManager.finder.getSocleException(e);
   }

}
