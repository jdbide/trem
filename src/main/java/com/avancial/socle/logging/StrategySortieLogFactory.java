package com.avancial.socle.logging;

import java.util.HashMap;
import java.util.Map;

import com.avancial.socle.resources.constants.SOCLE_logSortie;

/**
 * Factory qui retourne une instance d'une implémentation de la sortie de logging
 * 
 * @author heloise.guillemaud
 *
 */
public class StrategySortieLogFactory {

   private Map<SOCLE_logSortie, IStrategySortieLog> sortieMap = new HashMap<>();

   /**
    * Constructeur
    */
   public StrategySortieLogFactory() {
      this.sortieMap.put(SOCLE_logSortie.JOB_BDD, new StrategySortieLogJobBdd());
      this.sortieMap.put(SOCLE_logSortie.JOB_DETAIL_BDD, new StrategySortieLogJobDetailBdd());
      this.sortieMap.put(SOCLE_logSortie.CONSOLE, new StrategySortieLogConsole());
   }

   public IStrategySortieLog getStrategySortieLog(SOCLE_logSortie sortie) throws Exception {
      return this.sortieMap.get(sortie);
   }

}
