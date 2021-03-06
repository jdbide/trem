package com.avancial.app.service.traiteObjetMetier;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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

public class TraiteObjetMetierRegimeSpecificity extends AFiltreObjetMetier implements ITraiteObjetMetier {

   @Override
   public void traite(AtomicReference<Tranche> atomicTranche, MotriceRegimeEntity regime, Date dateDebutPeriode) throws ParseException {
      List<ASousRegimeTranche> listeSpecifications = (List<ASousRegimeTranche>) atomicTranche.get().getAttributsField(Specification.class);
      if (listeSpecifications == null) {
         listeSpecifications = new ArrayList<ASousRegimeTranche>();
      }
      Regime newRegime = new Regime(regime.getPeriodMotriceRegime(), dateDebutPeriode);
      newRegime.filtreDates(getDateDebut(), getDateFin());
      if (this.filtreDateAjout(newRegime)) {
         for (MotriceRegimeSpecificityEntity regimeSpecification : regime.getMotriceRegimeSpecificities()) {
            List<Compartiment> compartiments = new ArrayList<Compartiment>();
            List<Siege> sieges = new ArrayList<Siege>();
            sieges.add(new Siege(regimeSpecification.getSeatNumberMotriceRegimeSpecificity()));
            compartiments.add(new Compartiment(regimeSpecification.getCompartmentNumberMotriceRegimeSpecificity(), sieges));
            listeSpecifications.add(new Specification(new Voiture(regimeSpecification.getCoachNumberMotriceRegimeSpecificity(), compartiments),
                  EnumEtatSpecification.Blocked,
                  new Regime(newRegime.getCodeRegime(), newRegime.getDateDebut(), newRegime.getDateFin(), newRegime.getListeJours())));
         }
      }
      atomicTranche.get().addAttributsField(listeSpecifications);
   }

}
