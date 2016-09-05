/**
 * 
 */
package com.avancial.app.resources.constants;

/**
 * @author hamza.laterem
 *
 * Constantes pour les paths
 */
public enum APP_Directory {
   PathRapportDiff("APP_Path_RapportDiff");
   
   private String directory ="";
   
   private APP_Directory(String directory) {
      this.directory = directory;
   }
   
   @Override
   public String toString() {
      return this.directory;
   }
}
