package com.avancial.socle.logging;

import java.io.Serializable;
import java.util.Date;

import com.avancial.socle.resources.constants.SOCLE_logSeverite;

/**
 * Classe mère pour les objets qui représentent ce qui va être loggé. De base, elle contient une sévérité, un message et une date.
 * 
 * @author heloise.guillemaud
 *
 */
public abstract class ALogBean implements Serializable {

   /**
    * 
    */
   private static final long   serialVersionUID = 1839343190044180029L;

   /**
    * Sévérité du log, sur 4 niveaux - Informatif - Attention - Erreur - Mode debug
    */
   protected SOCLE_logSeverite severite;

   /**
    * Message lié au log
    */
   protected String            message;

   /**
    * Date correspondant au moment de l'enregistrement
    */
   protected Date              date;

   public SOCLE_logSeverite getSeverite() {
      return this.severite;
   }

   /**
    * 
    * @param severite
    */
   public void setSeverite(SOCLE_logSeverite severite) {
      this.severite = severite;
   }

   public String getMessage() {
      return this.message;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public Date getDate() {
      return this.date;
   }

   public void setDate(Date date) {
      this.date = date;
   }

}
