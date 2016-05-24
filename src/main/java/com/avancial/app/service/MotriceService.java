/**
 * 
 */
package com.avancial.app.service;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import data.model.databean.Socle_PUExterne;

/**
 * @author sebastien.benede Classe qui gère toutes les requêtes dans les tables DB2.
 */
public class MotriceService {
   @Inject
   @Socle_PUExterne
   private EntityManager ext;

   /**
    * Récupère tous les enregistrements de l'entité.
    * 
    * @param entityName
    *           nom de l'entité
    * @return liste des enregistrements de type entityName
    */
   public List<?> readAll(String entityName) {
      Query query = this.ext.createQuery(new StringBuilder("FROM ").append(entityName).toString());

      return query.getResultList();
   }

}
