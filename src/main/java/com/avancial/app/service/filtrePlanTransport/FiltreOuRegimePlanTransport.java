package com.avancial.app.service.filtrePlanTransport;

import java.util.Date;

import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.socle.utils.DateUtils;

/**
 * Filtre un plan de transport en fonction de deux critères : les régimes des tranches du plan de transport résultant contiennent toutes les dates
 * pour lesquelles les données répondent à l'un ou à l'autre des critères.
 * 
 * @author heloise.guillemaud
 *
 */
public class FiltreOuRegimePlanTransport implements IFiltre<PlanTransport> {

   private IFiltre<PlanTransport> filtreUn;
   private IFiltre<PlanTransport> filtreDeux;

   public FiltreOuRegimePlanTransport(IFiltre<PlanTransport> filtreUn, IFiltre<PlanTransport> filtreDeux) {
      super();
      this.filtreUn = filtreUn;
      this.filtreDeux = filtreDeux;
   }

   @Override
   public PlanTransport filtreParCritere(PlanTransport object) {
      PlanTransport planTransportUn = this.filtreUn.filtreParCritere(object);
      PlanTransport planTransportDeux = this.filtreDeux.filtreParCritere(object);

      Train trainDeux;
      Tranche trancheDeux;
      int index;

      /* Boucle sur les trains du pdt filtré par le premier filtre */
      for (Train trainUn : planTransportUn.getTrains()) {
         index = planTransportDeux.getTrains().indexOf(trainUn);
         if (index >= 0) {
            /* Le trainUn est présent dans le pdt filtré par le second filtre */
            trainDeux = planTransportDeux.getTrains().get(index);

            /* Boucle sur les tranches du trainUn */
            for (Tranche trancheUn : trainUn.getTranches()) {
               index = trainDeux.getTranches().indexOf(trancheUn);
               if (index >= 0) {
                  /* La trancheUn est présente dans le trainDeux */
                  trancheDeux = trainDeux.getTranches().get(index);

                  /* On fusionne leurs listes de dates des régimes */
                  for (Date date : trancheUn.getRegime().getListeJours()) {
                     DateUtils.addDayToDateList(trancheDeux.getRegime().getListeJours(), date);
                  }
               } else {
                  /* La trancheUn n'est pas présente dans trainDeux : on la rajoute */
                  trainDeux.getTranches().add(trancheUn);
               }
            }
         } else {
            /* La trainUn n'est pas présent dans planTransportDeux : on le rajoute */
            planTransportDeux.getTrains().add(trainUn);
         }
      }
      return planTransportDeux;
   }

}
