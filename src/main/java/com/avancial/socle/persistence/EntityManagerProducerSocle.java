/**
 * 
 */
package com.avancial.socle.persistence;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

/**
 * @author bruno.legloahec
 *
 */
public class EntityManagerProducerSocle {

   @Produces
   @Socle_PUSocle
   EntityManager getEntityManager() {
      return Persistence.createEntityManagerFactory("PU_app").createEntityManager();
   }

}
