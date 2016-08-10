package com.avancial.app.service.traiteObjetMetier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeRestrictionEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeServiceEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumClasseService;
import com.avancial.app.data.objetsMetier.PlanTransport.Gare;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Restriction;
import com.avancial.app.data.objetsMetier.PlanTransport.ServiceABord;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

public class TraiteObjetMetierRegimeService implements ITraiteObjetMetier {

   @Override
   public void traite(AtomicReference<Tranche> atomicTranche, MotriceRegimeEntity regime) {
      List<ASousRegimeTranche> listeServices = (List<ASousRegimeTranche>) atomicTranche.get().getAttributsField(ServiceABord.class);
      if (listeServices == null) {
         listeServices = new ArrayList<ASousRegimeTranche>();
      }
      for (MotriceRegimeServiceEntity regimeService : regime.getMotriceRegimeServices()) {
         listeServices.add(new ServiceABord(regimeService.getServiceCodeMotriceRegimeService(), EnumClasseService.getEnumClasseService(regimeService.getClassMotriceRegimeService()), new Gare(regimeService.getOrigMotriceRegimeService()), new Gare(regimeService.getDestMotriceRegimeService()), new Regime(regime.getPeriodMotriceRegime())));
      }
      atomicTranche.get().addAttributsField(listeServices); 
   }

}
