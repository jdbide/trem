package com.avancial.app.service.filtrePlanTransport;

import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

/**
 * Implémentation d'un critère de condition "OU" entre deux critères {@link ICritereTrainTranche}
 * 
 * @author heloise.guillemaud
 *
 */
public class CritereOu implements ICritereTrainTranche {

   private ICritereTrainTranche critere1;
   private ICritereTrainTranche critere2;

   /**
    * Opération logique "OU" entre deux critères
    * 
    * @param critere1
    * @param critere2
    */
   public CritereOu(ICritereTrainTranche critere1, ICritereTrainTranche critere2) {
      super();
      this.critere1 = critere1;
      this.critere2 = critere2;
   }

   @Override
   public boolean satisfaitCritere(Train train, Tranche tranche) {
      return this.critere1.satisfaitCritere(train, tranche) || this.critere2.satisfaitCritere(train, tranche);
   }

}
