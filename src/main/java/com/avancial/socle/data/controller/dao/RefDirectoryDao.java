package com.avancial.socle.data.controller.dao;

import java.util.List;

import javax.persistence.Query;

import com.avancial.socle.data.model.databean.RefDirectoryDataBean;

/**
 * Classe DAO pour l'objet technique RefDirectory
 * 
 * @author bruno
 * 
 */
public class RefDirectoryDao extends AbstractDao {

   /**
    * Retourne un RefDirectoryDataBean � partir du nom technique
    * 
    * @param technicalName
    * 
    * @return l'utilisateur ayant comme login le parametre
    */
   public RefDirectoryDataBean getRefDirectoryByTechnicalName(String technicalName) {
      String sql = "FROM RefDirectoryDataBean refDirectory WHERE refDirectory.technicalNameRefDirectory = :technicalName";

      Query requete = this.getEntityManager().createQuery(sql);
      requete.setParameter("technicalName", technicalName);

      return (RefDirectoryDataBean) requete.getSingleResult();

   }

   /**
    * @return une collection de bean
    */
   @Override
   public List<?> getAll() {
      if (this.getEntityManager() == null)
         return null;
      String sql = "From RefDirectoryDataBean";
      Query requete = this.getEntityManager().createQuery(sql);
      return requete.getResultList();
   }
}
