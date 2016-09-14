/**
 * 
 */
package com.avancial.app.resources.constants;

/**
 * @author hamza.laterem
 *
 */
public enum APP_Const {
   PERSISTENCE_UNIT_NAME("PU_socle");
   
   private String constante ="";
   
   private APP_Const(String constante) {
      this.constante = constante;
   }
   
   @Override
   public String toString() {
      return this.constante;
   }
}
