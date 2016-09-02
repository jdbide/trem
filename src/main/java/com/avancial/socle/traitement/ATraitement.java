/**
 * 
 */
package com.avancial.socle.traitement;

import java.io.Serializable;

/**
 * @author bruno.legloahec
 *
 */

public abstract class ATraitement implements ITraitement, Serializable {
   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   /**
    * Méthode contenant le code du traitement à implémenter
    * 
    * @throws Exception
    */
   protected abstract void executeTraitement() throws Exception;
}
