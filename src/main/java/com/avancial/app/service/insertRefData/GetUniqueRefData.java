package com.avancial.app.service.insertRefData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import com.avancial.app.data.databean.importMotrice.MotriceRefCodeDiagrammeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefCompositionClassEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefDistributionEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefEqpTypeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefFareProfileCodeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefGareEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefMealTypeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefODEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefRameCodeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefSatcodeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefServiceClassEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefServiceEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefTospEntity;

public class GetUniqueRefData {

   private Map<String, IGetUniqueRefData<?>> map = new HashMap<>();

   /**
    * Constructeur qui remplit la map
    */
   public GetUniqueRefData() {
      this.map.put(MotriceRefCodeDiagrammeEntity.class.getSimpleName(), new GetUniqueRefCodeDiagramme());
      this.map.put(MotriceRefCompositionClassEntity.class.getSimpleName(), new GetUniqueRefCompositionClass());
      this.map.put(MotriceRefDistributionEntity.class.getSimpleName(), new GetUniqueRefDistribution());
      this.map.put(MotriceRefEqpTypeEntity.class.getSimpleName(), new GetUniqueRefEqpType());
      this.map.put(MotriceRefFareProfileCodeEntity.class.getSimpleName(), new GetUniqueRefFareProfileCode());
      this.map.put(MotriceRefGareEntity.class.getSimpleName(), new GetUniqueRefGare());
      this.map.put(MotriceRefMealTypeEntity.class.getSimpleName(), new GetUniqueRefMealType());
      this.map.put(MotriceRefODEntity.class.getSimpleName(), new GetUniqueRefOd());
      this.map.put(MotriceRefRameCodeEntity.class.getSimpleName(), new GetUniqueRefRameCode());
      this.map.put(MotriceRefSatcodeEntity.class.getSimpleName(), new GetUniqueRefSatcode());
      this.map.put(MotriceRefServiceEntity.class.getSimpleName(), new GetUniqueRefService());
      this.map.put(MotriceRefServiceClassEntity.class.getSimpleName(), new GetUniqueRefServiceClass());
      this.map.put(MotriceRefTospEntity.class.getSimpleName(), new GetUniqueRefTosp());
   }

   public List<Object> getUniqueKeyEntity(Object refDataEntity, EntityManager em) throws Exception {
      List<Object> res = new ArrayList<>();
      IGetUniqueRefData<Object> getUniqueRefData = (IGetUniqueRefData<Object>) this.map.get(refDataEntity.getClass().getSimpleName());
      if (getUniqueRefData != null) {
         res = getUniqueRefData.getUniqueKeyEntity(refDataEntity, em);
      } else {
         System.out.println("Pas d'implémentation de IGetUniqueRefData pour la classe " + refDataEntity.getClass().getSimpleName());
      }
      return res;
   }

}
