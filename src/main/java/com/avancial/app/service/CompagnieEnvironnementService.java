/**
 * 
 */
package com.avancial.app.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.CompagnieEnvironnementEntity;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

/**
 * @author hamza.laterem
 *
 */
@SessionScoped
public class CompagnieEnvironnementService implements Serializable {
   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   @Inject
   @Socle_PUSocle
   private EntityManager       em;
   
   /**
    * 
    */
   public CompagnieEnvironnementService() {
      // TODO Auto-generated constructor stub
   }
   
   public CompagnieEnvironnementEntity getCompagnieEnvironnementById(Integer idCompagnieEnvironnement) {
      Query query = this.em.createNamedQuery("CompagnieEnvironnementEntity.findById", CompagnieEnvironnementEntity.class);
      query.setParameter("idCompagnieEnvironnement", idCompagnieEnvironnement);
      return ((CompagnieEnvironnementEntity) query.getSingleResult());
   }
   
   public List<CompagnieEnvironnementEntity> getAllCompagnieEnvironnementActif() {
      Query query = this.em.createNamedQuery("CompagnieEnvironnementEntity.findAllActif", CompagnieEnvironnementEntity.class);
      return ((List<CompagnieEnvironnementEntity>) query.getResultList());
   }

}
