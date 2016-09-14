package com.avancial.socle.logging;

import java.util.HashMap;
import java.util.Map;

import com.avancial.socle.data.controller.dao.AbstractDao;
import com.avancial.socle.data.controller.dao.LogJobDao;
import com.avancial.socle.data.controller.dao.LogJobDetailDao;

/**
 * Factory retournant le DAO qui va pouvoir enregistrer un log en BDD, à partir
 * de l'objet de Log (qui implémente ALogBean)
 * 
 * @author heloise.guillemaud
 *
 */
public class LogDaoFactory {

   private static Map<Class<?>, Class<?>> daoMap = new HashMap<>();
   static {
      daoMap.put(LogJobBean.class, LogJobDao.class);
      daoMap.put(LogJobDetailBean.class, LogJobDetailDao.class);
   }

   /**
    * @param logBean
    * @return une instance de DAO
    * @throws Exception
    */
   public static AbstractDao getLogDao(ALogBean logBean) throws Exception {
      Class<?> resClasse = daoMap.get(logBean.getClass());

      if (resClasse != null) {
         return (AbstractDao) resClasse.newInstance();
      }

      return null;
   }

}
