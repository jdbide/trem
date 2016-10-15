package com.avancial.app.service.insertRefData;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;

/**
 * Service pour remplir les tables de référence des données motrice.
 * 
 * @author heloise.guillemaud
 *
 */
public class InsertRefDataService {
   private static Logger logger = Logger.getLogger(InsertRefDataService.class);

   /**
    * Méthode statique pour récupérer, et si besoin sauvegarder, une donnée de référence motrice en base
    * 
    * @param entity
    *           Entité de référence, déjà présente en base ou non
    * @param em
    * @return L'entité de référence en base correspondant à la donnée en paramètre (c'est-à-dire ayant un id)
    * @throws Exception
    */
   public static Object persistRefData(Object entity, EntityManager em) throws Exception {
      List<Object> res = GetUniqueRefData.getUniqueKeyEntity(entity, em);
      if (res.isEmpty()) {
         /* La requête n'a pas retourné de résultat; la donnée de référence n'existe pas, il faut l'insérer en base */
         try {
            em.getTransaction().begin();
            em.persist(entity);
            em.flush();
            em.getTransaction().commit();
            logger.info("Entité de référence de classe " + entity.getClass().getSimpleName() + " sauvegardée en base.");
            return entity;
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
      return res.get(0);
      // else {
      // /* La requête a retourné un résultat; la donnée de référence existe déjà en base */
      // logger.info("L'entité de référence à sauvegarder, de classe " + entity.getClass().getSimpleName() + ", est déjà présente en base.");
      // }
   }
}
