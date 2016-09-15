package com.avancial.app.service;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.importMotriceBrut.ImportTMDKAPPEntity;
import com.avancial.socle.persistence.EntityManagerFactoryProvider;
import com.avancial.socle.resources.constants.SOCLE_constants;

public class ImportKappService {

   public ImportTMDKAPPEntity getKht() {
      EntityManager em = EntityManagerFactoryProvider.getInstance().getEntityManagerFactory(SOCLE_constants.PERSISTENCE_UNIT_NAME.toString())
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
