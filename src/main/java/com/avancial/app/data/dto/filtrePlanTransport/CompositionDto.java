/**
 * 
 */
package com.avancial.app.data.dto.filtrePlanTransport;

import java.util.List;

/**
 * @author hamza.laterem
 *
 */
public class CompositionDto {
   private Integer          capacity = 0;
   private List<VoitureDto> voitures;

   /**
    * constructeur vide
    */
   public CompositionDto() {

   }

   /**
    * @return the capacity
    */
   public Integer getCapacity() {
      return capacity;
   }

   /**
    * @param capacity the capacity to set
    */
   public void setCapacity(Integer capacity) {
      this.capacity = capacity;
   }

   /**
    * @return the voitures
    */
   public List<VoitureDto> getVoitures() {
      return voitures;
   }

   /**
    * @param voitures the voitures to set
    */
   public void setVoitures(List<VoitureDto> voitures) {
      this.voitures = voitures;
   }
}
