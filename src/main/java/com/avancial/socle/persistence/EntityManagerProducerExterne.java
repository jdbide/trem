/**
 * 
 */
package com.avancial.socle.persistence;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.avancial.socle.persistence.qualifiers.Socle_AutoCloseable;
import com.avancial.socle.persistence.qualifiers.Socle_PUExterne;

/**
 * @author bruno.legloahec
 *
 *         Modifier par hamza.laterem DateModification : 26/05/2016
 * 
 *         Utilisation de EntityManagerFactoryProvider (@see EntityManagerFactoryProvider).
 */

public class EntityManagerProducerExterne {
   private static final String PERSISTENCE_UNIT_NAME = "PU_externe";

   @Inject
   private AutoCloseableEntityManagerProducer autoClosableProducer;
   
   @Produces
   @Socle_PUExterne
   public EntityManager getEntityManager() {
      return EntityManagerFactoryProvider.getInstance().getEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
   }

   /**
    * méthode de création d'une nouvelle instance d'entityManager à fermeture multiple pour la PuExterne.
    * @return la nouvelle instance.
    */
   @Produces
   @Socle_AutoCloseable(Socle_PUExterne.class)
   public AutoCloseableEntityManager createNewAutoCloseableEntityManagerExterne() {
      return autoClosableProducer.createNewAutoCloseableEntityManager(Socle_PUExterne.class);
   }
}
