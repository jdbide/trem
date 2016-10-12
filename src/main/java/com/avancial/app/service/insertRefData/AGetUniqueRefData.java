package com.avancial.app.service.insertRefData;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.avancial.app.utilitaire.GetEntiteService;

/**
 * Implémentation d'une méthode pour récupérer la requête qui va vérifier la contrainte d'unicité dans la table. Cette requête doit être une
 * {@link NamedQuery} dans l'entité.
 * 
 * @author heloise.guillemaud
 *
 * @param <T>
 *           Classe de l'entité, qui doit posséder une {@link NamedQuery} nommée "getUnique"
 */
public abstract class AGetUniqueRefData<T> implements IGetUniqueRefData<T> {
   private static Logger logger = Logger.getLogger(AGetUniqueRefData.class);

   /**
    * Retourne la requête vérifiant la contrainte d'unicité d'une table
    * 
    * @param entity
    *           Entité dont on va vouloir tester l'existence dans la table
    * @param em
    * @return Objet {@link Query} initialisant la requête de test d'unicité. Les paramètres de la requête doivent encore être donnés.
    * @throws Exception
    */
   protected Query getUniqueQuery(T entity, EntityManager em) throws Exception {
      if (!entity.getClass().isAnnotationPresent(Entity.class)) {
         String erreurMessage = "La donnée de référence demandée à être sauvegardée, de classe " + entity.getClass().getSimpleName()
               + ", n'est pas une entité.";
         logger.error(erreurMessage);
         throw new Exception(erreurMessage);
      }

      try {
         /* Requête qui récupère les résultats qui ont la même contrainte d'unicité */
         return em.createNamedQuery(GetEntiteService.getNomFromEntiteTableMotriceRegime(entity.getClass().getSimpleName()) + ".getUnique");
      } catch (IllegalArgumentException e) {
         logger.error("L'entité de référence demandée à être sauvegardée, de classe " + entity.getClass().getSimpleName()
               + ", n'a pas de requête nommée 'getUnique'", e);
         throw e;
      }

   }
}
