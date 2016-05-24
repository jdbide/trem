package com.avancial.app.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.modelmapper.ModelMapper;

import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

/**
 * Service permettant de gérer les tables d'import
 * 
 * @author gabriel.gagnier
 *
 */
public class TremasImportService {
   @Inject
   @Socle_PUSocle
   private EntityManager em;

   @Inject
   private EntiteService entiteService;

   /**
    * Renvoie les données contenues dans la table entityName (exemple: "TMDAVTR") en JSON
    * 
    * @param entityName
    * @return
    * @throws ClassNotFoundException
    */
   @SuppressWarnings("unchecked")
   public JSONObject getDataTable(String entityName) throws ClassNotFoundException {
      Class<?> entity = null;

      try {
         entity = entiteService.getClasseEntiteImporFromTableMotrice(entityName);
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }

      List<String> colNames = new ArrayList<>();
      for (int i = 0; i < entity.getDeclaredFields().length; i++) {
         colNames.add(entity.getDeclaredFields()[i].getName());
      }

      Query query = this.em.createNamedQuery(new StringBuilder(this.entiteService.getNomEntiteImportFromNomEntiteMotrice(entityName)).append(".getAll").toString(), this.entiteService.getClasseEntiteImporFromTableMotrice(entityName));
      List<Object> tmdavtrDataBeans = query.getResultList();
      JSONArray datas = new JSONArray();
      datas.addAll(tmdavtrDataBeans);

      JSONObject retour = new JSONObject();
      retour.put("cols", colNames);
      retour.put("dataset", datas);
      return retour;
   }

   /**
    * Insère les enregistrements issus de la table DB2 dans la table d'import.
    * 
    * @param entityList
    *           liste des enregistrements
    * @param libelleTableMotrice
    *           nom de l'entité
    * @throws ClassNotFoundException
    */
   public void insertAll(List<?> entityList, String libelleTableMotrice) throws ClassNotFoundException {
      ModelMapper modelMapper = new ModelMapper();
      // on récupère la classe à partir du nom de la table DB2
      Class<?> importDataBeanClass = this.entiteService.getClasseEntiteImporFromTableMotrice(libelleTableMotrice);

      if (importDataBeanClass != null) {
         this.em.getTransaction().begin();

         Object object;
         for (int i = 0; i < entityList.size(); i++) {
            // correspondance entre les entités DB2 et les entités en local
            object = modelMapper.map(entityList.get(i), importDataBeanClass);

            this.em.persist(object);
         }

         this.em.flush();
         this.em.getTransaction().commit();
      }
   }

   /**
    * Supprime les enregistrement de la table associée à l'entité.
    * 
    * @param entityName
    *           nom de l'entité
    */
   public void deleteTable(String entityName) {
      String query = new StringBuilder("DELETE FROM ").append(entityName).toString();

      this.em.getTransaction().begin();
      this.em.createQuery(query).executeUpdate();
      this.em.getTransaction().commit();
   }

}
