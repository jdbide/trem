package com.avancial.app.service;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.importMotriceBrut.ImportTMDKAPPEntity;
import com.avancial.app.resources.constants.APP_Const;
import com.avancial.socle.persistence.EntityManagerFactoryProvider;

public class ImportKappService {

   public ImportTMDKAPPEntity getKht() {
      EntityManager em = EntityManagerFactoryProvider.getInstance().getEntityManagerFactory(APP_Const.PERSISTENCE_UNIT_NAME.toString())
            .createEntityManager();
      ImportTMDKAPPEntity kappEntity = null;
      try {
         Query query = em.createNamedQuery("ImportTMDKAPP.getKht", ImportTMDKAPPEntity.class);
         kappEntity = (ImportTMDKAPPEntity) query.getSingleResult();
      } finally {
         em.close();
      }
      return kappEntity;
   
   }
}
