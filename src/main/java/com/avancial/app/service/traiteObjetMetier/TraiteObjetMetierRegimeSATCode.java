package com.avancial.app.service.traiteObjetMetier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.avancial.app.data.databean.importMotrice.MotriceRefSatcodeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeFareProfileEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeSatcodeEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.CodeSat;
import com.avancial.app.data.objetsMetier.PlanTransport.FareProfile;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

public class TraiteObjetMetierRegimeSATCode implements ITraiteObjetMetier {

   @Override
   public void traite(AtomicReference<Tranche> atomicTranche, MotriceRegimeEntity regime) {
      List<ASousRegimeTranche> listeCodeSat = (List<ASousRegimeTranche>) atomicTranche.get().getAttributsField(CodeSat.class);
      if (listeCodeSat == null) {
         listeCodeSat = new ArrayList<ASousRegimeTranche>();
      }
      for (MotriceRegimeSatcodeEntity regimeCodeSat : regime.getMotriceRegimeSatcode()) {
         listeCodeSat.add(new CodeSat(regimeCodeSat.getSatCodeMotriceRegimeSatcode(), new Regime(regime.getPeriodMotriceRegime(), null, null)));
      }
      atomicTranche.get().addAttributsField(listeCodeSat);
   }

}
