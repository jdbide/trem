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
import com.avancial.app.data.databean.importMotrice.MotriceRefOd2gareEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefRameCodeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefSatcodeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefServiceClassEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefServiceEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefTospEntity;

public class GetUniqueRefData {

   private static final Map<String, IGetUniqueRefData<?>> map = new HashMap<>();

   static {
      map.put(MotriceRefCodeDiagrammeEntity.class.getSimpleName(), new GetUniqueRefCodeDiagramme());
      map.put(MotriceRefCompositionClassEntity.class.getSimpleName(), new GetUniqueRefCompositionClass());
      map.put(MotriceRefDistributionEntity.class.getSimpleName(), new GetUniqueRefDistribution());
      map.put(MotriceRefEqpTypeEntity.class.getSimpleName(), new GetUniqueRefEqpType());
      map.put(MotriceRefFareProfileCodeEntity.class.getSimpleName(), new GetUniqueRefFareProfileCode());
      map.put(MotriceRefGareEntity.class.getSimpleName(), new GetUniqueRefGare());
      map.put(MotriceRefMealTypeEntity.class.getSimpleName(), new GetUniqueRefMealType());
      map.put(MotriceRefODEntity.class.getSimpleName(), new GetUniqueRefOd());
      map.put(MotriceRefRameCodeEntity.class.getSimpleName(), new GetUniqueRefRameCode());
      map.put(MotriceRefSatcodeEntity.class.getSimpleName(), new GetUniqueRefSatcode());
      map.put(MotriceRefServiceEntity.class.getSimpleName(), new GetUniqueRefService());
      map.put(MotriceRefServiceClassEntity.class.getSimpleName(), new GetUniqueRefServiceClass());
      map.put(MotriceRefTospEntity.class.getSimpleName(), new GetUniqueRefTosp());
      map.put(MotriceRefOd2gareEntity.class.getSimpleName(), new GetUniqueRefOd2gare());
   }

   /**
    * Retourne la liste de données correspondant à l'entité de référence en paramètre
    * 
    * @param refDataEntity
    *           Donnée de référence dont on veut l'entité correspondante en base
    * @param em
    * @return Liste contenant zéro ou un élément, selon que la donnée est respectivement absente ou présente dans la base
    * @throws Exception
    */
   public static List<Object> getUniqueKeyEntity(Object refDataEntity, EntityManager em) throws Exception {
      List<Object> res = new ArrayList<>();
      IGetUniqueRefData<Object> getUniqueRefData = (IGetUniqueRefData<Object>) map.get(refDataEntity.getClass().getSimpleName());
      if (getUniqueRefData != null) {
         res = getUniqueRefData.getUniqueKeyEntity(refDataEntity, em);
      } else {
         System.out.println("Pas d'implémentation de IGetUniqueRefData pour la classe " + refDataEntity.getClass().getSimpleName());
      }
      return res;
   }

}
