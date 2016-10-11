package com.avancial.app.service.filtrePlanTransport;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;

/**
 * Filtre les régimes tranche dans un plan de transport en fonction de la valeur donnée au constructeur (pour les détails, voir
 * {@link FiltreRegimeTranche}).
 * 
 * @author heloise.guillemaud
 *
 */
public class FiltreRegimePlanTransport extends AFiltreTranchePlanTransport {

   public FiltreRegimePlanTransport(ASousRegimeTranche aSousRegimeTranche) {
      super(new FiltreRegimeTranche(aSousRegimeTranche));
   }

}
