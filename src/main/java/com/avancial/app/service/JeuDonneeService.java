package com.avancial.app.service;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

public class JeuDonneeService {
   @Inject
   @Socle_PUSocle
   EntityManager       em;

   /**
    * Sauvegarde le jeu de données dans la table.
    * @param jeuDonneeDataBean
    * @return
    */
   public JeuDonneeEntity save(JeuDonneeEntity jeuDonneeDataBean) {
      this.em.getTransaction().begin();
      this.em.persist(jeuDonneeDataBean);
      this.em.flush();
      this.em.getTransaction().commit();
      
      return jeuDonneeDataBean;
   }  

}
