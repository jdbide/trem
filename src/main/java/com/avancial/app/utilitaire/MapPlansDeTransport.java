/**
 * 
 */
package com.avancial.app.utilitaire;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicReference;

import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;

/**
 * @author mikael.muller
 *
 */
public class MapPlansDeTransport extends HashMap<Integer, AtomicReference<PlanTransport>> {
   
   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   
   public MapPlansDeTransport() {
      this.put(1, new AtomicReference<PlanTransport>(new PlanTransport()));
      this.put(2, new AtomicReference<PlanTransport>(new PlanTransport()));
   }

}
