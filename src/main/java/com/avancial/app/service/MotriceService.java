/**
 * 
 */
package com.avancial.app.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.socle.persistence.qualifiers.Socle_PUExterne;

/**
 * @author sebastien.benede Classe qui gère toutes les requêtes dans les tables DB2.
 */
@RequestScoped
public class MotriceService implements Serializable {
   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   @Inject
   @Socle_PUExterne
   private EntityManager     ext;

   /**
    * Récupère tous les enregistrements de l'entité.
    * 
    * @param entity
    * @return
    */
   public List<?> readAll(String entity) {
      Query query = this.ext.createQuery(new StringBuilder("FROM ").append(entity).toString());

      return query.getResultList();
   }

}
