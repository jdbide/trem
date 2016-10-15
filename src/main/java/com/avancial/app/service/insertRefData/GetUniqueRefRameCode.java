package com.avancial.app.service.insertRefData;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.importMotrice.MotriceRefRameCodeEntity;

class GetUniqueRefRameCode extends AGetUniqueRefData<MotriceRefRameCodeEntity> {

   @Override
   public List<MotriceRefRameCodeEntity> getUniqueKeyEntity(MotriceRefRameCodeEntity refDataEntity, EntityManager em) throws Exception {
      Query q = this.getUniqueQuery(refDataEntity, em);
      MotriceRefRameCodeEntity refRameCodeEntity = (MotriceRefRameCodeEntity) refDataEntity;
      q.setParameter("labelRameCode", refRameCodeEntity.getLabelRameCode());
      q.setParameter("compagnie", refRameCodeEntity.getCompagnie());
      return q.getResultList();
   }

}
