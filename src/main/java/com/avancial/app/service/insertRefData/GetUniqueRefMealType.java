package com.avancial.app.service.insertRefData;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.importMotrice.MotriceRefMealTypeEntity;

class GetUniqueRefMealType extends AGetUniqueRefData<MotriceRefMealTypeEntity> {

   @Override
   public List<MotriceRefMealTypeEntity> getUniqueKeyEntity(MotriceRefMealTypeEntity refDataEntity, EntityManager em) throws Exception {
      MotriceRefMealTypeEntity refMealTypeEntity = (MotriceRefMealTypeEntity) refDataEntity;
      Query q = this.getUniqueQuery(refMealTypeEntity, em);
      q.setParameter("codeMealType", refMealTypeEntity.getCodeMealType());
      q.setParameter("compagnie", refMealTypeEntity.getCompagnie());
      return q.getResultList();
   }

}
