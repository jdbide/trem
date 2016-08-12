package com.avancial.app.service.traiteObjetMetier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEqpTypeEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.data.objetsMetier.PlanTransport.TypeEquipement;

public class TraiteObjetMetierRegimeEqpType implements ITraiteObjetMetier {

   @Override
   public void traite(AtomicReference<Tranche> atomicTranche, MotriceRegimeEntity regime) {
      List<ASousRegimeTranche> listeTypeEquipement = (List<ASousRegimeTranche>) atomicTranche.get().getAttributsField(TypeEquipement.class);
      if (listeTypeEquipement == null) {
         listeTypeEquipement = new ArrayList<ASousRegimeTranche>();
      }
      for (MotriceRegimeEqpTypeEntity regimeEqpType : regime.getMotriceRegimeEqpType()) {
         listeTypeEquipement.add(new TypeEquipement(regimeEqpType.getEqpTypeRegimeEqpType(), new Regime(regime.getPeriodMotriceRegime())));
      }
      atomicTranche.get().addAttributsField(listeTypeEquipement);
   }

}