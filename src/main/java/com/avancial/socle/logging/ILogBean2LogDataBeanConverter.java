/**
 * 
 */
package com.avancial.socle.logging;

import com.avancial.socle.data.model.databean.AbstractDataBean;

/**
 * @author bruno
 *
 */
public interface ILogBean2LogDataBeanConverter {

   /**
    * Permet de renseigner un dataBean à partir d'un logBean
    * 
    * @param bean
    * @param dataBean
    */
   public AbstractDataBean convert(ALogBean bean);

}
