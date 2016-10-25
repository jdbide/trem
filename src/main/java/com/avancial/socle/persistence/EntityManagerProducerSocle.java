/**
 * 
 */
package com.avancial.socle.persistence;

import java.io.Serializable;

import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.avancial.socle.persistence.qualifiers.Socle_AutoCloseable;
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
public class EntityManagerProducerSocle implements Serializable {
  /**
    * 
    */
   private static final long serialVersionUID = 1L;
   
   @Inject
   private AutoCloseableEntityManagerProducer autoClosableProducer;
   
   private static final String PERSISTENCE_UNIT_NAME = "PU_socle";
   
   @Produces
   @Socle_PUSocle
   public EntityManager getEntityManager() {
      return EntityManagerFactoryProvider.getInstance().getEntityManagerFactory(PERSISTENCE_UNIT_NAME).createEntityManager();
   }
   
   /**
    * méthode de création d'une nouvelle instance d'entityManager à fermeture multiple pour la PuSocle.
    * @return la nouvelle instance.
    */
   @Produces
   @Socle_AutoCloseable(Socle_PUSocle.class)
   public AutoCloseableEntityManager createNewAutoCloseableEntityManagerSocle() {
      return this.autoClosableProducer.createNewAutoCloseableEntityManager(Socle_PUSocle.class);
   }
}
