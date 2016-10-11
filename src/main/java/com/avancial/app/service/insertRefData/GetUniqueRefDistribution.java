package com.avancial.app.service.insertRefData;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.importMotrice.MotriceRefDistributionEntity;

class GetUniqueRefDistribution extends AGetUniqueRefData<MotriceRefDistributionEntity> {

   @Override
   public List<MotriceRefDistributionEntity> getUniqueKeyEntity(MotriceRefDistributionEntity refDataEntity, EntityManager em) throws Exception {
      MotriceRefDistributionEntity refDistributionEntity = (MotriceRefDistributionEntity) refDataEntity;
      Query q = this.getUniqueQuery(refDistributionEntity, em);
      q.setParameter("labelDistribution", refDistributionEntity.getLabelDistribution());
      q.setParameter("compagnie", refDistributionEntity.getCompagnie());
      return q.getResultList();
   }

}
