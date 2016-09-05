/**
 * 
 */
package com.avancial.socle.params;

import java.util.ArrayList;

/**
 * Classe abstraite servant de point de départ aux classes voulant charger des paramètres à partir des bases de données.
 * 
 * @author bruno.legloahec
 *
 */
public abstract class AParamReaderDB extends AParamReader {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   /**
    * Constructeur
    */
   public AParamReaderDB() {
      this.colIParamBeans = new ArrayList<>();
   }

}
