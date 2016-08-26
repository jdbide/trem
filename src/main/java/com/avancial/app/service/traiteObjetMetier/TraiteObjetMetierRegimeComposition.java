package com.avancial.app.service.traiteObjetMetier;

import java.util.ArrayList;
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

public class TraiteObjetMetierRegimeComposition implements ITraiteObjetMetier {

    @Override
    public void traite(AtomicReference<Tranche> atomicTranche, MotriceRegimeEntity regime) {
        List<ASousRegimeTranche> listeCompositions = (List<ASousRegimeTranche>) atomicTranche.get()
                .getAttributsField(Composition.class);
        if (listeCompositions == null) {
            listeCompositions = new ArrayList<ASousRegimeTranche>();
        }
        for (MotriceRegimeCompositionEntity regimeComposition : regime.getMotriceRegimeComposition()) {
            List<Voiture> voitures = new ArrayList<Voiture>();
            for (MotriceRegimeCompositionCoachEntity voiture : regimeComposition.getCarsNumbers()) {
                voitures.add(new Voiture(voiture.getCoachNumberMotriceRegimeCompositionCoach(), null));
            }
            listeCompositions.add(new Composition(regimeComposition.getClassCodeMotriceRegimeComposition(),
                    regimeComposition.getDiagCodeMotriceRegimeComposition(),
                    regimeComposition.getRameCodeMotriceRegimeComposition(),
                    regimeComposition.getRmCodeMotriceRegimeComposition(), voitures,
                    new Regime(regime.getPeriodMotriceRegime())));
        }
        atomicTranche.get().addAttributsField(listeCompositions);
    }

}
