package com.avancial.app.service;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;

import com.avancial.app.data.databean.RefTablesMotriceRegimeEntity;
import com.avancial.socle.service.AService;

@RequestScoped
public class RefTablesMotriceRegimeService extends AService implements Serializable {

   /**
   * 
   */
   private static final long serialVersionUID = 1L;

   /**
    * Retourne le contenu de la table RefTablesMotriceRegime
    * 
    * @return List<{@link RefTablesMotriceRegimeEntity}>
    */
   public List<RefTablesMotriceRegimeEntity> getAllRefTablesMotriceRegime() {
      Query query = this.getEntityManager().createNamedQuery("RefTablesMotriceRegime.getAll", RefTablesMotriceRegimeEntity.class);
      return ((List<RefTablesMotriceRegimeEntity>) query.getResultList());
   }

}
