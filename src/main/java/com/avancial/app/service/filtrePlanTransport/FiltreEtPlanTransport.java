package com.avancial.app.service.filtrePlanTransport;

import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;

/**
 * Filtre les données d'un plan de transport en appliquant plusieurs filtres à la suite.
 * 
 * @author heloise.guillemaud
 *
 */
public class FiltreEtPlanTransport extends AFiltreEtPlanTransport {

   /**
    * 
    * @param filtres
    *           Liste des filtres à appliquer à la suite
    */
   public FiltreEtPlanTransport(IFiltre<PlanTransport>... filtres) {
      super(filtres);
   }
}
