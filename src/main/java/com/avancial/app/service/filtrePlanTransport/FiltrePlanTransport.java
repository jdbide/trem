package com.avancial.app.service.filtrePlanTransport;

import java.util.Iterator;

import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

/**
 * Filtre un plan de transport en fonction de critères définis grâce à un {@link ICritereTrainTranche} : les train-tranche qui ne correspondent pas au critère sont enlevés du plan de transport.
 * 
 * @author heloise.guillemaud
 *
 */
public class FiltrePlanTransport {

   /**
    * Filtre un plan de transport en fonction de critères donnés : les train-tranche qui ne correspondent pas au critère sont enlevés.
    * 
    * @param planTransport
    *           Plan de transport à filtrer
    * @param critereTrainTranche
    *           Critère de sélection sur un train-tranche
    */
   public void filtre(PlanTransport planTransport, ICritere<Tranche> critereTrainTranche) {
      /* Boucle sur les trains */
      for (Iterator<Train> itTrain = planTransport.getTrains().iterator(); itTrain.hasNext();) {
         Train train = itTrain.next();
         /* Boucle sur les tranches du train */
         for (Iterator<Tranche> itTranche = train.getTranches().iterator(); itTranche.hasNext();) {
            Tranche tranche = itTranche.next();
            /* Si la tranche ne satisfait pas le critère, on l'enlève */
            if (!critereTrainTranche.satisfaitCritere(tranche)) {
               itTranche.remove();
            }
         }
         /* Si le train n'a plus de tranche après filtrage, on l'enlève */
         if (train.getTranches().isEmpty()) {
            itTrain.remove();
         }
      }
   }

}
