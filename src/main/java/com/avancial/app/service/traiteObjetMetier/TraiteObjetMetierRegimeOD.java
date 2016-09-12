package com.avancial.app.service.traiteObjetMetier;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeODEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Gare;
import com.avancial.app.data.objetsMetier.PlanTransport.OrigineDestination;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

public class TraiteObjetMetierRegimeOD implements ITraiteObjetMetier {

   @Override
   public void traite(AtomicReference<Tranche> atomicTranche, MotriceRegimeEntity regime, Date dateDebutPeriode) throws ParseException {
      List<ASousRegimeTranche> listeOD = (List<ASousRegimeTranche>) atomicTranche.get().getAttributsField(OrigineDestination.class);
      if (listeOD == null) {
         listeOD = new ArrayList<ASousRegimeTranche>();
      }
      for (MotriceRegimeODEntity regimeOD : regime.getMotriceRegimeOD()) {
         listeOD.add(new OrigineDestination(new Gare(regimeOD.getOriMotriceRegimeOD()), new Gare(regimeOD.getDestMotriceRegimeOD()),
               new Regime(regime.getPeriodMotriceRegime(), dateDebutPeriode)));
      }
      atomicTranche.get().addAttributsField(listeOD);
   }

}
