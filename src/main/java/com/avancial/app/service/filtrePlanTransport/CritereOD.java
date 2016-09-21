package com.avancial.app.service.filtrePlanTransport;

import java.util.List;

import com.avancial.app.data.objetsMetier.PlanTransport.OrigineDestination;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

/**
 * Critère de sélection sur la présence d'une {@link OrigineDestination} dans la tranche.
 * 
 * @author heloise.guillemaud
 *
 */
public class CritereOD implements ICritereTrainTranche {

   private OrigineDestination origineDestination;

   /**
    * Présence d'une {@link OrigineDestination} dans la tranche.
    * 
    * @param origineDestination
    */
   public CritereOD(OrigineDestination origineDestination) {
      super();
      this.origineDestination = origineDestination;
   }

   @Override
   public boolean satisfaitCritere(Train train, Tranche tranche) {
      List<OrigineDestination> liste = (List<OrigineDestination>) tranche.getAttributsField(OrigineDestination.class);
      if (liste != null) {
         for (OrigineDestination origineDestination : liste) {
            if (origineDestination.equals(this.origineDestination)) {
               return true;
            }
         }
      }
      return false;
   }

}
