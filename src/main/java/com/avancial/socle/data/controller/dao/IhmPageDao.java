package com.avancial.socle.data.controller.dao;

import java.util.List;
import javax.persistence.Query;
import com.avancial.socle.data.model.databean.IhmPageDataBean;

public class IhmPageDao extends AbstractDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<IhmPageDataBean> getAll() {
        this.getEntityManager().clear();
        Query query = this.getEntityManager().createQuery("FROM IhmPageDataBean");
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<IhmPageDataBean> getAllActif() {
        String sql = "From IhmPageDataBean where actifIhmPage=1";
        Query requete = this.getEntityManager().createQuery(sql);
        return requete.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<IhmPageDataBean> getPageActifByIdChapitre(Long idChapitre) {
        this.getEntityManager().clear();
        Query query = this.getEntityManager().createQuery("FROM IhmPageDataBean where actifIhmPage=1 and idChapitre = :idChapitre");
        query.setParameter("idChapitre", idChapitre);
        return query.getResultList();
    }
}
