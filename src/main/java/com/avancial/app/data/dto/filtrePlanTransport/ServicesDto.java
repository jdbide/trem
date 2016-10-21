/**
 * 
 */
package com.avancial.app.data.dto.filtrePlanTransport;

import java.util.List;

/**
 * @author hamza.laterem
 *
 */
public class ServicesDto {
   private String codeEquipement;
   List<ServiceABoardDto> services;
   List<MealServiceDto> mealServices;

   public ServicesDto() {
   }

   /**
    * @return the codeEquipement
    */
   public String getCodeEquipement() {
      return codeEquipement;
   }

   /**
    * @param codeEquipement the codeEquipement to set
    */
   public void setCodeEquipement(String codeEquipement) {
      this.codeEquipement = codeEquipement;
   }

   /**
    * @return the services
    */
   public List<ServiceABoardDto> getServices() {
      return services;
   }

   /**
    * @param services the services to set
    */
   public void setServices(List<ServiceABoardDto> services) {
      this.services = services;
   }

   /**
    * @return the mealServices
    */
   public List<MealServiceDto> getMealServices() {
      return mealServices;
   }

   /**
    * @param mealServices the mealServices to set
    */
   public void setMealServices(List<MealServiceDto> mealServices) {
      this.mealServices = mealServices;
   }

}