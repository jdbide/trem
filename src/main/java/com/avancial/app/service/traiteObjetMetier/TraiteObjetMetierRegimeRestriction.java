package com.avancial.app.service.traiteObjetMetier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeODEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeRestrictionEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Gare;
import com.avancial.app.data.objetsMetier.PlanTransport.OrigineDestination;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Restriction;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

public class TraiteObjetMetierRegimeRestriction implements ITraiteObjetMetier {

   @Override
   public void traite(AtomicReference<Tranche> atomicTranche, MotriceRegimeEntity regime) {
      List<ASousRegimeTranche> listeRestrictions = (List<ASousRegimeTranche>) atomicTranche.get().getAttributsField(Restriction.class);
      if (listeRestrictions == null) {
         listeRestrictions = new ArrayList<ASousRegimeTranche>();
      }
      for (MotriceRegimeRestrictionEntity regimeRestriction : regime.getMotriceRegimeRestrictions()) {
         listeRestrictions.add(new Restriction(new Gare(regimeRestriction.getOrigineMotriceRegimeRestriction()), new Gare(regimeRestriction.getDestinationMotriceRegimeRestriction()), null, new Regime(regime.getPeriodMotriceRegime(), null, null)));
      }
      atomicTranche.get().addAttributsField(listeRestrictions);
   }

}
