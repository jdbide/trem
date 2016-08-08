package com.avancial.app.service.traiteObjetMetier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.avancial.app.data.databean.importMotrice.MotriceRegimeDistributionEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.ARegimeComparable;
import com.avancial.app.data.objetsMetier.PlanTransport.Distribution;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

public class TraiteObjetMetierRegimeDistribution implements ITraiteObjetMetier {

   @Override
   public void traite(AtomicReference<Tranche> atomicTranche, MotriceRegimeEntity regime) {
      List<ARegimeComparable> listeDistributions = new ArrayList<ARegimeComparable>();
      for (MotriceRegimeDistributionEntity regimeDistribution : regime.getMotriceRegimeDistribution()) {
         listeDistributions.add(new Distribution(regimeDistribution.getDistribIndexMotriceRegimeDistribution(), new Regime(regime.getPeriodMotriceRegime(), null, null)));
      }
      atomicTranche.get().addAttributsField(listeDistributions);
   }

}
