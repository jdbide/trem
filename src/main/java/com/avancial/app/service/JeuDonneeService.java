package com.avancial.app.service;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.avancial.app.data.model.databean.JeuDonneeDataBean;

public class JeuDonneeService {
   // TODO @Inject
   private EntityManager em;

   public JeuDonneeDataBean save(JeuDonneeDataBean jeuDonneeDataBean) {
      // FIXME a cenlever lors de la mise en place de l'@Inject
      this.em = Persistence.createEntityManagerFactory("PU_app").createEntityManager();
      this.em.getTransaction().begin();
      this.em.persist(jeuDonneeDataBean);
      this.em.flush();
      this.em.getTransaction().commit();

      return jeuDonneeDataBean;
   }

}
