package com.avancial.app.service.insertRefData;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.importMotrice.MotriceRefFareProfileCodeEntity;

class GetUniqueRefFareProfileCode extends AGetUniqueRefData<MotriceRefFareProfileCodeEntity> {

   @Override
   public List<MotriceRefFareProfileCodeEntity> getUniqueKeyEntity(MotriceRefFareProfileCodeEntity refDataEntity, EntityManager em) throws Exception {
      MotriceRefFareProfileCodeEntity refFareProfileCodeEntity = (MotriceRefFareProfileCodeEntity) refDataEntity;
      Query q = this.getUniqueQuery(refFareProfileCodeEntity, em);
      q.setParameter("labelFareProfileCode", refFareProfileCodeEntity.getLabelFareProfileCode());
      q.setParameter("compagnie", refFareProfileCodeEntity.getCompagnie());
      return q.getResultList();
   }

}
