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

public class TraiteObjetMetierRegimeDistribution extends AFiltreObjetMetier implements ITraiteObjetMetier {

   @Override
   public void traite(AtomicReference<Tranche> atomicTranche, MotriceRegimeEntity regime, Date dateDebutPeriode) throws ParseException {
      List<ASousRegimeTranche> listeDistributions = (List<ASousRegimeTranche>) atomicTranche.get().getAttributsField(Distribution.class);
      if (listeDistributions == null) {
         listeDistributions = new ArrayList<ASousRegimeTranche>();
      }
      Regime newRegime = new Regime(regime.getPeriodMotriceRegime(), dateDebutPeriode);
      newRegime.filtreDates(getDateDebut(), getDateFin());
      if (this.filtreDateAjout(newRegime)) {
         for (MotriceRegimeDistributionEntity regimeDistribution : regime.getMotriceRegimeDistribution()) {
            listeDistributions.add(new Distribution(regimeDistribution.getDistribIndexMotriceRegimeDistribution(),
                  new Regime(newRegime.getCodeRegime(), newRegime.getDateDebut(), newRegime.getDateFin(), newRegime.getListeJours())));
         }
      }
      atomicTranche.get().addAttributsField(listeDistributions);
   }

}
