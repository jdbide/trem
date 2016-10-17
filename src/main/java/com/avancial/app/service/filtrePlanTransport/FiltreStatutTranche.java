package com.avancial.app.service.filtrePlanTransport;

import com.avancial.app.data.objetsMetier.PlanTransport.EnumTrancheStatut;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

/**
 * Critère de sélection sur le statut d'une tranche.
 * 
 * @author jeandaniel.bide
 *
 */
public class FiltreStatutTranche implements IFiltre<Train> {

   private EnumTrancheStatut statut;

   public FiltreStatutTranche(EnumTrancheStatut s) {
      super();
      statut = s;
   }


   @Override
   public Train filtreParCritere(Train object) {

      Train train = object.clone();
      
      /* Boucle sur la liste d'objets de la classe donnée */
      if (object.getTranches() != null) {
         for(Tranche t : object.getTranches()){
            if(!t.getTrancheStatut().equals(statut))
               train.getTranches().remove(t);        
         }
      }    
      return train;
   }

}
