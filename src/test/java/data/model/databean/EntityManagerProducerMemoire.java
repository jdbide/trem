/**
 * 
 */
package data.model.databean;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * @author bruno.legloahec
 *
 */
public class EntityManagerProducerMemoire {

   @Produces
   @Socle_PUMemoire
   EntityManager getEntityManager() {
      return Persistence.createEntityManagerFactory("pu-memoire").createEntityManager();
   }

}
