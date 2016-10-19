package com.avancial.socle.params.data.dao;

import java.util.List;

import javax.persistence.Query;

import com.avancial.socle.data.controller.dao.AbstractDao;
import com.avancial.socle.params.data.entities.RefDirectoryEntity;

/**
 * Classe DAO pour l'objet technique RefDirectory
 * 
 * @author bruno
 * 
 */
public class RefDirectoryDao extends AbstractDao {

   /**
    * 
    */
   private static final long serialVersionUID = -2698430580961221287L;

   /**
    * Retourne un RefDirectoryEntity à partir du nom technique
    * 
    * @param technicalName
    * 
    * @return l'utilisateur ayant comme login le parametre
    */
   public RefDirectoryEntity getRefDirectoryByTechnicalName(String technicalName) {
      String sql = "FROM RefDirectoryEntity refDirectory WHERE refDirectory.technicalNameRefDirectory = :technicalName";

      Query requete = this.getEntityManager().createQuery(sql);
      requete.setParameter("technicalName", technicalName);

      return (RefDirectoryEntity) requete.getSingleResult();

   }

   /**
    * @return une collection de bean
    */
   @Override
   public List<?> getAll() {
      if (this.getEntityManager() == null)
         return null;
      String sql = "From RefDirectoryEntity";
      Query requete = this.getEntityManager().createQuery(sql);
      return requete.getResultList();
   }
}
