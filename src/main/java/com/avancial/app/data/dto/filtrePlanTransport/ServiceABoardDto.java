/**
 * 
 */
package com.avancial.app.data.dto.filtrePlanTransport;

/**
 * @author hamza.laterem
 *
 */
public class ServiceABoardDto {
   private String libelle;
   private String code;
   private String classe;
   private String manualAuto;
   private String origine;
   private String destination;
   
   public ServiceABoardDto() {
   }

   /**
    * @return the libelle
    */
   public String getLibelle() {
      return libelle;
   }

   /**
    * @param libelle the libelle to set
    */
   public void setLibelle(String libelle) {
      this.libelle = libelle;
   }

   /**
    * @return the code
    */
   public String getCode() {
      return code;
   }

   /**
    * @param code the code to set
    */
   public void setCode(String code) {
      this.code = code;
   }

   /**
    * @return the classe
    */
   public String getClasse() {
      return classe;
   }

   /**
    * @param classe the classe to set
    */
   public void setClasse(String classe) {
      this.classe = classe;
   }

   /**
    * @return the manualAuto
    */
   public String getManualAuto() {
      return manualAuto;
   }

   /**
    * @param manualAuto the manualAuto to set
    */
   public void setManualAuto(String manualAuto) {
      this.manualAuto = manualAuto;
   }

   /**
    * @return the origine
    */
   public String getOrigine() {
      return origine;
   }

   /**
    * @param origine the origine to set
    */
   public void setOrigine(String origine) {
      this.origine = origine;
   }

   /**
    * @return the destination
    */
   public String getDestination() {
      return destination;
   }

   /**
    * @param destination the destination to set
    */
   public void setDestination(String destination) {
      this.destination = destination;
   }
   
}
