/**
 * 
 */
package com.avancial.socle.jobs;

/**
 * @author bruno.legloahec
 *
 */
public class JobTest extends AJobDetailLog {

   /**
    * 
    */
   private static final long serialVersionUID = -883945079447613530L;

   @Override
   public void executeJob() {
      // this.logJobDetailBean.setMessage("Etape 1/2");
      try {
         this.log("Etape 1/2");
         this.log("Etape 2/2");

      } catch (Exception e) {
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

      // this.logger.ajouterSortie(SOCLE_logSortie.JOB_DETAIL_BDD);
   }

}
