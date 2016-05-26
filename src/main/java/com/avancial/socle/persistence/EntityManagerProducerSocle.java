/**
 * 
 */
package com.avancial.socle.persistence;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;

import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

/**
 * @author bruno.legloahec
 * 
 * Modifier par hamza.laterem
 * DateModification : 26/05/2016
 * 
 * Utilisation de EntityManagerFactoryProvider (@see EntityManagerFactoryProvider).
 *
 */
public class EntityManagerProducerSocle {
   private static final String PERSISTENCE_UNIT_NAME = "PU_socle";

   @Produces
   @Socle_PUSocle
   public EntityManager getEntityManager() {
      return EntityManagerFactoryProvider.getInstance().getEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
   }
}
