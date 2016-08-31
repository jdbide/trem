/**
 * 
 */
package com.avancial.socle.jobs;

import com.avancial.socle.exceptions.impl.ASocleException;

/**
 * @author bruno.legloahec
 *
 */
public interface IJob {
   /**
    * Méthode appelée au lancement du job
    * 
    * @throws ASocleException
    */
   public void executeJob() throws Exception, ASocleException;
}
