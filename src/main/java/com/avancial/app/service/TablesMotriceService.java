package com.avancial.app.service;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.TablesMotriceDataBean;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

/**
 * Service permetant la gestion de TablesMotrice
 * @author gabriel.gagnier
 *
 */
public class TablesMotriceService {

   @Inject
   @Socle_PUSocle
   private EntityManager em;

   /**
    * retourne le contenue de la table TablesMotrice
    * @return List<TablesMotriceDataBean>
    */
   public List<TablesMotriceDataBean> getAllTablesMotrice() {
      Query query = this.em.createNamedQuery("TablesMortice.getAll", TablesMotriceDataBean.class);
      return query.getResultList();
   }

}
