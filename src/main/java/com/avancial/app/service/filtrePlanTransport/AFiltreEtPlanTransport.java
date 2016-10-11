package com.avancial.app.service.filtrePlanTransport;

import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;

/**
 * Filtre un plan de transport en appliquant deux filtres à la suite.
 * 
 * @author heloise.guillemaud
 *
 */
public abstract class AFiltreEtPlanTransport implements IFiltre<PlanTransport> {

   private IFiltre<PlanTransport> filtreUn;
   private IFiltre<PlanTransport> filtreDeux;

   public AFiltreEtPlanTransport(IFiltre<PlanTransport> filtreUn, IFiltre<PlanTransport> filtreDeux) {
      super();
      this.filtreUn = filtreUn;
      this.filtreDeux = filtreDeux;
   }

   @Override
   public PlanTransport filtreParCritere(PlanTransport object) {
      PlanTransport planTransport = this.filtreUn.filtreParCritere(object);
      return this.filtreDeux.filtreParCritere(planTransport);
   }

}
