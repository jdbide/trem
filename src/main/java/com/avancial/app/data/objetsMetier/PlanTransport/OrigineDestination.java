package com.avancial.app.data.objetsMetier.PlanTransport;

public class OrigineDestination extends ASousRegimeTranche {

   private Gare   origine;

   private Gare   destination;

   private Regime regime;

   public OrigineDestination() {
      this.origine = new Gare();
      this.destination = new Gare();
      this.regime = new Regime();
   }

   public OrigineDestination(Gare origine, Gare destination, Regime regime) {
      super();
      this.origine = origine;
      this.destination = destination;
      this.regime = regime;
   }

   public OrigineDestination clone() {
      OrigineDestination res = new OrigineDestination();
      if (this.destination != null) {
         res.setDestination(this.destination.clone());
      } else {
         res.setDestination(null);
      }
      if (this.origine != null) {
         res.setOrigine(this.destination.clone());
      } else {
         res.setOrigine(null);
      }
      if (this.regime != null) {
         res.setRegime(this.regime.clone());
      } else {
         res.setRegime(null);
      }

      return res;
   }

   @Override
   public boolean equals(Object obj) {
      OrigineDestination od = (OrigineDestination) obj;
      if (od.getOrigine().equals(this.origine) && od.getDestination().equals(this.destination)) {
         return true;
      }
      return false;
   }

   public Gare getOrigine() {
      return this.origine;
   }

   public void setOrigine(Gare origine) {
      this.origine = origine;
   }

   public Gare getDestination() {
      return this.destination;
   }

   public void setDestination(Gare destination) {
      this.destination = destination;
   }

   public Regime getRegime() {
      return this.regime;
   }

   public void setRegime(Regime regime) {
      this.regime = regime;
   }
}
