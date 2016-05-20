package com.avancial.app.service;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.avancial.app.data.model.databean.JeuDonneeDataBean;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

public class JeuDonneeService {
   @Inject
   @Socle_PUSocle
   private EntityManager em;

   public JeuDonneeDataBean save(JeuDonneeDataBean jeuDonneeDataBean) {
      // FIXME a cenlever lors de la mise en place de l'@Inject
      // this.em = Persistence.createEntityManagerFactory("PU_app").createEntityManager();
      this.em.getTransaction().begin();
      this.em.persist(jeuDonneeDataBean);
      this.em.flush();
      this.em.getTransaction().commit();
      return jeuDonneeDataBean;
   }

}
