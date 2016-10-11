package com.avancial.app.service.insertRefData;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.importMotrice.MotriceRefServiceEntity;

class GetUniqueRefService extends AGetUniqueRefData<MotriceRefServiceEntity> {

   @Override
   public List<MotriceRefServiceEntity> getUniqueKeyEntity(MotriceRefServiceEntity refDataEntity, EntityManager em) throws Exception {
      MotriceRefServiceEntity refServiceEntity = (MotriceRefServiceEntity) refDataEntity;
      Query q = this.getUniqueQuery(refServiceEntity, em);
      q.setParameter("labelService", refServiceEntity.getLabelService());
      q.setParameter("compagnie", refServiceEntity.getCompagnie());
      return q.getResultList();
   }

}
