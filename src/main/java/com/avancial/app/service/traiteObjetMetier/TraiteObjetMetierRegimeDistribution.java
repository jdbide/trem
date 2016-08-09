package com.avancial.app.service.traiteObjetMetier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.avancial.app.data.databean.importMotrice.MotriceRegimeDistributionEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEqpTypeEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Distribution;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.data.objetsMetier.PlanTransport.TypeEquipement;

public class TraiteObjetMetierRegimeDistribution implements ITraiteObjetMetier {

   @Override
   public void traite(AtomicReference<Tranche> atomicTranche, MotriceRegimeEntity regime) {
      List<ASousRegimeTranche> listeDistributions = (List<ASousRegimeTranche>) atomicTranche.get().getAttributsField(Distribution.class);
      if (listeDistributions == null) {
         listeDistributions = new ArrayList<ASousRegimeTranche>();
      }
      for (MotriceRegimeDistributionEntity regimeDistribution : regime.getMotriceRegimeDistribution()) {
         listeDistributions.add(new Distribution(regimeDistribution.getDistribIndexMotriceRegimeDistribution(), new Regime(regime.getPeriodMotriceRegime())));
      }
      atomicTranche.get().addAttributsField(listeDistributions);
   }

}
