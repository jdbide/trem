/**
 * 
 */
package com.avancial.app.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.CompagnieEnvironnementEntity;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;
import com.avancial.socle.service.AService;

/**
 * @author hamza.laterem
 *
 */
@RequestScoped
public class CompagnieEnvironnementService extends AService implements Serializable {
   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   /**
    * 
    */
   public CompagnieEnvironnementService() {
      // TODO Auto-generated constructor stub
   }

   public CompagnieEnvironnementEntity getCompagnieEnvironnementById(Integer idCompagnieEnvironnement) {
      Query query = this.getEntityManager().createNamedQuery("CompagnieEnvironnementEntity.findById", CompagnieEnvironnementEntity.class);
      query.setParameter("idCompagnieEnvironnement", idCompagnieEnvironnement);
      return ((CompagnieEnvironnementEntity) query.getSingleResult());
   }

   public List<CompagnieEnvironnementEntity> getAllCompagnieEnvironnementActif() {
      List<CompagnieEnvironnementEntity> datas = null;
      
      try {
         Query query = this.getEntityManager().createNamedQuery("CompagnieEnvironnementEntity.findAllActif", CompagnieEnvironnementEntity.class);
         
         datas = (List<CompagnieEnvironnementEntity>) query.getResultList();
      } catch (Exception ex) {
         ex.printStackTrace();
         throw ex;
      } finally {
         return datas;
      }
   }

}
