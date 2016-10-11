package com.avancial.app.service.insertRefData;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.importMotrice.MotriceRefCompositionClassEntity;

public class GetUniqueRefCompositionClass extends AGetUniqueRefData<MotriceRefCompositionClassEntity> {

   @Override
   public List<MotriceRefCompositionClassEntity> getUniqueKeyEntity(MotriceRefCompositionClassEntity refDataEntity, EntityManager em)
         throws Exception {
      MotriceRefCompositionClassEntity refCompositionClassEntity = (MotriceRefCompositionClassEntity) refDataEntity;
      Query q = this.getUniqueQuery(refCompositionClassEntity, em);
      q.setParameter("labelCompositionClass", refCompositionClassEntity.getLabelCompositionClass());
      q.setParameter("compagnie", refCompositionClassEntity.getCompagnie());
      return q.getResultList();
   }

}
