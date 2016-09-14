package com.avancial.socle.ihm.menu.data.dao;

import java.util.List;

import javax.persistence.Query;

import com.avancial.socle.data.controller.dao.AbstractDao;
import com.avancial.socle.ihm.menu.model.databean.RubriqueDataBean;

public class IhmRubriqueDao extends AbstractDao {

   @SuppressWarnings("unchecked")
   @Override
   public List<RubriqueDataBean> getAll() {
      this.getEntityManager().clear();
      Query query = this.getEntityManager().createQuery("FROM RubriqueDataBean");
      return query.getResultList();
   }

   @SuppressWarnings("unchecked")
   public List<RubriqueDataBean> getAllActif() {
      String sql = "From RubriqueDataBean where actif=1";
      Query requete = this.getEntityManager().createQuery(sql);
      return requete.getResultList();
   }

}
