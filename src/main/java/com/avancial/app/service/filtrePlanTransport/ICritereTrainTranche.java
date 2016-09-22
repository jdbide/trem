package com.avancial.app.service.filtrePlanTransport;

import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

/**
 * Critère de sélection sur un train-tranche
 * 
 * @author heloise.guillemaud
 *
 */
public interface ICritereTrainTranche {

   /**
    * Détermine si le train-tranche donné satisfait un critère défini par l'implémentation.
    * 
    * @param train
    *           Train donné
    * @param tranche
    *           Tranche du train donné
    * @return {@code true} si le train-tranche satisfait le critère, {@code false} sinon
    */
   public boolean satisfaitCritere(Train train, Tranche tranche);
}
