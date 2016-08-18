/**
 * 
 */
package com.avancial.app.resources.constants;

/**
 * @author hamza.laterem
 *
 * Constantes pour les relation objet metier et le nom des fields
 */
public enum APP_Field {
   CodeSat("CodeSat"),
   Desserte("Desserte"),
   Distribution("Distribution"),
   FareProfile("FareProfile"),
   OrigineDestination("OrigineDestination"),
   Repas("Repas"),
   Restriction("Restriction"),
   ServiceABord("ServiceABord"),
   Specification("Specification"),
   TypeEquipement("TypeEquipement"),
   Composition("Composition");

   private String field = "";

   private APP_Field(String pconstant) {
      this.field = pconstant;
   }

   @Override
   public String toString() {
      return this.field;
   }

}
