/**
 * 
 */
package com.avancial.app.data.dto.filtrePlanTransport;

import java.util.List;

/**
 * @author hamza.laterem
 *
 */
public class VoitureDto {
   private String       numeroCoach;
   private String       rameCode;
   private String       coachClass;
   private String       diagCode;
   private int          capacity;
   private List<String> specifications;

   /**
    * contructor
    */
   public VoitureDto() {
   }

   /**
    * @return the numeroCoach
    */
   public String getNumeroCoach() {
      return numeroCoach;
   }

   /**
    * @param numeroCoach
    *           the numeroCoach to set
    */
   public void setNumeroCoach(String numeroCoach) {
      this.numeroCoach = numeroCoach;
   }

   /**
    * @return the rameCode
    */
   public String getRameCode() {
      return rameCode;
   }

   /**
    * @param rameCode
    *           the rameCode to set
    */
   public void setRameCode(String rameCode) {
      this.rameCode = rameCode;
   }

   /**
    * @return the coachClass
    */
   public String getCoachClass() {
      return coachClass;
   }

   /**
    * @param coachClass
    *           the coachClass to set
    */
   public void setCoachClass(String coachClass) {
      this.coachClass = coachClass;
   }

   /**
    * @return the diagCode
    */
   public String getDiagCode() {
      return diagCode;
   }

   /**
    * @param diagCode
    *           the diagCode to set
    */
   public void setDiagCode(String diagCode) {
      this.diagCode = diagCode;
   }

   /**
    * @return the capacity
    */
   public int getCapacity() {
      return capacity;
   }

   /**
    * @param capacity
    *           the capacity to set
    */
   public void setCapacity(int capacity) {
      this.capacity = capacity;
   }

   /**
    * @return the specifications
    */
   public List<String> getSpecifications() {
      return specifications;
   }

   /**
    * @param specifications
    *           the specifications to set
    */
   public void setSpecifications(List<String> specifications) {
      this.specifications = specifications;
   }

}
