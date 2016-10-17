/**
 * 
 */
package com.avancial.app.data.dto;

import java.util.List;

import com.avancial.app.data.databean.importMotrice.MotriceRefEqpTypeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefGareEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefODEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefRameCodeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefTospEntity;

/**
 * @author hamza.laterem
 *
 */
public class DataFormCompagnieSearchDto {
   private List<MotriceRefODEntity> ods;
   private List<MotriceRefGareEntity> stops;
   private List<MotriceRefTospEntity> tosps;
   private List<MotriceRefRameCodeEntity> rms;
   private List<MotriceRefEqpTypeEntity> equipements;
   /**
    * 
    */
   public DataFormCompagnieSearchDto() {
      // TODO Auto-generated constructor stub
   }
   /**
    * @return the ods
    */
   public List<MotriceRefODEntity> getOds() {
      return ods;
   }
   /**
    * @param ods the ods to set
    */
   public void setOds(List<MotriceRefODEntity> ods) {
      this.ods = ods;
   }
   /**
    * @return the stops
    */
   public List<MotriceRefGareEntity> getStops() {
      return stops;
   }
   /**
    * @param stops the stops to set
    */
   public void setStops(List<MotriceRefGareEntity> stops) {
      this.stops = stops;
   }
   /**
    * @return the tosps
    */
   public List<MotriceRefTospEntity> getTosps() {
      return tosps;
   }
   /**
    * @param tosps the tosps to set
    */
   public void setTosps(List<MotriceRefTospEntity> tosps) {
      this.tosps = tosps;
   }
   /**
    * @return the rms
    */
   public List<MotriceRefRameCodeEntity> getRms() {
      return rms;
   }
   /**
    * @param rms the rms to set
    */
   public void setRms(List<MotriceRefRameCodeEntity> rms) {
      this.rms = rms;
   }
   /**
    * @return the equipements
    */
   public List<MotriceRefEqpTypeEntity> getEquipements() {
      return equipements;
   }
   /**
    * @param equipements the equipements to set
    */
   public void setEquipements(List<MotriceRefEqpTypeEntity> equipements) {
      this.equipements = equipements;
   }

}
