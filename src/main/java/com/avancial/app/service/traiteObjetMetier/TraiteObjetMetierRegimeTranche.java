package com.avancial.app.service.traiteObjetMetier;

import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

public class TraiteObjetMetierRegimeTranche extends AFiltreObjetMetier implements ITraiteObjetMetier {

   @Override
   public void traite(AtomicReference<Tranche> atomicTranche, MotriceRegimeEntity regime, Date dateDebutPeriode) throws ParseException {
      Regime newRegime = new Regime(regime.getPeriodMotriceRegime(), dateDebutPeriode);
      newRegime.filtreDates(getDateDebut(), getDateFin());
      if (this.filtreDateAjout(newRegime)) {
         atomicTranche.get()
               .setRegime(new Regime(newRegime.getCodeRegime(), newRegime.getDateDebut(), newRegime.getDateFin(), newRegime.getListeJours()));
      }
   }

}
