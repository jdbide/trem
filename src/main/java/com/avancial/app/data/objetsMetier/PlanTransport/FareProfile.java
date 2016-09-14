package com.avancial.app.data.objetsMetier.PlanTransport;

public class FareProfile extends ASousRegimeTranche {

   private String fareProfileCode;

   private Regime regime;

   public FareProfile() {
      this.fareProfileCode = "";
      this.regime = new Regime();
   }

   public FareProfile(String fareProfileCode, Regime regime) {
      super();
      this.fareProfileCode = fareProfileCode;
      this.regime = regime;
   }

   public FareProfile clone() {
      FareProfile res = new FareProfile();
      res.setFareProfileCode(this.fareProfileCode);
      if (this.regime != null) {
         res.setRegime(this.regime.clone());
      } else {
         res.setRegime(null);
      }
      return res;
   }

   @Override
   public boolean equals(Object obj) {
      FareProfile fareProfile = (FareProfile) obj;
      if (fareProfile.getFareProfileCode().equals(this.fareProfileCode)) {
         return true;
      }
      return false;
   }

   public String getFareProfileCode() {
      return this.fareProfileCode;
   }

   public void setFareProfileCode(String fareProfileCode) {
      this.fareProfileCode = fareProfileCode;
   }

   public Regime getRegime() {
      return this.regime;
   }

   public void setRegime(Regime regime) {
      this.regime = regime;
   }
}
