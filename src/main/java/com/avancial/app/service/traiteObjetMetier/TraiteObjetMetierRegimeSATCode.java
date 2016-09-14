package com.avancial.app.service.traiteObjetMetier;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeSatcodeEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.CodeSat;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

public class TraiteObjetMetierRegimeSATCode implements ITraiteObjetMetier {

   @Override
   public void traite(AtomicReference<Tranche> atomicTranche, MotriceRegimeEntity regime, Date dateDebutPeriode) throws ParseException {
      List<ASousRegimeTranche> listeCodeSat = (List<ASousRegimeTranche>) atomicTranche.get().getAttributsField(CodeSat.class);
      if (listeCodeSat == null) {
         listeCodeSat = new ArrayList<ASousRegimeTranche>();
      }
      for (MotriceRegimeSatcodeEntity regimeCodeSat : regime.getMotriceRegimeSatcode()) {
         listeCodeSat.add(new CodeSat(regimeCodeSat.getSatCodeMotriceRegimeSatcode(), new Regime(regime.getPeriodMotriceRegime(), dateDebutPeriode)));
      }
      atomicTranche.get().addAttributsField(listeCodeSat);
   }

}
