package com.avancial.socle.logging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.resources.constants.SOCLE_logSortie;

/**
 * Implémentation de l'interface de Logger, elle contient une liste de stratégies de logging déterminant comment seront enregistrés les logs soumis par cette instance.
 * 
 * @author heloise.guillemaud
 *
 */
public class Logger implements ILogger {

   /**
    * Collection des sorties vers lesquelles le Logger doit envoyer/ enregistrer le log
    */
   private Map<SOCLE_logSortie, IStrategySortieLog> sortiesLogger;

   /**
    * Liste des sorties qui ne sont actives que pour le prochain log
    */
   private List<SOCLE_logSortie>                    sortiesActivees;

   /**
    * Liste des sorties qui sont désactivées, juste sur le prochain log
    */
   private List<SOCLE_logSortie>                    sortiesDesactivees;

   private StrategySortieLogFactory                 strategySortieLogFactory;

   /**
    * Une fois instancié, il faudra ajouter une stratégie de log ({@link #ajouterSortie(SOCLE_logSortie)}} Une stratégie de log implémente la méthode log qui demande un objet de type {@link ALogBean}<br/>
    * Certaines stratégies peuvent travailler avec différents objets de ce type ({@link StrategySortieLogConsole}<br/>
    * D'autres ont besoin d'un bean particulier sinon elle ne produiront aucune sortie ({@link StrategySortieLogJobBdd}<br/>
    * Effectivement elles testent le type de bean avant de logger.
    *
    * {@code }
    */
   public Logger() {
      this.sortiesLogger = new HashMap<>();
      this.sortiesActivees = new ArrayList<>();
      this.sortiesDesactivees = new ArrayList<>();
      this.strategySortieLogFactory = new StrategySortieLogFactory();
   }

   /**
    * Log sur toutes les sorties demandées. <br/>
    * Certaines stratégies de logs ne fonctionneront qu'avec un bean particulier.
    * 
    * @param logBean
    *           Objet à logger
    * @throws Exception
    * @throws ASocleException
    */
   @Override
   public void log(ALogBean logBean) throws Exception, ASocleException {
      /* Log sur toutes les sorties */
      for (IStrategySortieLog sortie : this.sortiesLogger.values()) {
         sortie.log(logBean);
      }

      /* Remettre les sorties désactivées pour ce log */
      for (SOCLE_logSortie sortieLog : this.sortiesDesactivees) {
         this.sortiesLogger.put(sortieLog, this.strategySortieLogFactory.getStrategySortieLog(sortieLog));
      }

      /* Enlever les sorties activées pour ce log */
      for (SOCLE_logSortie sortieLog : this.sortiesActivees) {
         this.sortiesLogger.remove(sortieLog);
      }
   }

   @Override
   public void ajouterSortie(SOCLE_logSortie sortie) {
      try {
         this.sortiesLogger.put(sortie, this.strategySortieLogFactory.getStrategySortieLog(sortie));
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   @Override
   public void enleverSortie(SOCLE_logSortie sortie) {
      this.sortiesLogger.remove(sortie);
   }

   @Override
   public void activerSortie(SOCLE_logSortie sortie) throws Exception {
      /* On regarde si la sortie ne serait pas déjà présente */
      IStrategySortieLog strategieSortie = this.sortiesLogger.get(sortie);
      /*
       * Si la sortie est déjà présente dans la map, elle ne doit pas être enlevée après le prochain log, donc on ne l'ajoute pas dans la liste des sorties activées
       */
      if (strategieSortie == null) {
         this.sortiesActivees.add(sortie);
         this.sortiesLogger.put(sortie, this.strategySortieLogFactory.getStrategySortieLog(sortie));
      }
   }

   @Override
   public void desactiverSortie(SOCLE_logSortie sortie) {
      /* On regarde si la sortie set bien présente dans la map */
      IStrategySortieLog strategieSortie = this.sortiesLogger.get(sortie);
      /*
       * Si la sortie n'est pas présente dans la map, elle ne doit pas être remise après le prochain log, donc on ne l'ajoute pas dans la liste des sorties désactivées
       */
      if (strategieSortie != null) {
         this.sortiesDesactivees.add(sortie);
         this.sortiesLogger.remove(sortie);
      }
   }

}
