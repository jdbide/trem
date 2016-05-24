/**
 * 
 */
package com.avancial.app.service;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.modelmapper.ModelMapper;

import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

/**
 * @author sebastien.benede Classe qui gère toutes les requêtes dans les tables d'import.
 */
public class ImportMotriceService {
   @Inject
   @Socle_PUSocle
   private EntityManager    em;
   @Inject
   private GetEntiteService getEntiteService;

   /**
    * Insère les enregistrements issus de la table DB2 dans la table d'import.
    * 
    * @param tmdavtrDataBeans
    *           liste des enregistrements
    * @param libelleTableMotrice
    *           nom de l'entité
    */
   public void insertAll(List<?> tmdavtrDataBeans, String libelleTableMotrice) {
      ModelMapper modelMapper = new ModelMapper();
      Class<?> importDataBeanClass = null;
      try {
         importDataBeanClass = this.getEntiteService.getClasseEntiteImporFromTableMotrice(libelleTableMotrice);

         if (importDataBeanClass != null) {
            this.em.getTransaction().begin();

            Object object;
            for (int i = 0; i < tmdavtrDataBeans.size(); i++) {
               object = modelMapper.map(tmdavtrDataBeans.get(i), importDataBeanClass);

               this.em.persist(object);
            }

            this.em.flush();
            this.em.getTransaction().commit();
         }
      } catch (ClassNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   public void deleteTable(String entityName) {
      String query = new StringBuilder("DELETE FROM ").append(entityName).toString();
      
      this.em.getTransaction().begin();
      this.em.createQuery(query).executeUpdate();
      this.em.getTransaction().commit();
   }

}
