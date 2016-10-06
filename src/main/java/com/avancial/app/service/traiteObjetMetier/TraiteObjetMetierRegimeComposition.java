package com.avancial.app.service.traiteObjetMetier;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.avancial.app.data.databean.importMotrice.MotriceRegimeCompositionCoachEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeCompositionEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Composition;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Voiture;

public class TraiteObjetMetierRegimeComposition extends AFiltreObjetMetier implements ITraiteObjetMetier {

   @Override
   public void traite(AtomicReference<Tranche> atomicTranche, MotriceRegimeEntity regime, Date dateDebutPeriode) throws ParseException {
      List<ASousRegimeTranche> listeCompositions = (List<ASousRegimeTranche>) atomicTranche.get().getAttributsField(Composition.class);
      if (listeCompositions == null) {
         listeCompositions = new ArrayList<ASousRegimeTranche>();
      }

      Regime newRegime = new Regime(regime.getPeriodMotriceRegime(), dateDebutPeriode);
      newRegime.filtreDates(getDateDebut(), getDateFin());
      if (this.filtreDateAjout(newRegime)) {
         for (MotriceRegimeCompositionEntity regimeComposition : regime.getMotriceRegimeComposition()) {
            List<Voiture> voitures = new ArrayList<Voiture>();
            for (MotriceRegimeCompositionCoachEntity voiture : regimeComposition.getCarsNumbers()) {
               voitures.add(new Voiture(voiture.getCoachNumberMotriceRegimeCompositionCoach(), null));
            }
            listeCompositions.add(new Composition(regimeComposition.getMotriceRefCompositionClassEntity().getLabelCompositionClass(),
                  regimeComposition.getMotriceRefCodeDiagrammeEntity().getLabelCodeDiagramme(),
                  regimeComposition.getMotriceRefRameCodeEntity().getLabelRameCode(), regimeComposition.getRmCodeMotriceRegimeComposition(), voitures,
                  new Regime(newRegime.getCodeRegime(), newRegime.getDateDebut(), newRegime.getDateFin(), newRegime.getListeJours())));
         }
      }
      atomicTranche.get().addAttributsField(listeCompositions);
   }

}
