/**
 * 
 */
package com.avancial.socle.traitement;

import java.io.Serializable;

import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

/**
 * @author bruno.legloahec
 *
 */

public abstract class ATraitement implements ITraitement, Serializable {
   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   /** entity manager. */
	@Inject
	@Socle_PUSocle
	private EntityManager     entityManager;

	/**
	 * méthode de pré-destruction.
	 */
	@PreDestroy
	public void preDestroy() {
		if(this.entityManager.isOpen()) {
			this.entityManager.close();
		}
	}
   
	/**
	 * retourne l'entity manager.
	 * @return l'entity manager.
	 */
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
   /**
    * Méthode contenant le code du traitement à implémenter
    * 
    * @throws Exception
    * @throws Throwable 
    */
   protected abstract void executeTraitement() throws Exception;
}
