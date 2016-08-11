package com.avancial.app.service.traiteObjetMetier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeSpecificityEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Compartiment;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumEtatSpecification;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Siege;
import com.avancial.app.data.objetsMetier.PlanTransport.Specification;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Voiture;

public class TraiteObjetMetierRegimeSpecificity implements ITraiteObjetMetier {

   @Override
   public void traite(AtomicReference<Tranche> atomicTranche, MotriceRegimeEntity regime) {
      List<ASousRegimeTranche> listeSpecifications = (List<ASousRegimeTranche>) atomicTranche.get().getAttributsField(Specification.class);
      if (listeSpecifications == null) {
         listeSpecifications = new ArrayList<ASousRegimeTranche>();
      }
      for (MotriceRegimeSpecificityEntity regimeSpecification : regime.getMotriceRegimeSpecificities()) {
         List<Compartiment> compartiments = new ArrayList<Compartiment>();
         List<Siege> sieges = new ArrayList<Siege>();
         sieges.add(new Siege(regimeSpecification.getSeatNumberMotriceRegimeSpecificity()));
         compartiments.add(new Compartiment(regimeSpecification.getCompartmentNumberMotriceRegimeSpecificity(), sieges));
         listeSpecifications.add(new Specification(new Voiture(regimeSpecification.getCoachNumberMotriceRegimeSpecificity(), compartiments), EnumEtatSpecification.fermer, new Regime(regime.getPeriodMotriceRegime())));
//         List<Voiture> voitures = new ArrayList<Voiture>();
      }   
      atomicTranche.get().addAttributsField(listeSpecifications);
   }

}
