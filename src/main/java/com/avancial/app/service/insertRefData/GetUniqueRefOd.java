package com.avancial.app.service.insertRefData;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.importMotrice.MotriceRefODEntity;

class GetUniqueRefOd extends AGetUniqueRefData<MotriceRefODEntity> {

   @Override
   public List<MotriceRefODEntity> getUniqueKeyEntity(MotriceRefODEntity refDataEntity, EntityManager em) throws Exception {
      Query q = this.getUniqueQuery(refDataEntity, em);
      MotriceRefODEntity refCodeODEntity = (MotriceRefODEntity) refDataEntity;
      q.setParameter("codeGareOrigine", refCodeODEntity.getCodeGareOrigineMotriceRefOd());
      q.setParameter("codeGareDestination", refCodeODEntity.getCodeGareDestinationMotriceRefOd());
      q.setParameter("compagnie", refCodeODEntity.getCompagnie());
      return q.getResultList();
   }

}
