package com.avancial.app.service.filtrePlanTransport;

import java.util.List;

import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

/**
 * Filtre d'un {@link Train} par rapport à des numéros de {@link Tranche} : on ne retient que les tranches avec les numéros précisés.
 * 
 * @author heloise.guillemaud
 *
 */
class FiltreNumeroTrancheTrain implements IFiltre<Train> {

   /**
    * Liste des numéros de tranche pour le filtre
    */
   private List<String> numeroTranches;

   public FiltreNumeroTrancheTrain(List<String> numeroTranches) {
      super();
      this.numeroTranches = numeroTranches;
   }

   @Override
   public Train filtreParCritere(Train object) {
      Train train = new Train();
      train.setNumeroTrain(object.getNumeroTrain());
      train.setValidForRR(object.isValidForRR());
      for (Tranche tranche : object.getTranches()) {
         if (this.numeroTranches.contains(tranche.getNumeroTranche())) {
            train.getTranches().add(tranche);
         }
      }
      return train;
   }

}
