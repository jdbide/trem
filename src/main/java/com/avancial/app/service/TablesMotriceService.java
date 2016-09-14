package com.avancial.app.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;

import com.avancial.app.data.databean.RefTablesMotriceEntity;
import com.avancial.socle.service.AService;

/**
 * Service permettant la gestion de TablesMotrice
 * 
 * @author gabriel.gagnier
 *
 */
@RequestScoped
public class TablesMotriceService extends AService {

   /**
	 * 
	 */
	private static final long serialVersionUID = 4894318770937495714L;

	/**
    * Retourne le contenu de la table TablesMotrice
    * 
    * @return List<{@link TablesMotriceEntity}>
    */
   public List<RefTablesMotriceEntity> getAllTablesMotrice() {
      Query query = this.getEntityManager().createNamedQuery("TablesMotrice.getAll", RefTablesMotriceEntity.class);
      return ((List<RefTablesMotriceEntity>) query.getResultList());
   }
}
