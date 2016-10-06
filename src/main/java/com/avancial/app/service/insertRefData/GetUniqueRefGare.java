package com.avancial.app.service.insertRefData;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.importMotrice.MotriceRefGareEntity;

class GetUniqueRefGare extends AGetUniqueRefData<MotriceRefGareEntity> {

   @Override
   public List<MotriceRefGareEntity> getUniqueKeyEntity(MotriceRefGareEntity refDataEntity, EntityManager em) throws Exception {
      MotriceRefGareEntity refGareEntity = (MotriceRefGareEntity) refDataEntity;
      Query q = this.getUniqueQuery(refGareEntity, em);
      q.setParameter("codeGare", refGareEntity.getCodeGareMotriceRefGare());
      q.setParameter("compagnie", refGareEntity.getCompagnie());
      return q.getResultList();
   }

}
