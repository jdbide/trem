/**
 * 
 */
package com.avancial.socle.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.socle.data.model.databean.RefDirectoryDataBean;
import com.avancial.socle.ihm.menu.model.databean.ChapitreDataBean;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

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
    * @param technicalName
    * @return
    */
   public RefDirectoryDataBean getRefDirectoryByTechnicalName(String technicalName) {
      RefDirectoryDataBean res = null;

      try {
         Query requete = this.getEntityManager().createNamedQuery(RefDirectoryDataBean.QUERY_GET_BY_NOM_TECH);

         requete.setParameter("technicalName", technicalName);

         if (!requete.getResultList().isEmpty())
            res = (RefDirectoryDataBean) requete.getSingleResult();
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
   public List<RefDirectoryDataBean> getAll() {
      List<RefDirectoryDataBean> res = null;

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
