/**
 * 
 */
package com.avancial.app.data.dto.filtrePlanTransport;

/**
 * @author hamza.laterem
 *
 */
public class RMCodeDto {
   private String codeRame1;
   private String codeRame2;
   private String fareProfileCode;

   /**
    * constructeur vide
    */
   public RMCodeDto() {
   }

   /**
    * @return the codeRame1
    */
   public String getCodeRame1() {
      return codeRame1;
   }

   /**
    * @param codeRame1
    *           the codeRame1 to set
    */
   public void setCodeRame1(String codeRame1) {
      this.codeRame1 = codeRame1;
   }

   /**
    * @return the codeRame2
    */
   public String getCodeRame2() {
      return codeRame2;
   }

   /**
    * @param codeRame2
    *           the codeRame2 to set
    */
   public void setCodeRame2(String codeRame2) {
      this.codeRame2 = codeRame2;
   }

   /**
    * @return the fareProfileCode
    */
   public String getFareProfileCode() {
      return fareProfileCode;
   }

   /**
    * @param fareProfileCode
    *           the fareProfileCode to set
    */
   public void setFareProfileCode(String fareProfileCode) {
      this.fareProfileCode = fareProfileCode;
   }

}
