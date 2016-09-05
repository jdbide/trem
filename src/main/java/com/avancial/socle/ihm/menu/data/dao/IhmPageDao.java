package com.avancial.socle.ihm.menu.data.dao;

import java.util.List;

import javax.persistence.Query;

import com.avancial.socle.data.controller.dao.AbstractDao;
import com.avancial.socle.ihm.menu.model.databean.PageDataBean;

public class IhmPageDao extends AbstractDao {

   @SuppressWarnings("unchecked")
   @Override
   public List<PageDataBean> getAll() {
      this.getEntityManager().clear();
      Query query = this.getEntityManager().createQuery("FROM IhmPageDataBean");
      return query.getResultList();
   }

   @SuppressWarnings("unchecked")
   public List<PageDataBean> getAllActif() {
      String sql = "From IhmPageDataBean where actifIhmPage=1";
      Query requete = this.getEntityManager().createQuery(sql);
      return requete.getResultList();
   }

   @SuppressWarnings("unchecked")
   public List<PageDataBean> getPageActifByIdChapitre(Long idChapitre) {
      this.getEntityManager().clear();
      Query query = this.getEntityManager().createQuery("FROM IhmPageDataBean where actifIhmPage=1 and idChapitre = :idChapitre");
      query.setParameter("idChapitre", idChapitre);
      return query.getResultList();
   }
}
