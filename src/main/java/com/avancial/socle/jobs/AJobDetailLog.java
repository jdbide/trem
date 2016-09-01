/**
 * 
 */
package com.avancial.socle.jobs;

import java.util.Date;

import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.logging.ALogBean;
import com.avancial.socle.logging.LogJobDetailBean;

/**
 * @author bruno.legloahec
 *
 */
public abstract class AJobDetailLog extends AJobLog {

   protected ALogBean logJobDetailBean;

   /**
    * Constructeur
    */
   public AJobDetailLog() {
      this.logJobDetailBean = new LogJobDetailBean();

   }

   @Override
   public void executeJob() throws Exception, ASocleException{
      ((LogJobDetailBean) this.logJobDetailBean).setDate(new Date());

   }

}
