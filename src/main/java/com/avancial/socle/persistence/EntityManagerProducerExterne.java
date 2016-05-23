/**
 * 
 */
package com.avancial.socle.persistence;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import data.model.databean.Socle_PUExterne;

/**
 * @author bruno.legloahec
 *
 */

public class EntityManagerProducerExterne {

   @Produces @Socle_PUExterne EntityManager getEntityManager() {
       return Persistence.createEntityManagerFactory("pu-externe").createEntityManager();
    }

}
