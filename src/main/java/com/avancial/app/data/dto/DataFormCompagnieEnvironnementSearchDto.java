/**
 * 
 */
package com.avancial.app.data.dto;

import java.util.List;

/**
 * @author hamza.laterem
 *
 */
public class DataFormCompagnieEnvironnementSearchDto {
   private List<String> trains;
   
   private List<String> tranches;
   
   /**
    * 
    */
   public DataFormCompagnieEnvironnementSearchDto() {
      // TODO Auto-generated constructor stub
   }

   /**
    * @return the trains
    */
   public List<String> getTrains() {
      return trains;
   }

   /**
    * @param trains the trains to set
    */
   public void setTrains(List<String> trains) {
      this.trains = trains;
   }

   /**
    * @return the tranches
    */
   public List<String> getTranches() {
      return tranches;
   }

   /**
    * @param tranches the tranches to set
    */
   public void setTranches(List<String> tranches) {
      this.tranches = tranches;
   }

}
