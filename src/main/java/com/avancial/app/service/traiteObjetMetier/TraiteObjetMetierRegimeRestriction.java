package com.avancial.app.service.traiteObjetMetier;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeRestrictionEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Gare;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Restriction;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

public class TraiteObjetMetierRegimeRestriction implements ITraiteObjetMetier {

   @Override
   public void traite(AtomicReference<Tranche> atomicTranche, MotriceRegimeEntity regime, Date dateDebutPeriode) throws ParseException {
      List<ASousRegimeTranche> listeRestrictions = (List<ASousRegimeTranche>) atomicTranche.get().getAttributsField(Restriction.class);
      if (listeRestrictions == null) {
         listeRestrictions = new ArrayList<ASousRegimeTranche>();
      }
      for (MotriceRegimeRestrictionEntity regimeRestriction : regime.getMotriceRegimeRestrictions()) {
         listeRestrictions.add(new Restriction(new Gare(regimeRestriction.getOrigineMotriceRegimeRestriction()),
               new Gare(regimeRestriction.getDestinationMotriceRegimeRestriction()), null,
               new Regime(regime.getPeriodMotriceRegime(), dateDebutPeriode)));
      }
      atomicTranche.get().addAttributsField(listeRestrictions);
   }

}
