package com.avancial.app.service.traiteMotriceRegime;

public class KeyMotriceRegimeSpecificity {

   private String voiture;
   private String regime;
   private String codeDiagramme;
   private String stateCode;
   
   

   /**
    * 
    */
   public KeyMotriceRegimeSpecificity() {
      super();
   }

   /**
    * @param voiture
    * @param regime
    * @param codeDiagramme
    * @param stateCode
    */
   public KeyMotriceRegimeSpecificity(String voiture, String regime, String codeDiagramme, String stateCode) {
      super();
      this.voiture = voiture;
      this.regime = regime;
      this.codeDiagramme = codeDiagramme;
      this.stateCode = stateCode;
   }

   /**
    * @return the voiture
    */
   public String getVoiture() {
      return this.voiture;
   }

   /**
    * @param voiture
    *           the voiture to set
    */
   public void setVoiture(String voiture) {
      this.voiture = voiture;
   }

   /**
    * @return the regime
    */
   public String getRegime() {
      return this.regime;
   }

   /**
    * @param regime
    *           the regime to set
    */
   public void setRegime(String regime) {
      this.regime = regime;
   }

   /**
    * @return the codeDiagramme
    */
   public String getCodeDiagramme() {
      return this.codeDiagramme;
   }

   /**
    * @param codeDiagramme
    *           the codeDiagramme to set
    */
   public void setCodeDiagramme(String codeDiagramme) {
      this.codeDiagramme = codeDiagramme;
   }

   /**
    * @return the stateCode
    */
   public String getStateCode() {
      return this.stateCode;
   }

   /**
    * @param stateCode
    *           the stateCode to set
    */
   public void setStateCode(String stateCode) {
      this.stateCode = stateCode;
   }

}
