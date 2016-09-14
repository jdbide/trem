package com.avancial.socle.logging;

/**
 * Log donnant un détail d'exécution d'un Job.<br/>
 * <b>ATTENTION</b> en cas de Log en Base De Données, le {@link LogJobBean} lié au Job en cours doit avoir déjà été enregistré (déjà loggé).
 * 
 * @author heloise.guillemaud
 *
 */
public class LogJobDetailBean extends ALogBean {

   protected String messageException;

   public String getMessageException() {
      return this.messageException;
   }

   public void setMessageException(String messageException) {
      this.messageException = messageException;
   }

}
