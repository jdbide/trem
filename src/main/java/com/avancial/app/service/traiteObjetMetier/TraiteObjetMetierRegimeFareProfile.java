package com.avancial.app.service.traiteObjetMetier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeFareProfileEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.FareProfile;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

public class TraiteObjetMetierRegimeFareProfile implements ITraiteObjetMetier {

   @Override
   public void traite(AtomicReference<Tranche> atomicTranche, MotriceRegimeEntity regime) {
      List<ASousRegimeTranche> listeFareProfile = (List<ASousRegimeTranche>) atomicTranche.get().getAttributsField(FareProfile.class);
      if (listeFareProfile == null) {
         listeFareProfile = new ArrayList<ASousRegimeTranche>();
      }
      for (MotriceRegimeFareProfileEntity regimeFareProfile : regime.getMotriceRegimeFareProfile()) {
         listeFareProfile.add(new FareProfile(regimeFareProfile.getFareProfileCodeMotriceRegimeFareProfile(), new Regime(regime.getPeriodMotriceRegime())));
      }
      atomicTranche.get().addAttributsField(listeFareProfile);
   }

}
