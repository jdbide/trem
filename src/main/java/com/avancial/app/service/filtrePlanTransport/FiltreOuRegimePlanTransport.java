package com.avancial.app.service.filtrePlanTransport;

import java.util.Date;

import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.socle.utils.DateUtils;

/**
 * Filtre un plan de transport en fonction de plusieurs critères : les régimes des tranches du plan de transport résultant contiennent toutes les
 * dates pour lesquelles les données répondent à au moins l'un des critères.
 * 
 * @author heloise.guillemaud
 *
 */
public class FiltreOuRegimePlanTransport extends AFiltreOuPlanTransport {

   /**
    * 
    * @param filtres
    *           Liste des filtres à appliquer en "ou"
    */
   public FiltreOuRegimePlanTransport(IFiltre<PlanTransport>... filtres) {
      super(filtres);
   }

   @Override
   protected PlanTransport filtreOuDeuxPlanTransport(PlanTransport planTransport, IFiltre<PlanTransport> filtreUn,
         IFiltre<PlanTransport> filtreDeux) {
      PlanTransport planTransportUn = filtreUn.filtreParCritere(planTransport);
      PlanTransport planTransportDeux = filtreDeux.filtreParCritere(planTransport);

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
