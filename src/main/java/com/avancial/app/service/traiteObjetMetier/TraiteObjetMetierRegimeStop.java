package com.avancial.app.service.traiteObjetMetier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeStopEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Desserte;
import com.avancial.app.data.objetsMetier.PlanTransport.Gare;
import com.avancial.app.data.objetsMetier.PlanTransport.GareHoraire;
import com.avancial.app.data.objetsMetier.PlanTransport.Horaire;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

public class TraiteObjetMetierRegimeStop implements ITraiteObjetMetier {

   @Override
   public void traite(AtomicReference<Tranche> atomicTranche, MotriceRegimeEntity regime) {
      List<ASousRegimeTranche> listeDessertes = (List<ASousRegimeTranche>) atomicTranche.get().getAttributsField(Desserte.class);
      List<GareHoraire> garesHoraires = new ArrayList<GareHoraire>();
      if (listeDessertes == null) {
         listeDessertes = new ArrayList<ASousRegimeTranche>();
      }
      for (MotriceRegimeStopEntity regimeDesserte : regime.getMotriceRegimeStops()) {
         garesHoraires.add(new GareHoraire(new Gare(regimeDesserte.getStationMotriceRegimeStop()), new Horaire(null, null)));
      }
      listeDessertes.add(new Desserte(garesHoraires, new Regime(regime.getPeriodMotriceRegime())));
      atomicTranche.get().addAttributsField(listeDessertes);
   }

}
