package com.avancial.app.service.filtrePlanTransport;

import java.util.ArrayList;
import java.util.List;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Desserte;
import com.avancial.app.data.objetsMetier.PlanTransport.Gare;
import com.avancial.app.data.objetsMetier.PlanTransport.GareHoraire;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

/**
 * Filtre une tranche à partir d'une liste de gares : on ne retient que les {@link Desserte} qui ont une gare dans leur liste de {@link GareHoraire}.
 * 
 * @author heloise.guillemaud
 *
 */
class FiltreGareDesserteTranche extends AFiltreGareDesserteTranche {

   /**
    * Liste des dessertes pour la tranche résultat
    */
   private List<ASousRegimeTranche> dessertes;

   /**
    * 
    * @param gares
    *           Liste de gares pour filtrer les dessertes
    */
   public FiltreGareDesserteTranche(List<Gare> gares) {
      super(gares);
      this.dessertes = new ArrayList<>();
   }

   @Override
   protected void traiteDesserte(boolean garePresente, Desserte desserte) {
      /* Si au moins une gare est présente dans la liste du filtre, on ajoute la desserte au résultat */
      if (garePresente) {
         this.dessertes.add(desserte);
      }
   }

   @Override
   protected void traiteTranche(Tranche tranche) {
      /* On ré-initialise les données */
      tranche.getAttributs().remove(Desserte.class);
      tranche.addAttributsField(this.dessertes);
   }

}
