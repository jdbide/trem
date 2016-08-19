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
   
   public void setPlanTransportActive(PlanTransport PTActive) {
      this.get(1).set(PTActive);
   }
   
   public void setPlanTransportDraft(PlanTransport PTDraft) {
      this.get(2).set(PTDraft);
   }
   
   public void setMapPlansDeTransport(PlanTransport PTDraft, PlanTransport PTActive) {
      this.setPlanTransportActive(PTActive);
      this.setPlanTransportDraft(PTDraft);
   }
   
   public PlanTransport getPlanTransportActive() {
      return this.get(1).get();
   }

   public PlanTransport getPlanTransportDraft() {
      return this.get(2).get();
   }
   
}
