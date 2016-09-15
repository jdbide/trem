/**
 * 
 */
package com.avancial.socle.traitement;

import java.util.Hashtable;
import java.util.Map;

/**
 * @author hamza.laterem
 *
 */
public class Task {
   private static Map <Long, ReponseTraitement> tasks = new Hashtable<Long, ReponseTraitement>();

   public static Boolean addTask (Long idTask, String msg) {
      if (tasks.containsKey(idTask))
         return false;

      ReponseTraitement reponseTraitement = new ReponseTraitement();
      reponseTraitement.setEndTraitement(false);
      reponseTraitement.setTraitementOk(false);
      reponseTraitement.setLastMsg("Début de traitement");

      tasks.put(idTask, reponseTraitement);

      return true;
   }

   public static void setMsgTask (Long idTask, String msg) {
      if (!tasks.containsKey(idTask))
         return;

      tasks.get(idTask).setLastMsg(msg);
   }
   
   public static void finishOkTask (Long idTask) {
      if (!tasks.containsKey(idTask))
         return;

      tasks.get(idTask).setLastMsg("Traitement terminé avec succès");
      tasks.get(idTask).setEndTraitement(true);
      tasks.get(idTask).setTraitementOk(true);
   }

   public static void finishKoTask (Long idTask) {
      if (!tasks.containsKey(idTask))
         return;

      tasks.get(idTask).setLastMsg("Traitement terminé en échec");
      tasks.get(idTask).setEndTraitement(true);
      tasks.get(idTask).setTraitementOk(false);
   }

   public static void finishKoTask (Long idTask, String msgErr) {
      if (!tasks.containsKey(idTask))
         return;

      tasks.get(idTask).setLastMsg(msgErr);
      tasks.get(idTask).setEndTraitement(true);
      tasks.get(idTask).setTraitementOk(false);
      tasks.get(idTask).setMsgErr(msgErr);
   }

   public static ReponseTraitement getReponseTask (Long idTask) {
      return tasks.get(idTask);
   }

   public static void removeTask (Long idTask) {
      if (tasks.containsKey(idTask))
         tasks.remove(idTask);
   }
   
   public static Boolean isActiveTask() {
      return !tasks.isEmpty();
   }
}
