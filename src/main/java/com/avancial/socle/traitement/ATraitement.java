/**
 * 
 */
package com.avancial.socle.traitement;

/**
 * @author bruno.legloahec
 *
 */

public abstract class ATraitement implements ITraitement {
   /**
    * Méthode contenant le code du traitement à implémenter
    * 
    * @throws Exception
    */
   protected abstract void executeTraitement() throws Exception;
}
