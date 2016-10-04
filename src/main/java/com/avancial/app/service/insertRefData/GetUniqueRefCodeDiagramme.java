package com.avancial.app.service.insertRefData;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.importMotrice.MotriceRefCodeDiagrammeEntity;

public class GetUniqueRefCodeDiagramme extends AGetUniqueRefData<MotriceRefCodeDiagrammeEntity> {

   @Override
   public List<MotriceRefCodeDiagrammeEntity> getUniqueKeyEntity(MotriceRefCodeDiagrammeEntity refDataEntity, EntityManager em) throws Exception {
      Query q = this.getUniqueQuery(refDataEntity, em);
      MotriceRefCodeDiagrammeEntity refCodeDiagrammeEntity = (MotriceRefCodeDiagrammeEntity) refDataEntity;
      q.setParameter("labelCodeDiagramme", refCodeDiagrammeEntity.getLabelCodeDiagramme());
      q.setParameter("compagnie", refCodeDiagrammeEntity.getCompagnie());
      return q.getResultList();
   }

}
