package com.avancial.app.service.insertRefData;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.importMotrice.MotriceRefTospEntity;

class GetUniqueRefTosp extends AGetUniqueRefData<MotriceRefTospEntity> {

   @Override
   public List<MotriceRefTospEntity> getUniqueKeyEntity(MotriceRefTospEntity refDataEntity, EntityManager em) throws Exception {
      MotriceRefTospEntity refTospEntity = (MotriceRefTospEntity) refDataEntity;
      Query q = this.getUniqueQuery(refTospEntity, em);
      q.setParameter("codeTosp", refTospEntity.getCodeMotriceRefTosp());
      q.setParameter("compagnie", refTospEntity.getCompagnie());
      return q.getResultList();
   }

}
