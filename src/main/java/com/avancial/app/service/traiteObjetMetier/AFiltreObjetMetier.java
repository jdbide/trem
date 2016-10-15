package com.avancial.app.service.traiteObjetMetier;

import java.util.Date;

import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.service.traiteMotriceRegime.IFiltreDateRegime;

public abstract class AFiltreObjetMetier implements IFiltreDateRegime {

   private Date dateDebut = null;
   private Date dateFin   = null;

   @Override
   public void setFiltreDate(Date dateDebut, Date dateFin) {
      this.dateDebut = dateDebut;
      this.dateFin = dateFin;
   }

   /**
    * Teste l'ajout d'un objet dans le plan de transport en fonction de la période de filtre.
    * 
    * @param regime
    *           Regime de l'objet à tester
    * @return {@code true} si et seulement si l'objet doit être ajouté au plan de transport
    */
   protected boolean filtreDateAjout(Regime regime) {
      return regime != null && !regime.getListeJours().isEmpty();
   }

   public Date getDateDebut() {
      return this.dateDebut;
   }

   public Date getDateFin() {
      return this.dateFin;
   }

}
