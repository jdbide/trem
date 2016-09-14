package com.avancial.app.traitement;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;

import com.avancial.socle.traitement.ATraitementLogDetail;


public class TraitementRegime<T> extends ATraitementLogDetail {

   private String selectSqlReq;

   public TraitementRegime() {

   }

   @Override
   protected void executeTraitement() {
      /* Récupère les Entities */
      List<T> result = this.selectEntity(this.selectSqlReq);
      this.getEntityManager().close();
      /* Transcodage du régime */

   }

   private List<T> selectEntity(String sqlString) {
      Query query = this.getEntityManager().createNativeQuery(sqlString);
      this.getEntityManager().close();
      return query.getResultList();
   }

   private void transcode(T entity) {

   }

}
