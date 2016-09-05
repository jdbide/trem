/**
 * 
 */
package com.avancial.socle.exceptions.finder;

import com.avancial.socle.exceptions.impl.ISocleException;

/**
 * Cette famille d'objet aura la responsabilité de créer des exceptions personnalisées à partir des exceptions classiques de Java.
 * 
 * @author bruno.legloahec
 *
 */
public interface ISocleExceptionFinder {
   public ISocleException getSocleException();

   public ISocleExceptionFinder getNext();
}
