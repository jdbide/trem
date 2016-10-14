package com.avancial.app.service.filtrePlanTransport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.avancial.app.data.objetsMetier.PlanTransport.Desserte;
import com.avancial.app.data.objetsMetier.PlanTransport.Gare;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.socle.utils.DateUtils;

/**
 * Filtre le régime d'une tranche à partir d'une liste de gares : on enlève toutes les dates de circulation pour lesquelles aucune gare de la liste
 * n'est desservie.
 * 
 * @author heloise.guillemaud
 *
 */
class FiltreGareDesserteRegimeTranche extends AFiltreGareDesserteTranche {

   /**
    * Liste des dates à enlever du régime de la Tranche résultat
    */
   private List<Date> dateToRemove;

   public FiltreGareDesserteRegimeTranche(List<Gare> gares) {
      super(gares);
      this.dateToRemove = new ArrayList<>();
   }

   @Override
   protected void traiteDesserte(boolean garePresente, Desserte desserte) {
      if (!garePresente) {
         this.dateToRemove.addAll(desserte.getRegime().getListeJours());
      }
   }

   @Override
   protected void traiteTranche(Tranche tranche) {
      for (Date date : this.dateToRemove) {
         DateUtils.removeDayFromDateList(tranche.getRegime().getListeJours(), date);
      }
   }

}
