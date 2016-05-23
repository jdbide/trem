package com.avancial.app.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.avancial.app.data.model.databean.JeuDonneeDataBean;
import com.avancial.app.data.model.databean.TablesMotriceDataBean;

public class JeuDonneeService {
   // TODO @Inject
   private EntityManager em;
   private EntityManager ext;

   public JeuDonneeDataBean save(JeuDonneeDataBean jeuDonneeDataBean) {
      // FIXME a cenlever lors de la mise en place de l'@Inject
      this.em = Persistence.createEntityManagerFactory("PU_app").createEntityManager();
      this.em.getTransaction().begin();
      this.em.persist(jeuDonneeDataBean);
      this.em.flush();
      this.em.getTransaction().commit();

      return jeuDonneeDataBean;
   }

   public static <T> T convertInstanceOfObject(Object o, Class<T> clazz) {
      try {
         return clazz.cast(o);
      } catch (ClassCastException e) {
         return null;
      }
   }

   public void readTable(TablesMotriceDataBean tableMotrice) {
      this.ext = Persistence.createEntityManagerFactory("pu-externe").createEntityManager();
      Query query = this.em.createQuery("FROM " + tableMotrice.getLibelleTablesMotrice() + "DataBean");

      List<?> tmdavtrDataBeans = query.getResultList();
      for(int i=0; i<tmdavtrDataBeans.size(); i++) {
         
      }

   }

}
