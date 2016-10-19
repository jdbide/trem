/**
 * 
 */
package com.avancial.socle.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;

import com.avancial.socle.params.data.entities.RefDirectoryEntity;

/**
 * Service pour la gestion des repertoire de l'application
 * 
 * @author hamza.laterem
 *
 */
@RequestScoped
public class RefDirectoryService extends AService implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   /**
    * 
    */
   public RefDirectoryService() {
   }

   /**
    * Retourne un RefDirectoryDataBean a partir du nom technique
    * 
    * @param technicalName
    * @return
    */
   public RefDirectoryEntity getRefDirectoryByTechnicalName(String technicalName) {
      RefDirectoryEntity res = null;

      try {
         Query requete = this.getEntityManager().createNamedQuery(RefDirectoryEntity.QUERY_GET_BY_NOM_TECH);

         requete.setParameter("technicalName", technicalName);

         if (!requete.getResultList().isEmpty())
            res = (RefDirectoryEntity) requete.getSingleResult();
      } catch (Exception ex) {
         ex.printStackTrace();
         throw ex;
      } finally {
         return res;
      }
   }

   /**
    * @return une collection de bean
    */
   public List<RefDirectoryEntity> getAll() {
      List<RefDirectoryEntity> res = null;

      try {
         if (this.getEntityManager() == null)
            return null;

         String sql = "From RefDirectoryDataBean";
         Query requete = this.getEntityManager().createQuery(sql);
         res = requete.getResultList();
      } catch (Exception ex) {
         ex.printStackTrace();
         throw ex;
      } finally {
         return res;
      }
   }
}
