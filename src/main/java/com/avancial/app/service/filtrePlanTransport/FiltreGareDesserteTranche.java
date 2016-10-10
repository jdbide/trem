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
class FiltreGareDesserteTranche implements IFiltre<Tranche> {

   private List<Gare> gares;

   /**
    * 
    * @param gares
    *           Liste de gares pour filtrer les dessertes
    */
   public FiltreGareDesserteTranche(List<Gare> gares) {
      super();
      this.gares = gares;
   }

   @Override
   public Tranche filtreParCritere(Tranche object) {
      Tranche tranche = object.clone();

      /* Indique si l'on trouve une gare de la liste dans un desserte */
      boolean garePresente = false;
      if (object.getAttributsField(Desserte.class) != null) {
         /* On ré-initialise les données pour la classe à filtrer */
         tranche.getAttributs().remove(Desserte.class);

         /* Nouvelle liste de dessertes */
         List<ASousRegimeTranche> dessertes = new ArrayList<>();

         /* Boucle sur les dessertes à filtrer */
         for (ASousRegimeTranche aSousRegimeTranche : object.getAttributsField(Desserte.class)) {
            garePresente = false;
            Desserte desserte = (Desserte) aSousRegimeTranche;

            /* On cherche dans les gares de la desserte une gare présente dans la liste du filtre */
            for (GareHoraire gareHoraire : desserte.getGareHoraires()) {
               if (this.gares.contains(gareHoraire.getGare())) {
                  garePresente = true;
                  break;
               }
            }

            /* Si au moins une gare est présente dans la liste du filtre, on ajoute la desserte au résultat */
            if (garePresente) {
               dessertes.add(desserte);
            }
         }
         tranche.addAttributsField(dessertes);
      }
      return tranche;
   }

}
