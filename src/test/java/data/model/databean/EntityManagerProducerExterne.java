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

public class EntityManagerProducerExterne {

   @Produces @Socle_PUExterne EntityManager getEntityManager() {
       return Persistence.createEntityManagerFactory("pu-externe").createEntityManager();
    }

}
