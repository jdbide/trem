package com.avancial.app.service.filtrePlanTransport;

import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;

/**
 * Filtre les régimes tranche d'un plan de transport en appliquant plusieurs filtres à la suite.
 * 
 * @author heloise.guillemaud
 *
 */
public class FiltreEtRegimePlanTransport extends AFiltreEtPlanTransport {

   /**
    * 
    * @param filtres
    *           Liste des filtres à appliquer à la suite
    */
   public FiltreEtRegimePlanTransport(IFiltre<PlanTransport>... filtres) {
      super(filtres);
   }

}
