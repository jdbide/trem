package com.avancial.app.service.filtrePlanTransport;

import java.util.ArrayList;
import java.util.List;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.OrigineDestination;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

/**
 * Filtre une {@link Tranche} par rapport à une {@link OrigineDestination}.
 * 
 * @author heloise.guillemaud
 *
 */
class FiltreODTranche implements IFiltre<Tranche> {

   private OrigineDestination origineDestination;

   public FiltreODTranche(OrigineDestination origineDestination) {
      super();
      this.origineDestination = origineDestination;
   }

   @Override
   public Tranche filtreParCritere(Tranche object) {
      Tranche tranche = new Tranche();
      tranche.setNumeroTranche(object.getNumeroTranche());
      tranche.setTrancheStatut(object.getTrancheStatut());
      tranche.setRegime(object.getRegime());

      if (object.getAttributsField(OrigineDestination.class) != null) {
         List<ASousRegimeTranche> ods = new ArrayList<>();
         for (ASousRegimeTranche aSousRegimeTranche : object.getAttributsField(OrigineDestination.class)) {
            OrigineDestination origineDestination = (OrigineDestination) aSousRegimeTranche;
            if (this.origineDestination.equals(origineDestination)) {
               ods.add(origineDestination);
            }
         }
         tranche.addAttributsField(ods);
      }
      return tranche;
   }

}
