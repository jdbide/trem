package com.avancial.app.service.traiteObjetMetier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.avancial.app.data.databean.importMotrice.MotriceRegimeDistributionEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEqpTypeEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.ARegimeComparable;
import com.avancial.app.data.objetsMetier.PlanTransport.Distribution;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.data.objetsMetier.PlanTransport.TypeEquipement;

public class TraiteObjetMetierRegimeEqpType implements ITraiteObjetMetier {

   @Override
   public void traite(AtomicReference<Tranche> atomicTranche, MotriceRegimeEntity regime) {
      List<ARegimeComparable> listeEqpTypes = new ArrayList<ARegimeComparable>();
      for (MotriceRegimeEqpTypeEntity regimelisteEqpType : regime.getMotriceRegimeEqpType()) {
         listeEqpTypes.add(new TypeEquipement(regimelisteEqpType.getEqpTypeRegimeEqpType(), new Regime(regime.getPeriodMotriceRegime(), null, null)));
      }
      atomicTranche.get().addAttributsField(listeEqpTypes);
   }

}
