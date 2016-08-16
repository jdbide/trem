package com.avancial.app.data.objetsMetier.PlanTransport;

public class Specification extends ASousRegimeTranche {
   private Voiture               voiture;
   private EnumEtatSpecification etat;
   private Regime                regime;

   /**
    * @param voiture
    * @param etat
    * @param regime
    */
   public Specification(Voiture voiture, EnumEtatSpecification etat, Regime regime) {
      super();
      this.voiture = voiture;
      this.etat = etat;
      this.regime = regime;
   }

   public Specification() {
      this.voiture = new Voiture();
      this.etat = EnumEtatSpecification.fermer;
      this.regime = new Regime();
   }

   public Specification clone() {
      Specification res = new Specification();
      res.setEtat(this.etat);
      if (this.regime != null) {
         res.setRegime(this.regime.clone());
      } else {
         res.setRegime(null);
      }
      if (this.voiture != null) {
         res.setVoiture(this.voiture.clone());
      } else {
         res.setVoiture(null);
      }
      return res;
   }

   @Override
   public boolean equals(Object obj) {
      Specification specification = (Specification) obj;
      return this.getVoiture().equals(specification.getVoiture()) && this.getEtat().equals(specification.getEtat());
   }

   /**
    * @return the voiture
    */
   public Voiture getVoiture() {
      return this.voiture;
   }

   /**
    * @param voiture
    *           the voiture to set
    */
   public void setVoiture(Voiture voiture) {
      this.voiture = voiture;
   }

   /**
    * @return the etat
    */
   public EnumEtatSpecification getEtat() {
      return this.etat;
   }

   /**
    * @param etat
    *           the etat to set
    */
   public void setEtat(EnumEtatSpecification etat) {
      this.etat = etat;
   }

   /**
    * @return the regime
    */
   public Regime getRegime() {
      return this.regime;
   }

   /**
    * @param regime
    *           the regime to set
    */
   public void setRegime(Regime regime) {
      this.regime = regime;
   }

}
