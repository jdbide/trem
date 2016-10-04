package com.avancial.app.service.insertRefData;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.importMotrice.MotriceRefSatcodeEntity;

class GetUniqueRefSatcode extends AGetUniqueRefData<MotriceRefSatcodeEntity> {

   @Override
   public List<MotriceRefSatcodeEntity> getUniqueKeyEntity(MotriceRefSatcodeEntity refDataEntity, EntityManager em) throws Exception {
      Query q = this.getUniqueQuery(refDataEntity, em);
      MotriceRefSatcodeEntity refSatcodeEntity = (MotriceRefSatcodeEntity) refDataEntity;
      q.setParameter("labelSatCode", refSatcodeEntity.getLabelSatCode());
      return q.getResultList();
   }

}
