/**
 * 
 */
package com.avancial.socle.scheduler.service;

import org.quartz.SchedulerException;

/**
 * @author bruno.legloahec
 *
 */
public interface ISocleScheduler {

   /**
    * Supprime toutes les plannifications du scheduler;
    * 
    * @throws SchedulerException
    */
   public void unscheduleAllJobs() throws SchedulerException;

   /**
    * charge et programme toutes les plannifications actives
    * 
    * @throws SchedulerException
    */
   public void scheduleAllActiveJobs() throws SchedulerException;

   /**
    * Recharge les plannifications du scheduler
    * 
    * @throws SchedulerException
    */
   public void reloadlActiveJobs() throws SchedulerException;

}
