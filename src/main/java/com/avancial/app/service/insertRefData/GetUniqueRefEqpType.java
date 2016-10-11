package com.avancial.app.service.insertRefData;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.importMotrice.MotriceRefEqpTypeEntity;

class GetUniqueRefEqpType extends AGetUniqueRefData<MotriceRefEqpTypeEntity> {

   @Override
   public List<MotriceRefEqpTypeEntity> getUniqueKeyEntity(MotriceRefEqpTypeEntity refDataEntity, EntityManager em) throws Exception {
      MotriceRefEqpTypeEntity refEqpTypeEntity = (MotriceRefEqpTypeEntity) refDataEntity;
      Query q = this.getUniqueQuery(refEqpTypeEntity, em);
      q.setParameter("labelEqpType", refEqpTypeEntity.getLabelEqpType());
      q.setParameter("compagnie", refEqpTypeEntity.getCompagnie());
      return q.getResultList();
   }

}
