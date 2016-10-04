package com.avancial.app.service.insertRefData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import com.avancial.app.data.databean.importMotrice.MotriceRefSatcodeEntity;

public class GetUniqueRefData {

   private Map<String, IGetUniqueRefData<?>> map = new HashMap<>();

   /**
    * Constructeur qui remplit la map
    */
   public GetUniqueRefData() {
      this.map.put(MotriceRefSatcodeEntity.class.getSimpleName(), new GetUniqueRefSatcode());
   }

   public List<Object> getUniqueKeyEntity(Object refDataEntity, EntityManager em) throws Exception {
      List<Object> res = new ArrayList<>();
      IGetUniqueRefData<Object> getUniqueRefData = (IGetUniqueRefData<Object>) this.map.get(refDataEntity.getClass().getSimpleName());
      if (getUniqueRefData != null) {
         res = getUniqueRefData.getUniqueKeyEntity(refDataEntity, em);
      } else {
         System.out.println("Pas de getUnique pour la classe " + refDataEntity.getClass().getSimpleName());
      }
      return res;
   }

}
