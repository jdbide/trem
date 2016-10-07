package com.avancial.app.service.filtrePlanTransport;

import com.avancial.app.data.objetsMetier.PlanTransport.OrigineDestination;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;

/**
 * Filtre un plan de transport par rapport à une {@link OrigineDestination}.
 * 
 * @author heloise.guillemaud
 *
 */
public class FiltreODPlanTransport implements IFiltre<PlanTransport> {

   private IFiltre<PlanTransport> filtreOdPdt;

   public FiltreODPlanTransport(OrigineDestination origineDestination) {
      super();
      this.filtreOdPdt = new FiltreTranchePlanTransport(new FiltreODTranche(origineDestination));
   }

   @Override
   public PlanTransport filtreParCritere(PlanTransport object) {
      return this.filtreOdPdt.filtreParCritere(object);
   }

}
