/**
 * 
 */
package com.avancial.app.data.dto.filtrePlanTransport;

import java.util.Date;

/**
 * @author hamza.laterem
 *
 */
public class StopsDto {
   private String cityCode;
   private String libelle;
   private Date   arrivalTime;
   private Date   DepartureTime;
   private String offForbidden;
   private String onForbidden;

   public StopsDto() {
      super();
   }

   /**
    * @return the cityCode
    */
   public String getCityCode() {
      return cityCode;
   }

   /**
    * @param cityCode
    *           the cityCode to set
    */
   public void setCityCode(String cityCode) {
      this.cityCode = cityCode;
   }

   /**
    * @return the libelle
    */
   public String getLibelle() {
      return libelle;
   }

   /**
    * @param libelle
    *           the libelle to set
    */
   public void setLibelle(String libelle) {
      this.libelle = libelle;
   }

   /**
    * @return the arrivalTime
    */
   public Date getArrivalTime() {
      return arrivalTime;
   }

   /**
    * @param arrivalTime
    *           the arrivalTime to set
    */
   public void setArrivalTime(Date arrivalTime) {
      this.arrivalTime = arrivalTime;
   }

   /**
    * @return the departureTime
    */
   public Date getDepartureTime() {
      return DepartureTime;
   }

   /**
    * @param departureTime
    *           the departureTime to set
    */
   public void setDepartureTime(Date departureTime) {
      DepartureTime = departureTime;
   }

   /**
    * @return the offForbidden
    */
   public String getOffForbidden() {
      return offForbidden;
   }

   /**
    * @param offForbidden
    *           the offForbidden to set
    */
   public void setOffForbidden(String offForbidden) {
      this.offForbidden = offForbidden;
   }

   /**
    * @return the onForbidden
    */
   public String getOnForbidden() {
      return onForbidden;
   }

   /**
    * @param onForbidden
    *           the onForbidden to set
    */
   public void setOnForbidden(String onForbidden) {
      this.onForbidden = onForbidden;
   }

}
