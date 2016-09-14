package com.avancial.app.traitement;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;
import com.avancial.socle.traitement.ATraitementLogDetail;

public class TraitementRegime<T> extends ATraitementLogDetail {
   @Inject
   @Socle_PUSocle
   EntityManager  em;

   private String selectSqlReq;

   public TraitementRegime() {

   }

   @Override
   protected void executeTraitement() {
      /* Récupère les Entities */
      List<T> result = this.selectEntity(this.selectSqlReq);
      this.em.close();
      /* Transcodage du régime */

   }

   private List<T> selectEntity(String sqlString) {
      Query query = this.em.createNativeQuery(sqlString);
      this.em.close();
      return query.getResultList();
   }

   private void transcode(T entity) {

   }

}
