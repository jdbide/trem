package com.avancial.app.service.insertRefData;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.avancial.app.utilitaire.GetEntiteService;

public abstract class AGetUniqueRefData<T> implements IGetUniqueRefData<T> {
   private static Logger logger = Logger.getLogger(AGetUniqueRefData.class);

   protected Query getUniqueQuery(T entity, EntityManager em) throws Exception {
      if (!entity.getClass().isAnnotationPresent(Entity.class)) {
         String erreurMessage = "La donnée de référence demandée à être sauvegardée, de classe " + entity.getClass().getSimpleName()
               + ", n'est pas une entité.";
         logger.error(erreurMessage);
         throw new Exception(erreurMessage);
      }

      Query q;
      try {
         /* Requête qui récupère les résultats qui ont la même contrainte d'unicité */
         q = em.createNamedQuery(GetEntiteService.getNomFromEntiteTableMotriceRegime(entity.getClass().getSimpleName()) + ".getUnique");
         return q;
      } catch (IllegalArgumentException e) {
         logger.error("L'entité de référence demandée à être sauvegardée, de classe " + entity.getClass().getSimpleName()
               + ", n'a pas de requête nommée 'getUnique'", e);
         throw e;
      }

   }
}
