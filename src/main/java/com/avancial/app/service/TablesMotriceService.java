package com.avancial.app.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.avancial.app.data.model.databean.TablesMotriceDataBean;

public class TablesMotriceService {
   // TODO @Inject
   private EntityManager em;

   public List<TablesMotriceDataBean> getAllTablesMotrice() {
      // FIXME a cenlever lors de la mise en place de l'@Inject
      this.em = Persistence.createEntityManagerFactory("PU_app").createEntityManager();

      Query query = this.em.createNamedQuery("TablesMortice.getAll", TablesMotriceDataBean.class);
      return query.getResultList();
   }
}
