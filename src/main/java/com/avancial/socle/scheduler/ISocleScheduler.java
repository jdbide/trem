/**
 * 
 */
package com.avancial.socle.scheduler;

import org.quartz.SchedulerException;

/**
 * @author bruno.legloahec
 *
 */
public interface ISocleScheduler {
   public void init() throws SchedulerException;

   public void reload() throws SchedulerException;

}
