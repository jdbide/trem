/**
 * 
 */
package com.avancial.socle.persistence;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;

import data.model.databean.Socle_PUExterne;

/**
 * @author bruno.legloahec
 *
 * Modifier par hamza.laterem
 * DateModification : 26/05/2016
 * 
 * Utilisation de EntityManagerFactoryProvider (@see EntityManagerFactoryProvider).
 */

public class EntityManagerProducerExterne {
   private static final String PERSISTENCE_UNIT_NAME = "PU_externe";
   
   @Produces 
   @Socle_PUExterne
   public EntityManager getEntityManager() {
      return EntityManagerFactoryProvider.getInstance().getEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
    }

}
