package com.avancial.app.service.insertRefData;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.importMotrice.MotriceRefServiceClassEntity;

class GetUniqueRefServiceClass extends AGetUniqueRefData<MotriceRefServiceClassEntity> {

   @Override
   public List<MotriceRefServiceClassEntity> getUniqueKeyEntity(MotriceRefServiceClassEntity refDataEntity, EntityManager em) throws Exception {
      MotriceRefServiceClassEntity refServiceClassEntity = (MotriceRefServiceClassEntity) refDataEntity;
      Query q = this.getUniqueQuery(refServiceClassEntity, em);
      q.setParameter("labelServiceClass", refServiceClassEntity.getLabelServiceClass());
      q.setParameter("compagnie", refServiceClassEntity.getCompagnie());
      return q.getResultList();
   }

}
