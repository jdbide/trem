package com.avancial.app.service.filtrePlanTransport;

import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;

/**
 * Filtre les données d'un plan de transport en appliquant deux filtres à la suite.
 * 
 * @author heloise.guillemaud
 *
 */
public class FiltreEtPlanTransport extends AFiltreEtPlanTransport {

   public FiltreEtPlanTransport(IFiltre<PlanTransport> filtreUn, IFiltre<PlanTransport> filtreDeux) {
      super(filtreUn, filtreDeux);
   }
}
