package com.avancial.app.data.objetsMetier.PlanTransport;

public class Tosp extends ASousRegimeTranche {
   private String oureCode;

   private Regime regime;

   public Tosp() {
      this.oureCode = "";
      this.regime = new Regime();
   }

   public Tosp clone() {
      Tosp res = new Tosp();
      res.setOureCode(this.oureCode);
      if (this.regime != null) {
         res.setRegime(this.regime.clone());
      } else {
         res.setRegime(null);
      }

      return res;
   }

   public Tosp(String oureCode, Regime regime) {
      this.oureCode = oureCode;
      this.regime = regime;
   }

   public String getOureCode() {
      return this.oureCode;
   }

   public void setOureCode(String oureCode) {
      this.oureCode = oureCode;
   }

   public Regime getRegime() {
      return this.regime;
   }

   public void setRegime(Regime regime) {
      this.regime = regime;
   }

   @Override
   public boolean equals(Object obj) {
      Tosp oureCode = (Tosp) obj;
      if (oureCode.getOureCode().equals(this.oureCode)) {
         return true;
      }
      return false;
   }

}
