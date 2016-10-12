package com.avancial.app.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;

import com.avancial.app.data.databean.CompagnieEntity;
import com.avancial.socle.service.AService;

@RequestScoped
public class CompagnieService extends AService implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   public List<CompagnieEntity> getAll() {
      Query query = this.getEntityManager().createNamedQuery("Compagnie.findAll", CompagnieEntity.class);
      return query.getResultList();
   }

   public CompagnieEntity getById(Long idCompagnie) {
      Query query = this.getEntityManager().createNamedQuery("Compagnie.findById", CompagnieEntity.class);
      query.setParameter("idCompagnie", idCompagnie);
      return (CompagnieEntity) query.getSingleResult();
   }

   public List<CompagnieEntity> getByNomTechnique(String nomTechniqueCompagnie) {
      Query query = this.getEntityManager().createNamedQuery("Compagnie.findByNomTechnique", CompagnieEntity.class);
      query.setParameter("nomTechniqueCompagnie", nomTechniqueCompagnie);
      return query.getResultList();
   }
}
