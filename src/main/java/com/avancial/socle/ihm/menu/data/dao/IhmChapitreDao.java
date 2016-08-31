package com.avancial.socle.ihm.menu.data.dao;

import java.util.List;

import javax.persistence.Query;

import com.avancial.socle.data.controller.dao.AbstractDao;
import com.avancial.socle.ihm.menu.model.databean.ChapitreDataBean;

public class IhmChapitreDao extends AbstractDao {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   @SuppressWarnings("unchecked")
   @Override
   public List<ChapitreDataBean> getAll() {
      this.getEntityManager().clear();
      Query query = this.getEntityManager().createQuery("FROM IhmChapitreDataBean");
      return query.getResultList();
   }

   @SuppressWarnings("unchecked")
   public List<ChapitreDataBean> getAllActif() {
      String sql = "From IhmChapitreDataBean where actifIhmChapitre=1";
      Query requete = this.getEntityManager().createQuery(sql);
      return requete.getResultList();
   }

   @SuppressWarnings("unchecked")
   public List<ChapitreDataBean> getChapitreActifByIdRubrique(Long idRubrique) {
      this.getEntityManager().clear();
      Query query = this.getEntityManager().createQuery("FROM IhmChapitreDataBean where actifIhmChapitre = 1 and idRubrique = :idRubrique");
      query.setParameter("idRubrique", idRubrique);
      return query.getResultList();
   }

}
