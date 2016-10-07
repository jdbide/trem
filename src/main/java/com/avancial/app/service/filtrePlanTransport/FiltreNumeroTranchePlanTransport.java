package com.avancial.app.service.filtrePlanTransport;

import java.util.List;

/**
 * Filtre un plan de transport à partir d'une liste de numéros de tranches.
 * 
 * @author heloise.guillemaud
 *
 */
public class FiltreNumeroTranchePlanTransport extends AFiltreTrainPlanTransport {

   public FiltreNumeroTranchePlanTransport(List<String> numeroTranches) {
      super(new FiltreNumeroTrancheTrain(numeroTranches));
   }

}
