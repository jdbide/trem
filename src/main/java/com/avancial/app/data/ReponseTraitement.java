/**
 * 
 */
package com.avancial.app.data;

/**
 * @author hamza.laterem
 *
 */
public class ReponseTraitement {
   public Boolean endTraitement;
   public Boolean traitementOk;
   public String lastMsg;
   public String msgErr;
   
   /**
    * @return the endTraitement
    */
   public Boolean getEndTraitement() {
      return endTraitement;
   }
   /**
    * @param endTraitement the endTraitement to set
    */
   public void setEndTraitement(Boolean endTraitement) {
      this.endTraitement = endTraitement;
   }
   /**
    * @return the traitementOk
    */
   public Boolean getTraitementOk() {
      return traitementOk;
   }
   /**
    * @param traitementOk the traitementOk to set
    */
   public void setTraitementOk(Boolean traitementOk) {
      this.traitementOk = traitementOk;
   }
   /**
    * @return the lastMsg
    */
   public String getLastMsg() {
      return lastMsg;
   }
   /**
    * @param lastMsg the lastMsg to set
    */
   public void setLastMsg(String lastMsg) {
      this.lastMsg = lastMsg;
   }
   /**
    * @return the msgErr
    */
   public String getMsgErr() {
      return msgErr;
   }
   /**
    * @param msgErr the msgErr to set
    */
   public void setMsgErr(String msgErr) {
      this.msgErr = msgErr;
   }
}
