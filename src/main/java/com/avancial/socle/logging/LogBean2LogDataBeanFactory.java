package com.avancial.socle.logging;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory retournant la classe permettant de convertir un LogBean en DataBean
 * DAO qui va pouvoir enregistrer un log en BDD, à partir de l'objet de Log (qui
 * implémente ALogBean)
 * 
 * @author bruno.legloahec
 *
 */
public class LogBean2LogDataBeanFactory {

   private static Map<Class<?>, Class<?>> converterMap = new HashMap<>();
   static {
      converterMap.put(LogJobBean.class, LogJobBean2LogJobDataBean.class);
      // daoMap.put(LogJobDetailBean.class, LogJobDetailDao.class);
   }

   /**
    * @param logBean
    * @return une instance de DAO
    * @throws Exception
    */
   public static Class<ILogBean2LogDataBeanConverter> getConverter(ALogBean logBean) throws Exception {
      @SuppressWarnings("unchecked")
      Class<ILogBean2LogDataBeanConverter> resClasse = (Class<ILogBean2LogDataBeanConverter>) converterMap.get(logBean.getClass());

      // if (resClasse != null) {
      return resClasse;
      // }

      // return null;
   }

}
