package com.avancial.app.data.objetsMetier.PlanTransport;

public class GareHoraire {

   private Gare    gare;

   private Horaire horaire;

   public GareHoraire() {
      this.gare = new Gare();
      this.horaire = new Horaire();
   }

   public GareHoraire(Gare gare, Horaire horaire) {
      super();
      this.gare = gare;
      this.horaire = horaire;
   }

   public GareHoraire clone() {
      GareHoraire res = new GareHoraire();
      if (this.gare != null) {
         res.setGare(this.gare.clone());
      }
      if (this.horaire != null) {
         res.setHoraire(this.horaire.clone());
      }
      return res;
   }

   @Override
   public boolean equals(Object obj) {
      GareHoraire gare = (GareHoraire) obj;
      if (gare.getGare().equals(this.gare) && gare.getHoraire().equals(this.horaire)) {
         return true;
      }
      return false;
   }

   public Gare getGare() {
      return this.gare;
   }

   public void setGare(Gare gare) {
      this.gare = gare;
   }

   public Horaire getHoraire() {
      return this.horaire;
   }

   public void setHoraire(Horaire horaire) {
      this.horaire = horaire;
   }

}
