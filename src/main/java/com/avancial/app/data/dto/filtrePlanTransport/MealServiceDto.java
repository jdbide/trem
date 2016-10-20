/**
 * 
 */
package com.avancial.app.data.dto.filtrePlanTransport;

/**
 * @author hamza.laterem
 *
 */
public class MealServiceDto {
   private String numeroCoach;
   private String mealType;
   private String mealCode;
   private String type;
   private String starting;
   private String  ending;
   
   /**
    * constructor vide
    */
   public MealServiceDto() {
      super();
   }

   /**
    * @return the numeroCoach
    */
   public String getNumeroCoach() {
      return numeroCoach;
   }

   /**
    * @param numeroCoach the numeroCoach to set
    */
   public void setNumeroCoach(String numeroCoach) {
      this.numeroCoach = numeroCoach;
   }

   /**
    * @return the mealType
    */
   public String getMealType() {
      return mealType;
   }

   /**
    * @param mealType the mealType to set
    */
   public void setMealType(String mealType) {
      this.mealType = mealType;
   }

   /**
    * @return the mealCode
    */
   public String getMealCode() {
      return mealCode;
   }

   /**
    * @param mealCode the mealCode to set
    */
   public void setMealCode(String mealCode) {
      this.mealCode = mealCode;
   }

   /**
    * @return the type
    */
   public String getType() {
      return type;
   }

   /**
    * @param type the type to set
    */
   public void setType(String type) {
      this.type = type;
   }

   /**
    * @return the starting
    */
   public String getStarting() {
      return starting;
   }

   /**
    * @param starting the starting to set
    */
   public void setStarting(String starting) {
      this.starting = starting;
   }

   /**
    * @return the ending
    */
   public String getEnding() {
      return ending;
   }

   /**
    * @param ending the ending to set
    */
   public void setEnding(String ending) {
      this.ending = ending;
   }
   
   
}
