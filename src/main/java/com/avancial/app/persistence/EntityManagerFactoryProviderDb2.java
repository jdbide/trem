package com.avancial.app.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.avancial.app.data.databean.CompagnieEnvironnementEntity;

/**
 * Provisoir.
 * 
 * @author hamza.laterem
 *
 */

public class EntityManagerFactoryProviderDb2 {
   public static final boolean DEBUG = true;
   
   private static final String PERSISTENCE_UNIT_NAME = "PU_db2";
   
   
   /** Point d'accès pour l'instance unique du singleton */
   public static synchronized EntityManagerFactory getInstance(CompagnieEnvironnementEntity compagnieEnvironnementEntity, String username, String password) {
      EntityManagerFactory emf = null;
      Map<String, String> persistUnitConfig = new HashMap<>();      
      try {
         persistUnitConfig.put("javax.persistence.jdbc.driver", compagnieEnvironnementEntity.getDatasource().getDriverClassName());
         persistUnitConfig.put("javax.persistence.jdbc.url", compagnieEnvironnementEntity.getDatasource().getUrl());
         persistUnitConfig.put("javax.persistence.jdbc.user", username);
         persistUnitConfig.put("javax.persistence.jdbc.password", password);

         emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, persistUnitConfig);
         
         if (DEBUG)
            System.out.println(new java.util.Date() + " - EntityManagerProviderDb2 : Création de l'entity manager factory \""+PERSISTENCE_UNIT_NAME+"\" ");
         
         return emf;
      } catch (Exception ex) {
         if (DEBUG)
            System.err.println(new java.util.Date() + " - EntityManagerFactoryProvider : Erreur de création de l'entity manager factory \""+PERSISTENCE_UNIT_NAME+"\"");
         // Ajout d'un log
         ex.printStackTrace();
         throw ex;
      }
            
   }
}
