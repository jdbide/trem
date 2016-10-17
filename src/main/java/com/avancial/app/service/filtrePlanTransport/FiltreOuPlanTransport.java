package com.avancial.app.service.filtrePlanTransport;

import java.util.List;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

/**
 * Filtre un plan de transport en fonction de plusieurs critères : le plan de transport résultant contient tous les éléments qui répondent à au moins
 * l'un des critères.
 * 
 * @author heloise.guillemaud
 *
 */
public class FiltreOuPlanTransport extends AFiltreOuPlanTransport {

   /**
    * 
    * @param filtres
    *           Liste des filtres à appliquer en "ou"
    */
   public FiltreOuPlanTransport(IFiltre<PlanTransport>... filtres) {
      super(filtres);
   }

   @Override
   protected PlanTransport filtreOuDeuxPlanTransport(PlanTransport planTransport, IFiltre<PlanTransport> filtreUn,
         IFiltre<PlanTransport> filtreDeux) {
      PlanTransport planTransportUn = filtreUn.filtreParCritere(planTransport);
      PlanTransport planTransportDeux = filtreDeux.filtreParCritere(planTransport);

      Train trainDeux;
      Tranche trancheDeux;
      List<ASousRegimeTranche> attributsUn, attributsDeux;
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

                  /* Boucle sur les attributs de trancheUn */
                  for (Class<?> classe : trancheUn.getAttributs().keySet()) {
                     attributsUn = (List<ASousRegimeTranche>) trancheUn.getAttributsField(classe);
                     attributsDeux = (List<ASousRegimeTranche>) trancheDeux.getAttributsField(classe);

                     if (attributsUn != null && attributsDeux != null) {
                        /* Boucle sur les attributs de trancheUn */
                        for (ASousRegimeTranche attributUn : attributsUn) {
                           index = attributsDeux.indexOf(attributUn);
                           if (index < 0) {
                              /* L'attributUn n'est pas présent dans attributsDeux : on le rajoute */
                              attributsDeux.add(attributUn);
                           }
                        }

                     } else if (attributsUn != null) {
                        /* Les attributsUn ne sont pas présents dans trancheDeux */
                        trancheDeux.getAttributs().put(classe, attributsUn);
                     }
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
