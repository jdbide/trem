package com.avancial.app.service;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.TablesMotriceDataBean;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

public class TablesMotriceService {
   
   @Inject
   @Socle_PUSocle
   private EntityManager em;

   public List<TablesMotriceDataBean> getAllTablesMotrice() {
      // FIXME a cenlever lors de la mise en place de l'@Inject
//      this.em = Persistence.createEntityManagerFactory("PU_app").createEntityManager();

      Query query = this.em.createNamedQuery("TablesMortice.getAll", TablesMotriceDataBean.class);
      return query.getResultList();
   }
}
