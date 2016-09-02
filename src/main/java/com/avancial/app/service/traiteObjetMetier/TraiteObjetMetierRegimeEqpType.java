package com.avancial.app.service.traiteObjetMetier;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
   public void traite(AtomicReference<Tranche> atomicTranche, MotriceRegimeEntity regime, Date dateDebutPeriode) {
      List<ASousRegimeTranche> listeTypeEquipement = (List<ASousRegimeTranche>) atomicTranche.get().getAttributsField(TypeEquipement.class);
      if (listeTypeEquipement == null) {
         listeTypeEquipement = new ArrayList<ASousRegimeTranche>();
      }
      for (MotriceRegimeEqpTypeEntity regimeEqpType : regime.getMotriceRegimeEqpType()) {
         try {
            listeTypeEquipement.add(new TypeEquipement(regimeEqpType.getEqpTypeRegimeEqpType(), new Regime(regime.getPeriodMotriceRegime(), dateDebutPeriode)));
         } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
      atomicTranche.get().addAttributsField(listeTypeEquipement);
   }

}
