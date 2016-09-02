package com.avancial.app.service.traiteObjetMetier;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.avancial.app.data.databean.importMotrice.MotriceRegimeDistributionEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Distribution;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

public class TraiteObjetMetierRegimeDistribution implements ITraiteObjetMetier {

   @Override
   public void traite(AtomicReference<Tranche> atomicTranche, MotriceRegimeEntity regime, Date dateDebutPeriode) {
      List<ASousRegimeTranche> listeDistributions = (List<ASousRegimeTranche>) atomicTranche.get().getAttributsField(Distribution.class);
      if (listeDistributions == null) {
         listeDistributions = new ArrayList<ASousRegimeTranche>();
      }
      for (MotriceRegimeDistributionEntity regimeDistribution : regime.getMotriceRegimeDistribution()) {
         try {
            listeDistributions.add(new Distribution(regimeDistribution.getDistribIndexMotriceRegimeDistribution(), new Regime(regime.getPeriodMotriceRegime(), dateDebutPeriode)));
         } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      atomicTranche.get().addAttributsField(listeDistributions);
   }

}
