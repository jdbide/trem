package com.avancial.app.service.traiteObjetMetier;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeStopEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Desserte;
import com.avancial.app.data.objetsMetier.PlanTransport.Gare;
import com.avancial.app.data.objetsMetier.PlanTransport.GareHoraire;
import com.avancial.app.data.objetsMetier.PlanTransport.Horaire;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

public class TraiteObjetMetierRegimeStop implements ITraiteObjetMetier {

   @Override
   public void traite(AtomicReference<Tranche> atomicTranche, MotriceRegimeEntity regime, Date dateDebutPeriode) throws ParseException {
      SimpleDateFormat formatter = new SimpleDateFormat("HHmm");
      String heureArrivee, heureDepart;
      List<ASousRegimeTranche> listeDessertes = (List<ASousRegimeTranche>) atomicTranche.get().getAttributsField(Desserte.class);
      List<GareHoraire> garesHoraires = new ArrayList<GareHoraire>();
      if (listeDessertes == null) {
         listeDessertes = new ArrayList<ASousRegimeTranche>();
      }
      for (MotriceRegimeStopEntity regimeDesserte : regime.getMotriceRegimeStops()) {
         Date horaireDebut = null;
         Date horaireFin = null;
         heureArrivee = regimeDesserte.getArrivalHourMotriceRegimeStop();
         if (!heureArrivee.equals("    ")) {
            horaireDebut = formatter.parse(heureArrivee);
         }
         heureDepart = regimeDesserte.getDepartureHourMotriceRegimeStop();
         if (!heureDepart.equals("    ")) {
            horaireFin = formatter.parse(heureDepart);
         }
         garesHoraires.add(new GareHoraire(new Gare(regimeDesserte.getStationMotriceRegimeStop()), new Horaire(horaireDebut, horaireFin)));

      }
      listeDessertes.add(new Desserte(garesHoraires, new Regime(regime.getPeriodMotriceRegime(), dateDebutPeriode)));
      atomicTranche.get().addAttributsField(listeDessertes);
   }

}
