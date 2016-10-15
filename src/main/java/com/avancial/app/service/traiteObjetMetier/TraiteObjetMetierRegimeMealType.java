package com.avancial.app.service.traiteObjetMetier;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeMealTypeEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeRepas;
import com.avancial.app.data.objetsMetier.PlanTransport.Horaire;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Repas;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

public class TraiteObjetMetierRegimeMealType extends AFiltreObjetMetier implements ITraiteObjetMetier {

   @Override
   public void traite(AtomicReference<Tranche> atomicTranche, MotriceRegimeEntity regime, Date dateDebutPeriode) throws ParseException {
      SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
      List<ASousRegimeTranche> listeMeal = (List<ASousRegimeTranche>) atomicTranche.get().getAttributsField(Repas.class);
      if (listeMeal == null) {
         listeMeal = new ArrayList<ASousRegimeTranche>();
      }
      Regime newRegime = new Regime(regime.getPeriodMotriceRegime(), dateDebutPeriode);
      newRegime.filtreDates(getDateDebut(), getDateFin());
      if (this.filtreDateAjout(newRegime)) {
         for (MotriceRegimeMealTypeEntity regimeMeal : regime.getMotriceRegimeMealType()) {
            listeMeal.add(new Repas(EnumTypeRepas.getEnumTypeRepas(regimeMeal.getMotriceRefMealTypeEntity().getCodeMealType()),
                  new Horaire(formatter.parse(regimeMeal.getBeginServiceHourRegimeMealType()),
                        formatter.parse(regimeMeal.getEndServiceHourMotriceRegimeMealType())),
                  new Regime(newRegime.getCodeRegime(), newRegime.getDateDebut(), newRegime.getDateFin(), newRegime.getListeJours())));
         }
      }
      atomicTranche.get().addAttributsField(listeMeal);

   }

}