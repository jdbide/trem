package com.avancial.app.service.insertRefData;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.importMotrice.MotriceRefOd2gareEntity;

class GetUniqueRefOd2gare extends AGetUniqueRefData<MotriceRefOd2gareEntity> {

   @Override
   public List<MotriceRefOd2gareEntity> getUniqueKeyEntity(MotriceRefOd2gareEntity refDataEntity, EntityManager em) throws Exception {
      MotriceRefOd2gareEntity refOd2gare = (MotriceRefOd2gareEntity) refDataEntity;
      Query q = this.getUniqueQuery(refOd2gare, em);
      q.setParameter("refOd", refOd2gare.getMotriceRefODEntity());
      q.setParameter("refGare", refOd2gare.getMotriceRefGareEntity());
      return q.getResultList();
   }

}
