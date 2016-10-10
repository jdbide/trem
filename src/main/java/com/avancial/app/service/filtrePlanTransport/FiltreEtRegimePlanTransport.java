package com.avancial.app.service.filtrePlanTransport;

import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;

/**
 * Filtre les régimes tranche d'un plan de transport en appliquant deux filtres à la suite.
 * 
 * @author heloise.guillemaud
 *
 */
public class FiltreEtRegimePlanTransport extends AFiltreEtPlanTransport {

   public FiltreEtRegimePlanTransport(IFiltre<PlanTransport> filtreUn, IFiltre<PlanTransport> filtreDeux) {
      super(filtreUn, filtreDeux);
   }

}
