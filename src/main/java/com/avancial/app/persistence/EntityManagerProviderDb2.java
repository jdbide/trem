package com.avancial.app.persistence;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Singleton de gestion du EntityManagerFactory et EntityManager Db2
 * Username et password sont récupérés au cours de l'exécution.
 * 
 * Deuxieme solution :
 *  Création d'une annotation pour pu-db2, pour cela :
 *    - Création d'un accés a db2 par profile.
 *    - Les identifiant sauvegardés en bdd
 *    - Le producer récupere les données à partir de la bdd
 * 
 * @author hamza.laterem
 *
 */
@SessionScoped
public class EntityManagerProviderDb2 {
   public static final boolean DEBUG = true;
   private static final String          PERSISTENCE_UNIT_NAME = "pu-db2";
   
   /** Instance unique pré-initialisée */
   private static EntityManagerProviderDb2 instance = null;
   
   private EntityManagerFactory         emf;
   private EntityManager                em;
   
   /** Constructeur privé */
   private EntityManagerProviderDb2(String userName, String password) {
      Map<String, String> props = new HashMap<>();
      props.put("javax.persistence.jdbc.user", userName);
      props.put("javax.persistence.jdbc.password", password);
      
      this.emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, props);
      this.em = this.emf.createEntityManager();
   }
   
   /** Point d'accès pour l'instance unique du singleton */
   public static synchronized EntityManagerProviderDb2 getInstance(String userName, String password) {
      if (instance == null) {         
         instance = new EntityManagerProviderDb2(userName, password);        
      }
      
      return instance;
   }

   /**
    * @return the emf
    */
   public EntityManagerFactory getEmf() {
      return emf;
   }

   /**
    * @return the em
    */
   public EntityManager getEm() {
      return em;
   }
   


}
