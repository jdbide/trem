package com.avancial.app.data.objetsMetier.PlanTransport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Map des attributs d'une tranche : la clé est la classe des attributs en question (qui hérite de {@link ASousRegimeTranche}), et la valeur est une
 * liste d'objets de la même classe que la clé.
 * 
 * @author heloise.guillemaud
 *
 */
public class MapTranche extends HashMap<Class<?>, List<? extends ASousRegimeTranche>> {

   private static final long serialVersionUID = 1L;

   /**
    * @return the previous value associated with key, or null if there was no mapping for key (A null return can also indicate that the map previously
    *         associated null with key.) or the runtime class of the list values is not equal to the key.
    */
   @Override
   public List<? extends ASousRegimeTranche> put(Class<?> key, List<? extends ASousRegimeTranche> value) {
      if (value != null && value.size() > 0 && value.get(0).getClass().equals(key)) {
         return super.put(key, value);
      }
      return null;
   }

   public MapTranche clone() {
      HashMap<String, String> test = new HashMap<>();
      test.clone();
      MapTranche mapTranche = new MapTranche();
      for (Class<?> comparable : this.keySet()) {
         List<ASousRegimeTranche> objects = new ArrayList<>();
         for (ASousRegimeTranche object : this.get(comparable)) {
            objects.add(object.clone());
         }
         mapTranche.put(comparable, objects);
      }
      return mapTranche;
   }

}
