/**
 * 
 */
package com.avancial.app.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import com.avancial.app.utilitaire.GetEntiteService;
import com.avancial.socle.service.AService;

/**
 * @author sebastien.benede Classe qui gère toutes les requêtes dans les tables d'import.
 */
@RequestScoped
public class ImportMotriceService extends AService {
   /**
	 * 
	 */
	private static final long serialVersionUID = -1097499901107750530L;

   @Inject
   private GetEntiteService getEntiteService;

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
      modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

      Class<?> importEntityClass = this.getEntiteService.getClasseEntiteImportFromTableMotrice(libelleTableMotrice);

      if (importEntityClass != null) {
         this.getEntityManager().getTransaction().begin();

         Object object;
         for (int i = 0; i < entityList.size(); i++) {
            object = modelMapper.map(entityList.get(i), importEntityClass);// mapping entre les deux entités

            this.getEntityManager().persist(object);
         }

         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit();
      }
   }

   /**
    * Supprime les enregistrements de la table avant import.
    * 
    * @param entityName
    */
   public void deleteTable(String entityName) {
      String query = new StringBuilder("DELETE FROM ").append(entityName).toString();

      this.getEntityManager().getTransaction().begin();
      this.getEntityManager().createQuery(query).executeUpdate();
      this.getEntityManager().getTransaction().commit();
   }

}
