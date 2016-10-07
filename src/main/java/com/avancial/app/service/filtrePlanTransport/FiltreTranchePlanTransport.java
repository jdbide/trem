package com.avancial.app.service.filtrePlanTransport;

import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

/**
 * Filtre un plan de transport à partir d'un {@link IFiltre} sur les {@link Tranche} : applique ce filtre sur toutes les tranches de tous les trains
 * du plan de transport.
 * 
 * @author heloise.guillemaud
 *
 */
class FiltreTranchePlanTransport implements IFiltre<PlanTransport> {

   private IFiltre<Tranche> filtreTranche;

   public FiltreTranchePlanTransport(IFiltre<Tranche> filtreTranche) {
      super();
      this.filtreTranche = filtreTranche;
   }

   @Override
   public PlanTransport filtreParCritere(PlanTransport object) {
      PlanTransport planTransport = new PlanTransport();
      planTransport.setCompagnie(object.getCompagnie());
      Train trainFiltre;
      
      /* Boucle sur les trains du plan de transport */
      for (Train train : object.getTrains()) {
         trainFiltre = new Train();
         trainFiltre.setNumeroTrain(train.getNumeroTrain());
         trainFiltre.setValidForRR(train.isValidForRR());
         
         /* Boucle sur les tranches du train */
         for (Tranche tranche : train.getTranches()) {
            /* On ajoute au résultat les tranches filtrées */
            trainFiltre.getTranches().add(this.filtreTranche.filtreParCritere(tranche));
         }

         if (!trainFiltre.getTranches().isEmpty()) {
            planTransport.getTrains().add(trainFiltre);
         }
      }
      return planTransport;
   }

}
