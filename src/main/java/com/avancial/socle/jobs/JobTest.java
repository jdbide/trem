/**
 * 
 */
package com.avancial.socle.jobs;

import java.util.Date;

import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.logging.LogJobDetailBean;
import com.avancial.socle.logging.StrategySortieLogConsole;
import com.avancial.socle.resources.constants.SOCLE_logSeverite;
import com.avancial.socle.resources.constants.SOCLE_logSortie;

/**
 * @author bruno.legloahec
 *
 */
public class JobTest extends AJobDetailLog {

   @Override
   public void executeJob() {
      ((LogJobDetailBean) this.logJobDetailBean).setMessage("Etape 1/2");
      ((LogJobDetailBean) this.logJobDetailBean).setSeverite(SOCLE_logSeverite.INFO);
      ((LogJobDetailBean) this.logJobDetailBean).setDate(new Date());
      try {
         this.logger.log(this.logJobDetailBean);
         ((LogJobDetailBean) this.logJobDetailBean).setMessage("Etape 2/2");
         ((LogJobDetailBean) this.logJobDetailBean).setDate(new Date());
         this.logger.log(this.logJobDetailBean);

      } catch (Exception | ASocleException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      System.out.println("Job exécuté");
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.socle.jobs.AJobLog#initLogStrategy()
    */
   @Override
   public void initLogStrategy() {

      this.logger.ajouterSortie(SOCLE_logSortie.JOB_DETAIL_BDD);
   }

}
