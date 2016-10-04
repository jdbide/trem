package com.avancial.app.service.insertRefData;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;

public class InsertRefDataService {
   private static Logger logger = Logger.getLogger(InsertRefDataService.class);

   public static void persistRefData(Object entity, EntityManager em) throws Exception {
      GetUniqueRefData getUniqueRefData = new GetUniqueRefData();
      List<Object> res = getUniqueRefData.getUniqueKeyEntity(entity, em);
      if (res.isEmpty()) {
         /* La requête n'a pas retourné de résultat; la donnée de référence n'existe pas, il faut l'insérer en base */
         try {
            em.getTransaction().begin();
            em.persist(entity);
            em.flush();
            em.getTransaction().commit();
            logger.info("Entité de référence de classe " + entity.getClass().getSimpleName() + " sauvegardée en base.");
         } catch (PersistenceException e) {
            em.getTransaction().rollback();
            if (e.getCause().getClass().equals(ConstraintViolationException.class)) {
               logger.warn("L'entité de référence à sauvegarder, de classe " + entity.getClass().getSimpleName() + ", est déjà présente en base.");
            } else {
               logger.warn("Erreur lors de la sauvegarde d'une entité de référence de classe " + entity.getClass().getSimpleName());
               throw e;
            }
         }
      } 
//         else {
//         /* La requête a retourné un résultat; la donnée de référence existe déjà en base */
//         logger.info("L'entité de référence à sauvegarder, de classe " + entity.getClass().getSimpleName() + ", est déjà présente en base.");
//      }
   }
}
